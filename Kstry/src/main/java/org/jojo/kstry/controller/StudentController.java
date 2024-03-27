package org.jojo.kstry.controller;

import cn.kstry.framework.core.engine.StoryEngine;
import cn.kstry.framework.core.engine.facade.ReqBuilder;
import cn.kstry.framework.core.engine.facade.StoryRequest;
import cn.kstry.framework.core.engine.facade.TaskResponse;
import cn.kstry.framework.core.enums.TrackingTypeEnum;
import cn.kstry.framework.core.monitor.MonitorTracking;
import cn.kstry.framework.core.monitor.NodeTracking;
import cn.kstry.framework.core.monitor.RecallStory;
import cn.kstry.framework.core.util.GlobalUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.jojo.kstry.domain.QueryScoreVarScope;
import org.jojo.kstry.req.QueryScoreRequest;
import org.jojo.kstry.res.QueryScoreResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author djq
 * @date 2024-03-27 17:14
 **/
@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource
    private StoryEngine storyEngine;

    @RequestMapping("")
    public R<QueryScoreResponse> studentQuery() {
        QueryScoreRequest request = new QueryScoreRequest();
        request.setStudentId(77L);
        request.setNeedScore(true);
        StoryRequest<QueryScoreResponse> fireRequest = ReqBuilder
                .returnType(QueryScoreResponse.class) // 指定返回类型
                .recallStoryHook(WebUtil::recallStoryHook) // 流程结束的回溯
                .trackingType(TrackingTypeEnum.SERVICE_DETAIL) // 指定监控类型
                .request(request) // 指定req域参数
                .varScopeData(new QueryScoreVarScope()) // 指定var域数据载体，可不指定使用默认值
                .startId("student-score-query-process") // 指定开始事件ID
                .build();
        TaskResponse<QueryScoreResponse> result = storyEngine.fire(fireRequest);
        return result.isSuccess() ? R.success(result.getResult()) : R.error(NumberUtils.toInt(result.getResultCode(), -1), result.getResultDesc());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class R<T> {
        private boolean success;

        private Integer code;

        private String msg;

        private T data;
        public static  <D> R<D> success(D data) {
            R<D> res = new R<>();
            res.setSuccess(true);
            res.setCode(0);
            res.setMsg("success");
            res.setData(data);
            return res;
        }

        public static  <D> R<D> error(int code, String desc) {
            R<D> res = new R<>();
            res.setCode(code);
            res.setMsg(desc);
            res.setSuccess(false);
            return res;
        }
    }

    public static class WebUtil {
        public static void recallStoryHook(RecallStory recallStory) {
            MonitorTracking monitorTracking = recallStory.getMonitorTracking();
            List<NodeTracking> storyTracking = monitorTracking.getStoryTracking();
            List<String> collect = storyTracking.stream().map(nt -> GlobalUtil.format("{}({}ms)", nt.getNodeName(), nt.getSpendTime())).collect(Collectors.toList());
            log.info("Story startId: {}, service node spend list: {}", recallStory.getStartId(), String.join(",", collect));
        }
    }
}
