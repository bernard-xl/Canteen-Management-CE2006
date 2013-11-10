package solid5ive.cams.data.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The entity class that records an entry of food order information in an order.
 * An order contains multiple order lines.
 * 
 * @author Poh Shie Liang
 */
@Entity
public class Orderline implements Serializable{
    @Id @GeneratedValue
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MenuReference reference;

    public Orderline() {
    }

    public Orderline(MenuReference reference) {
        this.reference = reference;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MenuReference getReference() {
        return reference;
    }

    public void setReference(MenuReference reference) {
        this.reference = reference;
    }
}
