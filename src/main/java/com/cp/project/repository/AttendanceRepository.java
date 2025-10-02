package com.cp.project.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cp.project.entity.AttendanceRecord;
import com.cp.project.entity.User;

public interface AttendanceRepository extends JpaRepository<AttendanceRecord, Long> {
	List<AttendanceRecord> findByUserOrderByDateDesc(User user);
    Optional<AttendanceRecord> findTopByUserAndDateOrderByIdDesc(User user, LocalDate date);
}
