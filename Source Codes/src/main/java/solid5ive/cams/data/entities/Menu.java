package solid5ive.cams.data.entities;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Cache;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity class for menu item records, cacheable during runtime.
 * Important information such as food name and price are stored in another entity for versioning purpose.
 * 
 * @see MenuReference
 * 
 * @author Poh Shie Liang
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
public class Menu implements Serializable {
    @Id @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime lastModified;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Stall stall;

    @OneToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private MenuReference reference;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private MenuTag tag;
    
    @Column(length = 6000, nullable = true)
    private String description;

    public Menu() {
        lastModified = new DateTime();
    }

    public Menu(MenuReference reference, Stall stall) {
        this.reference = reference;
        this.stall = stall;
        this.lastModified = new DateTime();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(DateTime lastModified) {
        this.lastModified = lastModified;
    }

    public Stall getStall() {
        return stall;
    }

    public void setStall(Stall stall) {
        this.stall = stall;
    }

    public MenuReference getReference() {
        return reference;
    }

    public void setReference(MenuReference reference) {
        this.reference = reference;
    }

    public MenuTag getTag() {
        return tag;
    }

    public void setTag(MenuTag tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
