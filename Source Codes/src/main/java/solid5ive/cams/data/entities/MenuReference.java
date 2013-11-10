package solid5ive.cams.data.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Entity class that records information of a menu item that is to be used for sales reporting.
 * These records are never deleted in database so the sales report is able refer to them anytime
 * even the stall no longer selling the item (the item no longer exist in menu).
 * 
 * @author Poh Shie Liang
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
public class MenuReference implements Serializable {
    @Id @GeneratedValue
    private Integer id;

    @Column(length = 1024, nullable = false)
    private String name;

    @Column(precision = 2, nullable = false)
    private Double price;

    public MenuReference() {
    }

    public MenuReference(String name, Double price) {
        this.name = name;
        this.price = price;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
