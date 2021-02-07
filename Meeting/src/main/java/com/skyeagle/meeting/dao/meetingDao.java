package com.skyeagle.meeting.dao;

import com.skyeagle.meeting.entity.ApplicationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @projectName:meeting
 * @packageName:com.skyeagle.meeting.dao
 * @className:meetingDao
 * @Description:[一句话描述该类的功能]
 * @Author:lvyang
 * @CreateDate:2021-2-1 19:54
 */
public interface meetingDao extends JpaRepository<ApplicationRecord,Integer> {
    @Query(value="select * from application_record ORDER BY start_time DESC LIMIT 10",nativeQuery=true)
    List<ApplicationRecord> get10Record();
}
