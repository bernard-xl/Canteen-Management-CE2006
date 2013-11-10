/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package solid5ive.cams.demo.apps;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import solid5ive.cams.configuration.SpringConfiguration;
import solid5ive.cams.data.entities.Canteen;
import solid5ive.cams.data.entities.User;
import solid5ive.cams.data.entities.UserAccount;
import solid5ive.cams.data.entities.UserRole;
import solid5ive.cams.data.repositories.AccountRepository;
import solid5ive.cams.data.repositories.RoleRepository;
import solid5ive.cams.data.repositories.UserRepository;
import solid5ive.cams.security.SecurityRuntime;
import solid5ive.cams.security.SecurityUtils;
import solid5ive.cams.service.contracts.InfrastructureService;
import solid5ive.cams.service.contracts.UserService;

@Component
public class FirstSetup {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;    
    
    @Autowired
    private AccountRepository accRepository;
    
    @Autowired
    private SecurityRuntime securityRuntime;
    
    @Autowired
    private InfrastructureService infService;
    
    @Autowired
    private UserService userService;
    
    public static void main(String... args) {
        System.out.print("Initializing the system....");
        AnnotationConfigApplicationContext ctx = 
                new AnnotationConfigApplicationContext(SpringConfiguration.class);
        FirstSetup setup = ctx.getBean(FirstSetup.class);
        String password = SecurityUtils.encryptPassword("1234");
        UserRole role = new UserRole("ADMIN");
        User root = new User("root", password, "root");
        UserAccount account = new UserAccount(root, 99999.99);
        root.addRole(role);
        setup.roleRepository.save(role);
        setup.userRepository.save(root);
        setup.accRepository.save(account);
        
        Subject subject = setup.securityRuntime.currentSubject();
        subject.login(new UsernamePasswordToken("root", "1234"));
        for(int i = 1; i <= 8; i++) {
            Canteen canteen = setup.infService.createCanteen("Canteen " + i);
            for(int j = 1; j <= 6; j++) {
                setup.infService.createStall(canteen.getId(), "#0" + i + "-" + j);
            }
        }
        
        for(int i = 1; i <= 10; i++) {
            User user = setup.userService.createUser("user" + i, "1234", "username" + i);
            UserAccount userAccount = setup.userService.createUserAccount("user" + i);
            setup.userService.topUpUserAccount("root", "user" + i, 30.00);
        }
        
        ctx.close();
        System.exit(0);
    }
    
}
