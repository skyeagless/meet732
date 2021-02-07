package com.skyeagle.meeting.controller;

import com.skyeagle.meeting.Enum.ResponseEnum;
import com.skyeagle.meeting.VO.RecordVO;
import com.skyeagle.meeting.VO.ResponseVo;
import com.skyeagle.meeting.dao.meetingDao;
import com.skyeagle.meeting.entity.ApplicationRecord;
import com.skyeagle.meeting.utils.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @projectName:meeting
 * @packageName:com.skyeagle.meeting.controller
 * @className:meetingController
 * @Description:[一句话描述该类的功能]
 * @Author:lvyang
 * @CreateDate:2021-2-1 20:17
 */
@RestController
public class meetingController {
    @Autowired
    private meetingDao meetingDao;

    @GetMapping("/list")
    public ResponseVo get10Record(){
        return ResponseVo.success(meetingDao.get10Record().stream().map(Transfer::Data2Record).collect(Collectors.toList()));
    }

    @DeleteMapping("/del/{id}")
    public ResponseVo deleteRecord(@PathVariable Integer id){
        try{
            meetingDao.deleteById(id);
        }catch (Exception e){
            return ResponseVo.error(ResponseEnum.DELETE_ERROR,"删除失败");
        }
        return ResponseVo.success("删除成功");
    }


    //有时间冲突的时候不能加入
    @PostMapping("/create")
    public ResponseVo createRecord(@RequestBody RecordVO recordVO) {
        ApplicationRecord applicationRecord = new ApplicationRecord();
        try{
            applicationRecord = Transfer.Record2Data(recordVO);
        }catch (ParseException e){
            return ResponseVo.error(ResponseEnum.PARSE_ERROR);
        }
        //首先获得所有的Record
        List<ApplicationRecord> allRecord = meetingDao.findAll();
        boolean flag = false;
        for(ApplicationRecord record:allRecord){
            if(
                ((applicationRecord.getStartTime().compareTo(record.getStartTime()) >=0) && (applicationRecord.getStartTime().compareTo(record.getEndTime())<0)) ||
                ((applicationRecord.getEndTime().compareTo(record.getEndTime()) <=0) && (applicationRecord.getEndTime().compareTo(record.getStartTime())>0))
            ){

                flag = true;
                break;
            }
        }
        if(!flag){
            ApplicationRecord result = meetingDao.save(applicationRecord);
            Map<String,Integer> map =  new HashMap<>();
            map.put("recordId",result.getApplicationId());

            ResponseVo<Map<String,Integer>> responseVo = ResponseVo.success(map);
            responseVo.setMsg("新建预定成功");
            return responseVo;
        }else{
            return ResponseVo.error(ResponseEnum.INSERT_ERROR,"已有重复的预定！请重新插入");
        }
    }
}
