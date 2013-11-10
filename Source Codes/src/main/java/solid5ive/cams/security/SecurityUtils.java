package solid5ive.cams.security;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;

/**
 * A helper class that generates salted password.
 * 
 * @author Poh Shie Liang
 */
public class SecurityUtils {

    public static String encryptPassword(String password) {
        PasswordService passwordService = new DefaultPasswordService();
        return passwordService.encryptPassword(password);
    }
}
