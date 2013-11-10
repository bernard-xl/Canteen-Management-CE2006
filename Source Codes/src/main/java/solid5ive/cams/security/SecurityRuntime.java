package solid5ive.cams.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.SubjectThreadState;
import org.apache.shiro.util.ThreadState;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;

/**
 * The security runtime that manages the user session as well as access control.
 * It control user permission in thread level, but also allow developer to change the current thread user in asynchronous server.
 * 
 * @author Poh Shie Liang
 */
@Component
public class SecurityRuntime {
    private ThreadLocal<ThreadState> threadStates;

    public SecurityRuntime() {
        threadStates = new ThreadLocal<>();
    }

    @PostConstruct
    public void init() {
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
    }

    public Subject newSubject() {
        return new Subject.Builder().buildSubject();
    }

    public Subject currentSubject() {
        return SecurityUtils.getSubject();
    }

    public Subject retrieveSubject(Serializable sessionKey) {
        return new Subject.Builder().sessionId(sessionKey).buildSubject();
    }

    public void bindSubject(Subject subject) {
        ThreadState threadState = threadStates.get();
        if(threadState != null) threadState.clear();
        threadState = new SubjectThreadState(subject);
        threadState.bind();
        threadStates.set(threadState);
    }

    public void unbindSubject() {
        ThreadState threadState = threadStates.get();
        if(threadState != null) threadState.clear();
        threadStates.set(null);
    }
}
