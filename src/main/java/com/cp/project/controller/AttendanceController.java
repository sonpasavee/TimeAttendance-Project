package com.cp.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.cp.project.entity.User;
import com.cp.project.repository.UserRepository;
import com.cp.project.service.AttendanceService;

@Controller
public class AttendanceController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/clockin")
    @ResponseBody
    public Map<String, String> clockIn(@AuthenticationPrincipal UserDetails userDetails,
                                       @RequestParam double latitude,
                                       @RequestParam double longitude) {
        Map<String, String> response = new HashMap<>();
        try {
            User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
            attendanceService.clockIn(user, latitude, longitude);
            response.put("success", "Clock In successful!");
        } catch (RuntimeException e) {
            response.put("error", e.getMessage());
        }
        return response;
    }

    @PostMapping("/clockout")
    @ResponseBody
    public Map<String, String> clockOut(@AuthenticationPrincipal UserDetails userDetails,
                                        @RequestParam double latitude,
                                        @RequestParam double longitude) {
        Map<String, String> response = new HashMap<>();
        try {
            User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
            attendanceService.clockOut(user, latitude, longitude);
            response.put("success", "Clock Out successful!");
        } catch (RuntimeException e) {
            response.put("error", e.getMessage());
        }
        return response;
    }

}
