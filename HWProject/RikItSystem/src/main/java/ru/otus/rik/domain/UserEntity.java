package ru.otus.rik.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "User",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}),
                            @UniqueConstraint(columnNames = {"email"})}
       )
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    private String name;
    private String email;
    private String phone;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="departmentRef", referencedColumnName="idDepartment")
    private DepartmentEntity departmentRef;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="positionRef", referencedColumnName="idPosition")
    private PositionEntity positionRef;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="roleRef", referencedColumnName="idRole")
    private RoleEntity roleRef;
}
