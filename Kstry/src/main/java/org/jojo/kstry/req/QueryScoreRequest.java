package org.jojo.kstry.req;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

/**
 * 入参
 *
 * @author djq
 * @date 2024-03-26 15:55
 **/
@Data
@FieldNameConstants(innerTypeName = "F")
public class QueryScoreRequest {
    private Long studentId;

    private boolean needScore;
}
