package com.douzi.greenhouse_system.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MiddleStudent extends StudentModel{

    private Integer grade;

    private Integer score;

    private String schlool;

//    @Override
//    public String toString() {
//        return ToStringBuilder.reflectionToString(this);
//    }

}
