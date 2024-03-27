package org.jojo.kstry.res;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.jojo.kstry.entity.ScoreInfo;
import org.jojo.kstry.entity.Student;

import java.util.List;

/**
 * 出参
 *
 * @author djq
 * @date 2024-03-26 15:57
 **/
@Data
@FieldNameConstants(innerTypeName = "F")
public class QueryScoreResponse {
    private Student student;

    private List<ScoreInfo> scoreInfos;
}
