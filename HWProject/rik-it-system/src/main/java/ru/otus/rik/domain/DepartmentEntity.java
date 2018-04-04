package ru.otus.rik.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;

@Entity
@Table(name = "Department",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "location"})})
@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DepartmentEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private int idDepartment;

    @XmlElement
    private String name;

    @XmlElement
    private String location;

    @Override
    public int hashCode() {
        return idDepartment ^ name.hashCode() ^ location.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;

        return obj instanceof DepartmentEntity &&
               this.idDepartment == ((DepartmentEntity) obj).idDepartment &&
               this.name.equals(((DepartmentEntity) obj).name) &&
               this.location.equals(((DepartmentEntity) obj).location);
    }
}
