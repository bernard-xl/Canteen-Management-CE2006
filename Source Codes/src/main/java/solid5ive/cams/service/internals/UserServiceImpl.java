package solid5ive.cams.service.internals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solid5ive.cams.data.entities.*;
import solid5ive.cams.data.repositories.AccountRepository;
import solid5ive.cams.data.repositories.ContactRepository;
import solid5ive.cams.data.repositories.StallRepository;
import solid5ive.cams.data.repositories.UserRepository;
import solid5ive.cams.security.SecurityUtils;
import solid5ive.cams.service.contracts.UserService;

import java.util.List;
import solid5ive.cams.data.repositories.TransactionRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StallRepository stallRepository;
    
    @Autowired
    private TransactionRepository transRepository;

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }
    
    @Override
    public User findUser(String loginName) {
        return userRepository.findOne(loginName);
    }
    
    @Override
    public User createUser(String loginName, String password, String name) {
        User user = new User(loginName, SecurityUtils.encryptPassword(password), name);
        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(String loginName) {
        User user = userRepository.findOne(loginName);
        UserAccount account = accountRepository.findByUser(user);
        List<UserContact> contacts = contactRepository.findByUser(user);
        List<Stall> stalls = stallRepository.findByOwner(user);

        if(account != null) accountRepository.delete(account);
        if(contacts != null && !contacts.isEmpty()) contactRepository.delete(contacts);
        if(user != null) userRepository.delete(user);
        else throw new IllegalArgumentException("The user doesn't exist.");

        if(stalls != null) for(Stall stall : stalls) stall.setOwner(null);
        stallRepository.save(stalls);
    }

    @Override
    public List<UserContact> listUserContacts(String userLogin) {
        User user = userRepository.findOne(userLogin);
        if(user == null) throw new IllegalArgumentException("The user doesn't exist.");

        return contactRepository.findByUser(user);
    }

    @Override
    public List<UserContact> listUserContacts(String userLogin, ContactType type) {
        User user = userRepository.findOne(userLogin);
        if(user == null) throw new IllegalArgumentException("The user doesn't exist.");

        return contactRepository.findByUserAndType(user, type);
    }

    @Override
    public UserContact createUserContact(String userLogin, ContactType type, String contact) {
        User user = userRepository.findOne(userLogin);
        if(user == null) throw new IllegalArgumentException("The user doesn't exist.");

        UserContact userContact = new UserContact(user, contact, type);
        contactRepository.save(userContact);
        return userContact;
    }

    @Override
    public UserContact updateUserContact(Integer contactId, ContactType type, String contact) {
        UserContact userContact = contactRepository.findOne(contactId);
        if(userContact == null) throw new IllegalArgumentException("The contact doesn't exist.");

        if(type != null) userContact.setType(type);
        if(contact != null) userContact.setContact(contact);
        contactRepository.save(userContact);

        return userContact;
    }

    @Override
    public void deleteUserContact(Integer contactId) {
        UserContact userContact = contactRepository.findOne(contactId);
        if(userContact == null) throw new IllegalArgumentException("The contact doesn't exist.");

        contactRepository.delete(userContact);
    }

    @Override
    public UserAccount findUserAccount(String userLogin) {
        User user = userRepository.findOne(userLogin);
        if(user == null) throw new IllegalArgumentException("The user doesn't exist.");

        return accountRepository.findByUser(user);
    }

    @Override
    public UserAccount createUserAccount(String userLogin) {
        User user = userRepository.findOne(userLogin);
        if(user == null) throw new IllegalArgumentException("The user doesn't exist.");

        UserAccount userAccount = new UserAccount(user, 0.00);
        accountRepository.save(userAccount);
        return userAccount;
    }

    @Override
    public void deleteUserAccount(String userLogin) {
        User user = userRepository.findOne(userLogin);
        
        if(user == null) throw new IllegalArgumentException("The user doesn't exist.");

        UserAccount userAccount = accountRepository.findByUser(user);
        if(userAccount == null) throw new IllegalArgumentException("The user doesn't own an account.");
        accountRepository.delete(userAccount);
    }

    @Override
    public UserAccount topUpUserAccount(String adminLogin, String userLogin, Double amount) {
        User admin = userRepository.findOne(adminLogin);
        User user = userRepository.findOne(userLogin);
        if(admin == null) throw new IllegalArgumentException("Administrator doesn't exist.");
        if(user == null) throw new IllegalArgumentException("The user doesn't exist.");
        
        if(Double.compare(amount, 0.00) < 0)
            throw new IllegalArgumentException("The amount must be positive.");
        
        UserAccount userAccount = accountRepository.findByUser(user);
        if(userAccount == null) throw new IllegalArgumentException("The user doesn't own an account.");
        userAccount.addBalance(amount);
        accountRepository.save(userAccount);
        
        BalanceTransaction transaction = new BalanceTransaction(admin, user, amount);
        transRepository.save(transaction);
        return userAccount;
    }

    @Override
    public UserAccount refundUserAccount(String adminLogin, String userLogin) {
        User admin = userRepository.findOne(adminLogin);
        User user = userRepository.findOne(userLogin);
        if(admin == null) throw new IllegalArgumentException("Administrator doesn't exist.");
        if(user == null) throw new IllegalArgumentException("The user doesn't exist.");

        UserAccount userAccount = accountRepository.findByUser(user);
        Double balance = userAccount.getBalance();
        if(userAccount == null) 
            throw new IllegalArgumentException("The user doesn't own an account.");
        userAccount.clearBalance();
        accountRepository.save(userAccount);
        
        BalanceTransaction transaction = new BalanceTransaction(admin, user, -balance);
        transRepository.save(transaction);
        
        return userAccount;
    }

    @Override
    public void transferBetweenAccount(String fromLogin, String toLogin, Double amount) {
        User fromUser = userRepository.findOne(fromLogin);
        if(fromUser == null) throw new IllegalArgumentException("The user doesn't exist.");
        User toUser = userRepository.findOne(toLogin);
        if(toUser == null) throw new IllegalArgumentException("The user doesn't exist.");

        UserAccount fromAccount = accountRepository.findByUser(fromUser);
        if(fromAccount == null) throw new IllegalArgumentException("The user doesn't own an account.");
        UserAccount toAccount = accountRepository.findByUser(toUser);
        if(toAccount == null) throw new IllegalArgumentException("The user doesn't own an account.");

        if(Double.compare(amount, 0.00) < 0)
            throw new IllegalArgumentException("The transfer amount can't be negative.");
        
        if(Double.compare(fromAccount.getBalance(), amount) < 0)
            throw new IllegalArgumentException("Insufficient balance.");

        fromAccount.deductBalance(amount);
        toAccount.addBalance(amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }
}
