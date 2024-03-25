package org.jojo.thinking.in.spring.ioc.overview.dependency.lookup;

import org.jojo.thinking.in.spring.ioc.overview.annotaion.Super;
import org.jojo.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 *
 * 依赖查找示例
 * 1、通过名称的方式来查找
 * 2、通过类型的方式来查找
 * 3、通过注解的方式查找
 * @author djq
 * @date 2024-03-20 16:33
 **/
public class DependencyLookupDemo {

    public static void main(String[] args) {
        // 配置xml文件
        // 启动spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INFO/dependency-lookup-context.xml");
        lookupInRealTime(beanFactory);
        lookupLazy(beanFactory);
        //通过类型的方式查找
        lookupByType(beanFactory);
        lookupCollectionByType(beanFactory);
        // 通过注解查找
        lookupByAnnotationType(beanFactory);
    }

    private static void lookupByAnnotationType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = (Map)listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("查找标注@Super 所有的 User 集合对象:"+users);
        }
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("通过类型查找集合:"+users);
        }
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("通过类型查找单一对象:"+user);
    }

    /**
     * 实时查找
     */
    private static void lookupInRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找:"+user);
    }
    /**
     * 延时查找
     */
    private static void lookupLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延时查找:"+user);
    }
}
