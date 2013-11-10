package solid5ive.cams.data.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
/**
 * Incompleted features, this is an entity used to keep track of
 * top-up and refund activities to further manage cash shrinkage.
 * 
 * @author Poh Shie Liang
 */
@Entity
public class BalanceTransaction {
    @Id @GeneratedValue
    private Integer id;
    
    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime dateTime;
    
    @Column(precision = 2, nullable = false)
    private Double amount;
    
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User admin;
    
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User user;
    

    public BalanceTransaction() {
    }
    
    public BalanceTransaction(User admin, User user, Double amount) {
        this.dateTime = new DateTime();
        this.admin = admin;
        this.user = user;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
