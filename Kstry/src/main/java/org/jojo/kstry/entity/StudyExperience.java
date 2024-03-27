package org.jojo.kstry.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学年信息
 *
 * @author djq
 * @date 2024-03-26 15:44
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyExperience {
    private Long studentId;

    private Long classId;

    private String studyYear;
}
