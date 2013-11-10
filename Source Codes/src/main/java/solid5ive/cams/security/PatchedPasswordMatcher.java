package solid5ive.cams.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.credential.PasswordMatcher;

/**
 * This is a bug fix patch to Shiro library.
 * There are some mismatch of data type char[] and String in the library.
 * 
 * @author Poh Shie Liang
 */
public class PatchedPasswordMatcher extends PasswordMatcher {

    @Override
    protected Object getStoredPassword(AuthenticationInfo storedAccountInfo) {
        Object stored = super.getStoredPassword(storedAccountInfo);
        if (stored instanceof char[]) {
            return new String((char[])stored);
        }
        return stored;
    }
}
