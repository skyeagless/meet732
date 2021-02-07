package com.skyeagle.meeting.utils;

import com.skyeagle.meeting.VO.RecordVO;
import com.skyeagle.meeting.entity.ApplicationRecord;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @projectName:meeting
 * @packageName:com.skyeagle.meeting.utils
 * @className:Data2Record
 * @Description:[一句话描述该类的功能]
 * @Author:lvyang
 * @CreateDate:2021-2-2 13:06
 */

public class Transfer {
    //将数据库的Date字段转换成RecordVO
    public static RecordVO Data2Record(ApplicationRecord apdata){
        RecordVO recordVO = new RecordVO();
        recordVO.setId(apdata.getApplicationId());
        recordVO.setName(apdata.getName());
        recordVO.setReason(apdata.getReason());
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        recordVO.setDate(formatter1.format(apdata.getStartTime()));
        SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
        recordVO.setStartTime(formatter2.format(apdata.getStartTime()));
        recordVO.setEndTime(formatter2.format(apdata.getEndTime()));
        return recordVO;
    }
    //反之
    public static ApplicationRecord Record2Data(RecordVO record) throws ParseException {
        ApplicationRecord applicationRecord = new ApplicationRecord();
        applicationRecord.setName(record.getName());
        applicationRecord.setReason(record.getReason());
        SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        applicationRecord.setStartTime(formatter3.parse(record.getDate()+" "+record.getStartTime()));
        applicationRecord.setEndTime(formatter3.parse(record.getDate()+" "+record.getEndTime()));
        return applicationRecord;
    }
}
