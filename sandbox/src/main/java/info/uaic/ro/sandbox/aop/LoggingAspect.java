package info.uaic.ro.sandbox.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

    @Around("execution(* info.uaic.ro.sandbox..*(..))")
    public Object logFunctionCall(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();

        log.info("Entry: {}.{}() with args: {}", className, methodName, methodArgs);

        Object result = joinPoint.proceed();

        log.info("Exit: {}.{}() with result: {}", className, methodName, result);

        return result;
    }
}
