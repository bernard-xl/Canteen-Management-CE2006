package solid5ive.cams.data.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * The entity class that records basic information about an user in the system.
 * Other details of an user are stored in other entities.
 * 
 * @see UserContact
 * @see UserAccount
 * 
 * @author Poh Shie Liang
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
public class User implements Serializable {
    @Id
    private String loginName;

    @Column(length = 2048, nullable = false)
    @Basic(fetch = FetchType.LAZY)
    private String password;

    @Column(length = 512, nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "UserToRoleMap", joinColumns = @JoinColumn(name = "userLogin"), inverseJoinColumns = @JoinColumn(name = "roleId"))
    @MapKeyColumn(name = "role")
    private Map<String, UserRole> roles = new HashMap<>();

    public User() {
    }

    public User(String loginName, String password, String name) {
        this.loginName = loginName;
        this.password = password;
        this.name = name;
    }

    public void addRole(UserRole role) {
        roles.put(role.getRole(), role);
    }

    public void removeRole(UserRole role) {
        roles.remove(role.getRole());
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Map<String, UserRole> roles) {
        this.roles = roles;
    }
    
    @Override
    public String toString() {
        return loginName + " (" + name + ")";
    }
}
