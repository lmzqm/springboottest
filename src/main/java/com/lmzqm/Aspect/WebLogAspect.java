package com.lmzqm.Aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 这里是针对AOP的相关操作
 *http://www.jianshu.com/p/b1d5485ca6ed
 * 首先添加AOP的依赖：
 * <dependency>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-starter-aop</artifactId>
 </dependency>
 *
 *分析下WEB层的日志切面
 *
 * 使用@Aspect 注解将一个JAva的类定义为切面类
 *
 * 使用@PointCut定义一个切入点 可以是一个规则表达式 比如某个Package下的所有函数，也可以是一个注解
 *
 * 根据需要在切入点的不同位置切入不同的内容
 *
 * 使用 @Before 在切入点开始处切入内容
 * 使用 @After 在切入点结尾处切入内容
 * 使用 @AfterReturning 在切入点Return 内容之后切入内容（可以用来处理返回值做一些加工处理）
 * 使用 @Around 在切入点前后切入内容，并自己控制合适执行切入点自身的内容
 * 使用 @AfterThrowing用来处理当切入内容部分抛出异常之后的处理逻辑
 *
 *
 *AOP切面中的同步问题
 * ThreadLocal
 *
 *  AOP切面的优先级
 *
 *  由于通过AOP的实现，程序得到了很好的解耦，但是也会带来一些问题
 *  比如我们有多个切面操作的时候，需要处理切面处理顺序的问题
 *  所以需要定义切面的优先级 通过@Order(i) 注解来标识切面的优先级。i的值越小，优先级越高
 *
 *  现在对优先级进行总结
 *  在切入点前操作，按order的值由小到大执行
 *  在切入点后操作，按order的值由大到小执行
 *
 *
 *  execution(annotation? modifiers-pattern? return-type-pattern declaring-type-pattern? name-pattern(param-pattern)throws-pattern?)
 即注解？ 访问修饰？ 返回类型 类名？ 方法名(方法参数) 异常列表？


 *
 * returning type pattern,name pattern, and parameters pattern是必须的.即返回值类型，方法名，方法参数是必须指定的。其余可选
 ret-type-pattern:可以为*表示任何返回值,全路径的类名等.
 name-pattern:指定方法名,*代表所以,set*,代表以set开头的所有方法.
 parameters pattern:指定方法参数(声明的类型),(..)代表所有参数,(*)代表一个参数,(*,String)代表第一个参数为任何值,第二个为String类型.

 匹配所有
 execution("* *.*(..)")
 匹配所有以set开头的方法
 execution("* *.set*(..))
 匹配指定包下所有的方法
 execution("* com.david.biz.service.impl.*(..))
 匹配指定包以及其子包下的所有方法
 execution("* com.david..*(..)")
 匹配指定包以及其子包下 参数类型为String 的方法
 execution("* com.david..*(java.lang.String))

 http://wujiu.iteye.com/blog/2162096

Mongodb对于log4j2的使用
 http://blog.csdn.net/jiongyi1/article/details/55213291
 *
 * Created by lmzqm on 2017/4/26.
 */
@Aspect
@Component
public class WebLogAspect {

    private static  final Logger logger = LogManager.getLogger(WebLogAspect.class);

    ThreadLocal<Long> startTime = new ThreadLocal<>();



    @Pointcut("execution(public * com.lmzqm.Controller.DoctorViewController.getBlogInfo(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{

        startTime.set(System.currentTimeMillis());

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =  attributes.getRequest();

        logger.info("URL:"+request.getRequestURL().toString());
        logger.info("HTTP_METHOD"+request.getMethod());
        logger.info("IP"+ request.getRemoteAddr());
        logger.info("ARGS:"+ Arrays.toString(joinPoint.getArgs()));



    }

    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfterReturning(Object ret ) throws Throwable{
        logger.info("RESPONSE:"+ret.toString());

        startTime.get();
    }


}
