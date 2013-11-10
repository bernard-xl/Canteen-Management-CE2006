package solid5ive.cams.data.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * The entity class for the record of a stall in canteen, cacheable at runtime.
 * 
 * @author Poh Shie Liang
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
public class Stall implements Serializable {
    @Id @GeneratedValue
    private Integer id;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private User owner;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Canteen canteen;

    @Column(length = 32, unique = true)
    private String lotNumber;
    
    @Column(length = 128, nullable = true)
    private String foodStyle;

    @Column(length = 1024, nullable = true)
    private String name;

    public Stall() {
    }
    
    public Stall(Stall copy) {
        this.canteen = copy.canteen;
        this.foodStyle = copy.foodStyle;
        this.id = copy.id;
        this.lotNumber = copy.lotNumber;
        this.name = copy.name;
        this.owner = copy.owner;
    }

    public Stall(Canteen canteen, String lotNumber) {
        this.canteen = canteen;
        this.lotNumber = lotNumber;
    }

    public Stall(User owner, Canteen canteen, String lotNumber) {
        this.owner = owner;
        this.canteen = canteen;
        this.lotNumber = lotNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Canteen getCanteen() {
        return canteen;
    }

    public void setCanteen(Canteen canteen) {
        this.canteen = canteen;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoodStyle() {
        return foodStyle;
    }

    public void setFoodStyle(String foodStyle) {
        this.foodStyle = foodStyle;
    }

}
