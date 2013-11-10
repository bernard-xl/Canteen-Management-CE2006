package solid5ive.cams.data.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The entity class that records account balance of an user.
 * 
 * @author Poh Shie Liang
 */
@Entity
public class UserAccount implements Serializable{
    @Id @GeneratedValue
    private Integer id;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    @Column(precision = 2, nullable = false)
    private Double balance;

    public UserAccount() {
    }

    public UserAccount(User user, Double balance) {
        this.user = user;
        this.balance = balance;
    }

    public void addBalance(Double amount) {
        balance += amount;
    }

    public void deductBalance(Double amount) {
        balance -= amount;
    }

    public void clearBalance() {
        balance = 0.00;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
