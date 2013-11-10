package solid5ive.cams.data.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The entity class that records user contact information such as phone number,
 * e-mail address, and postal address.
 * 
 * @author Poh Shie Liang
 */
@Entity
public class UserContact implements Serializable {
    @Id @GeneratedValue
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    @Column(length = 512, nullable = false)
    private String contact;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContactType type;

    public UserContact() {
    }

    public UserContact(User user, String contact, ContactType type) {
        this.user = user;
        this.contact = contact;
        this.type = type;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }
}
