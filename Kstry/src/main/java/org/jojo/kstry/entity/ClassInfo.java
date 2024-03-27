package org.jojo.kstry.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 班级信息
 *
 * @author djq
 * @date 2024-03-26 15:45
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassInfo {
    private Long id;

    private String name;
}
