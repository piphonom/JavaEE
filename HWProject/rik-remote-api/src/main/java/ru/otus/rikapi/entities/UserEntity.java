package ru.otus.rikapi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "User",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}),
                            @UniqueConstraint(columnNames = {"email"})}
       )
@NamedStoredProcedureQueries({
       @NamedStoredProcedureQuery(
           name = "findByMaxSalary",
           procedureName = "GetMaxSalary",
           resultClasses = { UserEntity.class }
           )
})
@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private int idUser;

    @XmlElement
    private String name;
    @XmlElement
    private String email;
    @XmlElement
    private String phone;
    private String pwdHash;
    private String salt;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name="departmentRef", referencedColumnName="idDepartment")
    @XmlElement(name = "Department")
    private DepartmentEntity departmentRef;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name="positionRef", referencedColumnName="idPosition")
    @XmlElement(name = "Position")
    private PositionEntity positionRef;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "UserRoles",
            joinColumns = { @JoinColumn(name = "idUser") },
            inverseJoinColumns = { @JoinColumn(name = "idRole") }
    )
    @XmlTransient
    private Set<RoleEntity> roles = new HashSet<>();

    /*
    void writeObject(ObjectOutputStream outStream) throws IOException {
        outStream.defaultWriteObject();
        outStream.writeObject(name);
        outStream.writeObject(email);
    }

    void readObject(ObjectInputStream inStream) throws IOException, ClassNotFoundException {
        inStream.defaultReadObject();
        Object userName = inStream.readObject();
        if (userName == null || !(userName instanceof String)) {
            throw new IOException("Bad deserialization");
        }
        name = (String) userName;

        Object userEmail = inStream.readObject();
        if (userEmail == null || !(userEmail instanceof String)) {
            throw new IOException("Bad deserialization");
        }
        email = (String) userEmail;
    }
    */
}
