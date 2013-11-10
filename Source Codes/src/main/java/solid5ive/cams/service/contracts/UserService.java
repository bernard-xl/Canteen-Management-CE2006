package solid5ive.cams.service.contracts;

import solid5ive.cams.data.entities.ContactType;
import solid5ive.cams.data.entities.User;
import solid5ive.cams.data.entities.UserAccount;
import solid5ive.cams.data.entities.UserContact;

import java.util.List;

/**
 * Service layer that provides functionalities related to user management of the system.
 * Note that our system doesn't suppose to create or delete any user (That supposed to be managed by school/higher level department),
 * those methods are exposed here for ease of testing and demostration only.
 * 
 * @author Poh Shie Liang
 */
public interface UserService {
    public List<User> listUsers();
    public User findUser(String loginName);
    public User createUser(String loginName, String password, String name);
    public void deleteUser(String loginName);

    public List<UserContact> listUserContacts(String userLogin);
    public List<UserContact> listUserContacts(String userLogin, ContactType type);
    public UserContact createUserContact(String userLogin, ContactType type, String contact);
    public UserContact updateUserContact(Integer contactId, ContactType type, String contact);
    public void deleteUserContact(Integer contactId);

    public UserAccount findUserAccount(String userLogin);
    public UserAccount createUserAccount(String userLogin);
    public void deleteUserAccount(String userLogin);
    public UserAccount topUpUserAccount(String adminLogin, String userLogin, Double amount);
    public UserAccount refundUserAccount(String adminLogin, String userLogin);
    public void transferBetweenAccount(String fromLogin, String toLogin, Double amount);
}
