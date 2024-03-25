package org.jojo.thinking.in.spring.ioc.overview.dependency.injection;

import org.jojo.thinking.in.spring.ioc.overview.annotaion.Super;
import org.jojo.thinking.in.spring.ioc.overview.domain.User;
import org.jojo.thinking.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 *
 * 依赖注入示例
 * @author djq
 * @date 2024-03-20 16:33
 **/
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        // 配置xml文件
        // 启动spring 应用上下文
//        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INFO/dependency-injection-context.xml");
        ApplicationContext beanFactory = new ClassPathXmlApplicationContext("META-INFO/dependency-injection-context.xml");
        // 依赖来源一：自定义的Bean
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
//        System.out.println("依赖注入:"+userRepository.getUsers());
        // 依赖来源二： 依赖注入（内建依赖）
        System.out.println(userRepository.getBeanFactory()); // 依赖注入 容器依赖

        ObjectFactory objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject()==beanFactory);

        // 依赖来源三：容器内建 Bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("获取 Environment 类型的Bean:"+environment);
//        System.out.println(beanFactory.getBean(BeanFactory.class)); // 依赖查找
        whoIsIocContainer(userRepository,beanFactory);
    }

    private static void whoIsIocContainer(UserRepository userRepository, ApplicationContext beanFactory){
        // 这表达式为什么不会成立
        // ApplicationContext 是 BeanFactory 的子接口
        // BeanFactory 是 Spring 底层Ioc容器
        // ApplicationContext 是具备应用特性的BeanFactory 超集
        System.out.println(userRepository.getBeanFactory() == beanFactory);
    }
}
