package com.cp.project.controller;

import com.cp.project.entity.AttendanceRecord;
import com.cp.project.entity.User;
import com.cp.project.repository.UserRepository;
import com.cp.project.service.AttendanceService;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AttendanceService attendanceService;
    public AuthController(UserRepository userRepository , PasswordEncoder passwordEncoder , AttendanceService attendanceService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.attendanceService = attendanceService;
    }
    
    @GetMapping("/")
    public String root() {
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try {
            // ตรวจสอบว่ามี email ซ้ำหรือยัง
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                redirectAttributes.addFlashAttribute("errorMessage", "Email already exists!");
                return "redirect:/register"; // กลับหน้า register
            }

            if(user.getRole() == null || user.getRole().isEmpty()) {
                user.setRole("USER");
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);

            redirectAttributes.addFlashAttribute("successMessage", "Register successful! Please login.");
            return "redirect:/login";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Something went wrong: " + e.getMessage());
            return "redirect:/register";
        }
    }


    @GetMapping("/login")
    public String showLoginForm(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            // ผู้ใช้ล็อกอินแล้ว
            if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                return "redirect:/admin/dashboard";
            } else {
                return "redirect:/user/dashboard";
            }
        }
        return "login"; // ถ้ายังไม่ login
    }


    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication) {
        if (authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/user/dashboard";
        }
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin_dashboard"; // ชื่อไฟล์ template admin_dashboard.html
    }

    @GetMapping("/user/dashboard")
    public String userDashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        // ดึง User entity จาก username
        User user = userRepository.findByEmail(userDetails.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

        model.addAttribute("username", user.getUsername());

        List<AttendanceRecord> records = attendanceService.getRecordsForUser(user);
        model.addAttribute("attendanceRecords", records);

        // ดึงล่าสุด
        if (!records.isEmpty()) {
            AttendanceRecord last = records.get(0);
            model.addAttribute("lastClockIn", last.getClockIn());
            model.addAttribute("lastClockOut", last.getClockOut());
            model.addAttribute("status", last.getStatus());
        } else {
            model.addAttribute("lastClockIn", "-");
            model.addAttribute("lastClockOut", "-");
            model.addAttribute("status", "Absent");
        }
        return "user_dashboard";
    }

    
    

}
