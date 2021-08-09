package mgivanov.qsmsa.util.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
public class GreetingControllerLoggingAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(mgivanov.qsmsa.util.annotation.LogInOutParameters)")
    public void callAtMyServicePublic() {
    }

    @Before("callAtMyServicePublic()")
    public void beforeCallAtMethod1(JoinPoint jp) {
        String args = Arrays.stream(jp.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(","));
        logger.info("Массив входящих параметров метода - " + jp.getSignature().getName() + ", args=[" + args + "]");
    }

    @AfterReturning(value = "callAtMyServicePublic()", returning = "returnValue")
    public void afterCallAt(JoinPoint jp, Object returnValue) {
        if (returnValue != null) {
            System.out.printf("Успешно выполнен метод - %s, класса- %s, с результатом выполнения - %s\n",
                    jp.getSignature().getName(),
                    jp.getSourceLocation().getWithinType().getName(),
                    returnValue);
        } else {
            System.out.printf("Успешно выполнен метод - %s, класса- %s\n",
                    jp.getSignature().getName(),
                    jp.getSourceLocation().getWithinType().getName());
        }
    }

    @AfterThrowing(value = "callAtMyServicePublic()", throwing = "exception")
    public void recordFailedExecution(JoinPoint joinPoint, Exception exception) {
        System.out.printf("Метод - %s, класса- %s, был аварийно завершен с исключением - %s\n",
                joinPoint.getSignature().getName(),
                joinPoint.getSourceLocation().getWithinType().getName(),
                exception);
    }


}
