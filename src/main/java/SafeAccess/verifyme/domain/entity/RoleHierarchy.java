package SafeAccess.verifyme.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role_hierarchy")
public class RoleHierarchy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_role_id")
    private Role parentRole;

    @ManyToOne
    @JoinColumn(name = "child_role_id")
    private Role childRole;
}
