package org.jojo.kstry.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分数信息
 *
 * @author djq
 * @date 2024-03-26 15:44
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoreInfo {
    private int score;

    private Long studentId;

    private String studyYear;

    private String course;

    private Long classId;

    private ClassInfo classInfo;
}
