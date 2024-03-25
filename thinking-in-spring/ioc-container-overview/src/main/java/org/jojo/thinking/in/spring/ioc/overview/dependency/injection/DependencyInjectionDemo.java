package org.jojo.thinking.in.spring.ioc.overview.dependency.injection;

import org.jojo.thinking.in.spring.ioc.overview.annotaion.Super;
import org.jojo.thinking.in.spring.ioc.overview.domain.User;
import org.jojo.thinking.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INFO/dependency-injection-context.xml");
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
//        System.out.println("依赖注入:"+userRepository.getUsers());
        System.out.println(userRepository.getBeanFactory()); // 依赖注入

        ObjectFactory objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject()==beanFactory);
//        System.out.println(userRepository.getBeanFactory() == beanFactory);
//        System.out.println(beanFactory.getBean(BeanFactory.class)); // 依赖查找
    }

}
