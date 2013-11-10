package solid5ive.cams.data.entities;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Entity class for a record of transaction between stall owners and customers,
 * consist of food order information as well for sales reporting purpose.
 * 
 * @see Orderline
 * 
 * @author Poh Shie Liang
 */
@Entity
@Table(name = "Purchase")
public class Order implements Serializable {
    @Id @GeneratedValue
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Stall stall;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User customer;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime timestamp;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "OrderToLinesMap", joinColumns = @JoinColumn(name = "orderId"), inverseJoinColumns = @JoinColumn(name = "lineId"))
    private List<Orderline> lines = new LinkedList<>();

    public Order() {
        timestamp = new DateTime();
    }

    public Order(Stall stall, User customer) {
        this.stall = stall;
        this.customer = customer;
        this.timestamp = new DateTime();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Stall getStall() {
        return stall;
    }

    public void setStall(Stall stall) {
        this.stall = stall;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(DateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<Orderline> getLines() {
        return lines;
    }

    public void setLines(List<Orderline> lines) {
        this.lines = lines;
    }
}
