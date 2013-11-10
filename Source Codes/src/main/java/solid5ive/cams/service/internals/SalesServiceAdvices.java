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
public class SalesServiceAdvices {
    @Autowired
    private SecurityRuntime securityRuntime;

    @Pointcut("execution(* solid5ive.cams.service.contracts.SalesService.acceptOrder(..))")
    public void acceptOrderPointcut() {};

    @Around("acceptOrderPointcut()")
    public Object acceptOrderAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer stallId = (Integer)joinPoint.getArgs()[0];
        Subject subject = securityRuntime.currentSubject();
        if(subject.hasRole("STALLOWNER_" + stallId)) {
            return joinPoint.proceed();
        } else {
            throw new SecurityException("Unauthorised operation.");
        }
    }
}
