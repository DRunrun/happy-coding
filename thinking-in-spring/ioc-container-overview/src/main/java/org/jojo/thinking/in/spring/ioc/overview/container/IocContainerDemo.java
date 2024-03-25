package org.jojo.thinking.in.spring.ioc.overview.container;

import org.jojo.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * Ioc 容器示例
 *
 * @author djq
 * @date 2024-03-25 17:19
 **/
public class IocContainerDemo {
    public static void main(String[] args) {
        // 创建BeanFactory容器
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        // xml 配置文件 CalssPath 路径
        String location = "classpath:/META-INFO/dependency-lookup-context.xml";
        // 加载配置
        int beanDefinitionsCount =  reader.loadBeanDefinitions(location);
        System.out.println("Bean 定义加载数量："+beanDefinitionsCount);
        // 依赖查找集合对象
        lookupCollectionByType(defaultListableBeanFactory);
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("通过类型查找集合:"+users);
        }
    }
}
