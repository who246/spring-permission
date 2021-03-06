package com.test.repository.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect("")
@Component
public class LogAop {
    @Pointcut("execution (public * com.lance.repository.imp..*.*(..))")  
    private void pointcut(){System.out.println("sdsd");}
    @Before(value="pointcut()")
    private void before(JoinPoint jp){
        
        System.out.println("调用前"+jp.getSignature().getName()+";参数：");
        java.lang.Object[] objects = jp.getArgs();
        for (Object object : objects) {
            System.out.println(object);
        }
    }
    @AfterReturning(value="pointcut()")
    private void afterReturning(JoinPoint jp){
        
        System.out.println("调用后:"+jp.getSignature().getName());
    }
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("已经记录下操作日志@Around 方法执行前");
        Object object = pjp.proceed();
        System.out.println("已经记录下操作日志@Around 方法执行后");
        return object;
    }
    @Before(value="execution (public * com.lance.repository.imp.*.findByUserName5(java.lang.String) ) && args(username)",argNames="username")
    private void afterReturningWithArg(JoinPoint jp,String username){
        
        System.out.println("调用findByUserName5的参数:"+username);
    }
}
