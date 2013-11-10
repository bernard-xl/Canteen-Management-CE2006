package solid5ive.cams.service.internals;

import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import solid5ive.cams.data.entities.Stall;
import solid5ive.cams.data.entities.User;
import solid5ive.cams.data.entities.UserRole;
import solid5ive.cams.data.repositories.RoleRepository;
import solid5ive.cams.data.repositories.StallRepository;
import solid5ive.cams.data.repositories.UserRepository;
import solid5ive.cams.security.SecurityRuntime;

@Aspect
@Component
public class InfrastructureServiceAdvices {
    @Autowired
    private SecurityRuntime securityRuntime;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private StallRepository stallRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Pointcut("execution(* solid5ive.cams.service.contracts.InfrastructureService.*Canteen*(..))")
    public void canteenPointcut() {}

    @Pointcut("execution(* solid5ive.cams.service.contracts.InfrastructureService.listStalls(..))")
    public void listStallsPointcut() {}

    @Pointcut("execution(* solid5ive.cams.service.contracts.InfrastructureService.listStallByOwner(..))")
    public void stallByOwnerPointcut() {}

    @Pointcut("execution(* solid5ive.cams.service.contracts.InfrastructureService.createStall(..))")
    public void createStallPointcut() {}

    @Pointcut("execution(* solid5ive.cams.service.contracts.InfrastructureService.updateStall(..))")
    public void updateStallPointcut() {}

    @Pointcut("execution(* solid5ive.cams.service.contracts.InfrastructureService.deleteStall(..))")
    public void deleteStallPointcut() {}

    @Around("canteenPointcut() || listStallsPointcut()")
    public Object canteenOptAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Subject subject = securityRuntime.currentSubject();
        if(subject.hasRole("ADMIN")) {
            return joinPoint.proceed();
        } else {
            throw new SecurityException("Unauthorised operation.");
        }
    }

    @Around("stallByOwnerPointcut()")
    public Object listStallByOwnerAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        String loginName = (String)joinPoint.getArgs()[0];
        Subject subject = securityRuntime.currentSubject();
        if(subject.getPrincipal().equals(loginName)) {
            return joinPoint.proceed();
        } else {
            throw new SecurityException("Unauthorised operation.");
        }
    }

    @Around("createStallPointcut()")
    public Object createStallAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Subject subject = securityRuntime.currentSubject();
        if(subject.hasRole("ADMIN")) {
            Stall result = (Stall)joinPoint.proceed();
            UserRole role = new UserRole("STALLOWNER_" + result.getId());
            roleRepository.save(role);
            return result;
        } else {
            throw new SecurityException("Unauthorised operation.");
        }
    }

    @Around("updateStallPointcut()")
    public Object updateStallAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Subject subject = securityRuntime.currentSubject();
        if(subject.hasRole("ADMIN")) {
            Integer stallId = (Integer)joinPoint.getArgs()[0];
            Object result = null;
            TransactionStatus txStatus = null;
            try {
                txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

                Stall stall = stallRepository.findOne(stallId);
                User oldOwner = (stall == null)? null : stall.getOwner();

                result = joinPoint.proceed();

                stall = stallRepository.findOne(stallId);
                User newOwner = stall.getOwner();

                if(newOwner != oldOwner) {
                    UserRole role = roleRepository.findByRole("STALLOWNER_" + stallId);
                    if(oldOwner != null) {
                        oldOwner.removeRole(role);
                        userRepository.save(oldOwner);
                    }
                    if(newOwner != null) {
                        newOwner.addRole(role);
                        userRepository.save(newOwner);
                    }
                }

                transactionManager.commit(txStatus);
                return result;
            } catch(Exception e) {
                if(txStatus != null) transactionManager.rollback(txStatus);
                throw e;
            }
        } else {
            throw new SecurityException("Unauthorised operation.");
        }
    }

    @Around("deleteStallPointcut()")
    public Object deleteStallAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Subject subject = securityRuntime.currentSubject();
        if(subject.hasRole("ADMIN")) {
            Integer stallId = (Integer)joinPoint.getArgs()[0];
            Stall stall = stallRepository.findOne(stallId);
            User user = stall.getOwner();
            Object result = joinPoint.proceed();
            UserRole role = roleRepository.findByRole("STALLOWNER_" + stallId);
            if(user != null) user.removeRole(role);
            roleRepository.delete(role);
            return result;
        } else {
            throw new SecurityException("Unauthorised operation.");
        }
    }
}
