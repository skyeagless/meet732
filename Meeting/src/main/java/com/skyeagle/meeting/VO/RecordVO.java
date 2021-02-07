package com.skyeagle.meeting.VO;

import lombok.Data;

/**
 * @projectName:meeting
 * @packageName:com.skyeagle.meeting.VO
 * @className:RecordVO
 * @Description:[一句话描述该类的功能]
 * @Author:lvyang
 * @CreateDate:2021-2-2 13:00
 */
@Data
public class RecordVO {
    private Integer id;
    //申请人
    private String name;
    //申请理由
    private String reason;
    //2016-05-01
    private String date;
    //08:30:00
    private String startTime;
    //09:00:00
    private String endTime;
}
