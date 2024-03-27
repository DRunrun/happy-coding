package org.jojo.kstry.domain;

import cn.kstry.framework.core.bus.ScopeData;
import cn.kstry.framework.core.enums.ScopeTypeEnum;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.jojo.kstry.entity.*;

import java.util.List;

/**
 * 对象形式定义var数据域
 * req：保存请求传入的request对象，例子中便是指QueryScoreRequest
 * var：保存节点执行完成后产生的变量。QueryScoreVarScope实现ScopeData接口，重写getScopeDataEnum()方法返回ScopeTypeEnum.VARIABLE。是以对象的形式定义了var数据域
 * sta：和var一样也是保存节点执行完成后产生的变量。只是保存后的结果不可变。可以理解成var是HasMap，sta是ImmutableMap
 * res：保存请求传入的response对象，例子中便是指QueryScoreResponse
 * @author djq
 * @date 2024-03-26 16:08
 **/
@Data
@FieldNameConstants(innerTypeName = "F")
public class QueryScoreVarScope implements ScopeData {
    private StudentBasic studentBasic;

    private StudentPrivacy studentPrivacy;

    private String student;

    private List<StudyExperience> studyExperienceList;

    private List<Long> classIds;

    private List<ClassInfo> classInfos;

    private List<ScoreInfo> scoreInfos;
    @Override
    public ScopeTypeEnum getScopeDataEnum() {
        return ScopeTypeEnum.VARIABLE;
    }
}
