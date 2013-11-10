package solid5ive.cams.data.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * User permission is not supposed to be managed by our system (It is managed by school/higher level department).
 * This entity class is here for the ease of create mock user during testing and demostration of the system.
 * 
 * @author Poh Shie Liang
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
public class UserPermission implements Serializable {
    @Id @GeneratedValue
    private Integer id;

    @Column(length = 256, nullable = false)
    private String permission;

    public UserPermission() {
        permission = "";
    }

    public UserPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof UserPermission) {
            return permission.equals(((UserPermission)object).permission);
        } else if(object instanceof String) {
            return permission.equals(object);
        }
        return false;
    }

    public int hashCode() {
        return permission.hashCode();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
