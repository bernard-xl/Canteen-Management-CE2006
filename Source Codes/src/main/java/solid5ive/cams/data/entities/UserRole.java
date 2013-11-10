package solid5ive.cams.data.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * User role is not supposed to be managed by our system (It is managed by school/higher level department).
 * This entity class is here for the ease of create mock user during testing and demostration of the system.
 * 
 * @author Poh Shie Liang
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
public class UserRole implements Serializable {
    @Id @GeneratedValue
    private Integer id;

    @Column(length = 128, unique = true)
    private String role;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "RoleToPermissionMap", joinColumns = @JoinColumn(name = "roleId"), inverseJoinColumns = @JoinColumn(name = "permissionId"))
    private Set<UserPermission> permissions = new HashSet<>();

    public UserRole() {
        role = "";
    }

    public UserRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof UserRole) {
            return role.equals(((UserRole)object).role);
        } else if(object instanceof String) {
            return role.equals(object);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return role.hashCode();
    }

    public boolean hasPermission(String permission) {
        return permissions.contains(new UserPermission("UserCrudTest:test"));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }
}
