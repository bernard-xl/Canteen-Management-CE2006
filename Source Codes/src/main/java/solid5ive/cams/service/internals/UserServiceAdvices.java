package solid5ive.cams.service.internals;

import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import solid5ive.cams.security.SecurityRuntime;

@Aspect
@Component
public class UserServiceAdvices {
    @Autowired
    private SecurityRuntime securityRuntime;

    @Pointcut("execution(* solid5ive.cams.service.contracts.UserService.*(..))")
    public void userServicePointcut() {}
   
    @Pointcut("execution(* solid5ive.cams.service.contracts.UserService.transferBetweenAccount(..))")
    public void transferPointcut() {}

    @Around("userServicePointcut() && !transferPointcut()")
    public Object userServiceAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Subject subject = securityRuntime.currentSubject();
        if(subject.hasRole("ADMIN")) {
            return joinPoint.proceed();
        } else {
            throw new SecurityException("Unauthorised operation.");
        }
    }

    @Around("transferPointcut()")
    public Object transferAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Subject subject = securityRuntime.currentSubject();
        if(subject.isAuthenticated()) {
            return joinPoint.proceed();
        } else {
            throw new SecurityException("Unauthorised operation.");
        }
    }
}
