package solid5ive.cams.data.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity class for Canteen records.
 *
 * @author Poh Shie Liang
 */

@Entity
public class Canteen implements Serializable {
    @Id @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String name;

    public Canteen() {
    }

    public Canteen(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return getName();
    }
}
