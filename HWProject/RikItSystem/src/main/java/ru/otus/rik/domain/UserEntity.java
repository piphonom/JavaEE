package ru.otus.rik.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

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
public class UserEntity {
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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name="roleRef", referencedColumnName="idRole")
    @XmlElement(name = "Role")
    private RoleEntity roleRef;
}
