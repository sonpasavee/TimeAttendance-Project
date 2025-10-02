package com.cp.project.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cp.project.entity.AttendanceRecord;
import com.cp.project.entity.User;
import com.cp.project.repository.AttendanceRepository;

@Service
public class AttendanceService {
	
	private final AttendanceRepository repository;

    public AttendanceService(AttendanceRepository repository) {
        this.repository = repository;
    }
    
    @Transactional
    public AttendanceRecord clockIn(User user, double latitude, double longitude) {
//    	ดึงข้อมูลมาเช็คก่อนว่าclock inไปยัง
    	Optional<AttendanceRecord> todayRecordOpt = repository
    	        .findTopByUserAndDateOrderByIdDesc(user, LocalDate.now());
    	if(todayRecordOpt.isPresent()) {
    		AttendanceRecord todayRecord = todayRecordOpt.get();
    		if(todayRecord.getClockIn() != null && todayRecord.getClockOut() == null) {
                throw new RuntimeException("You already clocked in today!");
            }
            if(todayRecord.getClockOut() != null) {
                throw new RuntimeException("You already finished work today!");
            }
    	}
    	
    	
        AttendanceRecord record = new AttendanceRecord();
        record.setUser(user);
        record.setUsername(user.getUsername());
        record.setDate(LocalDate.now());
        record.setClockIn(LocalTime.now());
        record.setLatitude(latitude);
        record.setLongitude(longitude);
        record.setStatus("Working");
        return repository.save(record);
    }

    @Transactional
    public AttendanceRecord clockOut(User user, double latitude, double longitude) {
    	AttendanceRecord record = repository
    	        .findTopByUserAndDateOrderByIdDesc(user, LocalDate.now())
    	        .orElseThrow(() -> new RuntimeException("No clock-in found for today"));

    	    if(record.getClockOut() != null) {
    	        throw new RuntimeException("You already clocked out today!");
    	    }
        record.setClockOut(LocalTime.now());
        record.setLatitude(latitude);
        record.setLongitude(longitude);
        return repository.save(record);
    }

    public List<AttendanceRecord> getRecordsForUser(User user) {
        return repository.findByUserOrderByDateDesc(user);
    }

}
