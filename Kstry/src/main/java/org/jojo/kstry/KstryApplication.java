package org.jojo.kstry;

import cn.kstry.framework.core.annotation.EnableKstry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO
 *
 * @author djq
 * @date 2024-03-26 14:57
 **/
@SpringBootApplication
@EnableKstry(bpmnPath = "./bpmn/*.bpmn,/bpmn/*.json") // 启用Kstry容器 并指定bpmn文件位置
public class KstryApplication {
    public static void main(String[] args) {
        SpringApplication.run(KstryApplication.class, args);
    }
}
