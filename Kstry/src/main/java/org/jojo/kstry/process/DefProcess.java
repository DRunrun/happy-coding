package org.jojo.kstry.process;



import cn.kstry.framework.core.component.bpmn.joinpoint.InclusiveJoinPoint;
import cn.kstry.framework.core.component.bpmn.joinpoint.ParallelJoinPoint;
import cn.kstry.framework.core.component.bpmn.link.ProcessLink;
import cn.kstry.framework.core.component.bpmn.link.StartProcessLink;
import cn.kstry.framework.core.component.expression.Exp;
import cn.kstry.framework.core.enums.ScopeTypeEnum;
import cn.kstry.framework.core.util.KeyUtil;
import org.jojo.kstry.req.QueryScoreRequest;
import org.jojo.kstry.service.StudentScoreService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author djq
 * @date 2024-03-27 15:55
 **/
@Configuration
public class DefProcess {

//    @Bean
//    public ProcessLink buildShowGoodsLink() {
//        StartProcessLink bpmnLink = StartProcessLink.build(DefProcess::buildShowGoodsLink, "展示商品详情");
//
//        // 构建一个游离的并行网关，网关标识开启异步，并且是非严格模式的并行网关（因为网关左侧的分支并不能全部到达并行网关，如果不设置非严格模式就会报错，也可用包含网关来替代将不会有这个问题）
//        ParallelJoinPoint pPoint1 = bpmnLink.parallel().openAsync().notStrictMode().build();
//
//        // 初始化基本信息
//        ProcessLink initTask = bpmnLink.nextService(StudentScoreService::initBaseInfo).customRole("goods-custom-role@goods-detail").build();
//
//        // 指向并行网关
//        initTask.nextParallel("res.img == null", pPoint1);
//
//        ProcessLink rCheck = initTask
//                // res.img != null 时校验图片
//                .nextService("res.img != null", RiskControlService::checkImg).build()
//                // 指向一个判断角色的排他网关
//                .nextExclusive().build();
//
//        // 排他网关指向并行网关
//        rCheck.nextParallel("!r:check-img@triple", pPoint1);
//
//        rCheck
//                // 三方服务统计
//                .nextService("r:check-img@triple", RiskControlService::statistics).build()
//                // 之后指向并行网关
//                .nextParallel(pPoint1);
//
//
//        // 指向排他网关
//        ProcessLink sourceCheck = pPoint1.nextExclusive().build();
//
//        // 构建一个游离的包含网关
//        InclusiveJoinPoint iPoint1 = bpmnLink.inclusive().build();
//
//        // 排他网关指向包含网关
//        sourceCheck.nextInclusive(Exp.b(e -> e.notEquals(KeyUtil.req("source"), "'app'")), iPoint1);
//
//        // 排他网关指向送运费险节点
//        sourceCheck
//                // 送运费险有可能报错，设置非严格模式，报错会忽略，会继续向下执行
//                .nextService(LogisticService::getLogisticInsurance).notStrictMode().build()
//                .nextInclusive(iPoint1);
//
//        bpmnLink.parallel().build()
//                .joinLinks(
//                        // 并行网关指向广告服务节点，广告服务节点没有对应的TaskService默认会报错，但是加上 allowAbsent() 之后就不会报错了
//                        pPoint1.nextTask("advertising", "get-advertising").name("获取广告").allowAbsent().build(),
//
//                        // 并行节点指向子流程
//                        pPoint1.nextSubProcess(DefSubProcess::buildStatisticsSubProcess).build(),
//
//                        // 并行网关指向 加载SKU信息 节点
//                        pPoint1.nextService(GoodsService::initSku).build(),
//
//                        iPoint1.nextService(ShopService::getShopInfoByGoodsId).build()
//                )
//                .nextService(GoodsService::detailPostProcess).build()
//                .end();
//        return bpmnLink;
//    }

    @Bean
    public ProcessLink studentScoreQueryProcess() {
        String instructContent = "var classIds = [];"
                + "for (var i = 0; i< kvar.studyExperienceList.length; i++)"
                + "{"
                + "    classIds.push(kvar.studyExperienceList[i].classId);"
                + "}"
                + "return JSON.stringify(classIds)";
        StartProcessLink processLink = StartProcessLink.build(DefProcess::studentScoreQueryProcess);
        InclusiveJoinPoint asyncInclusive = processLink.nextInclusive(processLink.inclusive().openAsync().build());
        InclusiveJoinPoint asyncInclusive2 = asyncInclusive
                .nextService(Exp.b(e -> e.isTrue(ScopeTypeEnum.REQUEST, QueryScoreRequest.F.needScore)), StudentScoreService::getStudyExperienceList).build()
                .nextInstruct("jscript", instructContent).name("JS脚本").property("{\"result-converter\": \"object_to_long_list\",\"return-target\": \"var.classIds\"}").build()
                .nextInclusive(processLink.inclusive().openAsync().build());

        processLink.inclusive().build().joinLinks(
                        processLink.parallel().notStrictMode().build().joinLinks(
                                asyncInclusive2.nextService(StudentScoreService::getClasInfoById).build(),
                                asyncInclusive2.nextService(StudentScoreService::getStudentScoreList).build()
                        ).nextService(StudentScoreService::assembleScoreClassInfo).build(),
                        processLink.inclusive().build().joinLinks(
                                asyncInclusive.nextService(StudentScoreService::getStudentBasic).build(),
                                asyncInclusive.nextService(StudentScoreService::getStudentPrivacy).build()
                        ).nextService(StudentScoreService::assembleStudentInfo).build()
                )
                .nextService(StudentScoreService::getQueryScoreResponse).build()
                .end();
        return processLink;
    }
}
