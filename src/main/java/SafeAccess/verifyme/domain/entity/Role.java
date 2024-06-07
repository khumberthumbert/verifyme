package SafeAccess.verifyme.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> userRoles = new HashSet<>();

    @OneToMany(mappedBy = "parentRole", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RoleHierarchy> childRoles = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "parent_role_id")
    private Role parentRole;
}
