package solid5ive.cams.service.internals;

import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import solid5ive.cams.data.entities.Menu;
import solid5ive.cams.data.repositories.MenuRepository;
import solid5ive.cams.security.SecurityRuntime;

@Aspect
@Component
public class MenuServiceAdvices {
    @Autowired
    private SecurityRuntime securityRuntime;

    @Autowired
    private MenuRepository menuRepository;

    @Pointcut("execution(* solid5ive.cams.service.contracts.MenuService.listMenu(..))")
    public void listMenuPointcut() {}

    @Pointcut("execution(* solid5ive.cams.service.contracts.MenuService.createMenu(..))")
    public void createMenuPointcut() {}

    @Pointcut("execution(* solid5ive.cams.service.contracts.MenuService.updateMenu(..))")
    public void updateMenuPointcut() {}

    @Pointcut("execution(* solid5ive.cams.service.contracts.MenuService.deleteMenu(..))")
    public void deleteMenuPointcut() {}

    @Around("listMenuPointcut() || createMenuPointcut()")
    public Object stallIdCheckAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer stallId = (Integer)joinPoint.getArgs()[0];
        Subject subject = securityRuntime.currentSubject();
        if(subject.hasRole("ADMIN") ||
           subject.hasRole("STALLOWNER_" + stallId)) {
           return joinPoint.proceed();
        } else {
            throw new SecurityException("Unauthorised operation.");
        }
    }

    @Around("updateMenuPointcut() || deleteMenuPointcut()")
    public Object menuIdCheckAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer menuId = (Integer)joinPoint.getArgs()[0];
        Subject subject = securityRuntime.currentSubject();
        Menu menu = menuRepository.findOne(menuId);
        if(menu != null) {
            if(subject.hasRole("ADMIN") ||
               subject.hasRole("STALLOWNER_" + menu.getStall().getId())) {
                return joinPoint.proceed();
            } else {
                throw new SecurityException("Unauthorised operation.");
            }
        } else {
            return joinPoint.proceed();
        }
    }
}
