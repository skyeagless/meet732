package com.skyeagle.meeting.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @projectName:meeting
 * @packageName:com.skyeagle.meeting.entity
 * @className:ApplicationRecord
 * @Description:一条申请记录所包含的部分
 * @Author:lvyang
 * @CreateDate:2021-2-1 17:10
 */
@Entity
@Data
public class ApplicationRecord {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Integer applicationId;
    //申请人
    private String name;
    //申请理由
    private String reason;
    //起始时间
    private Date startTime;
    //终止时间
    private Date endTime;
}
