package com.test.app.test.util;

import com.test.app.test.entity.User;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MockUserData {

    public static List<User> getMockUsers() {
        List<User> userList = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        Calendar modCal = Calendar.getInstance();

        cal.set(2025, Calendar.JANUARY, 15, 8, 30, 0);
        userList.add(User.builder()
                .firstName("Kasun")
                .lastName("Perera")
                .userName("kasun.p")
                .email("kasun.perera@ezyshop.lk")
                .phoneNumber("0771234567")
                .userId("USR-20254")
                .startDate(cal.getTime())
                .contractDuration(36L) // 3 Years
                .password("$2a$12$eImiTXuWVxjMv...")
                .maxLoginAttempts(3)
                .LockoutDuration(15)
                .role("Admin")
                .status("Active")
                .createdBy("System")
                .createdAt(cal.getTime())
                .modifiedBy("-")
                .modifiedAt(null)
                .build());

        cal.set(2025, Calendar.MARCH, 1, 9, 15, 0);
        modCal.set(2025, Calendar.DECEMBER, 12, 14, 45, 0);
        userList.add(User.builder()
                .firstName("Dilini")
                .lastName("Silva")
                .userName("dilini.s")
                .email("dilini.silva@ezyshop.lk")
                .phoneNumber("0719876543")
                .userId("USR-20252")
                .startDate(cal.getTime())
                .contractDuration(24L)
                .password("$2a$12$kYg821HsnMk...")
                .maxLoginAttempts(3)
                .LockoutDuration(15)
                .role("Manager")
                .status("Active")
                .createdBy("Admin")
                .createdAt(cal.getTime())
                .modifiedBy("Admin")
                .modifiedAt(modCal.getTime())
                .build());

        cal.set(2026, Calendar.FEBRUARY, 10, 11, 0, 0);
        userList.add(User.builder()
                .firstName("Mohamed")
                .lastName("Rizwan")
                .userName("rizwan.m")
                .email("m.rizwan@ezyshop.lk")
                .phoneNumber("0764567890")
                .userId("USR-20265")
                .startDate(cal.getTime())
                .contractDuration(12L)
                .password("$2a$12$xXyZ9871la...")
                .maxLoginAttempts(5)
                .LockoutDuration(30)
                .role("Cashier")
                .status("Active")
                .createdBy("Admin")
                .createdAt(cal.getTime())
                .modifiedBy("-")
                .modifiedAt(null)
                .build());

        cal.set(2024, Calendar.JUNE, 20, 10, 0, 0);
        modCal.set(2026, Calendar.JANUARY, 5, 16, 20, 0);
        userList.add(User.builder()
                .firstName("Thilina")
                .lastName("Jayasinghe")
                .userName("thilina.j")
                .email("thilina.j@ezyshop.lk")
                .phoneNumber("0783216549")
                .userId("USR-20242")
                .startDate(cal.getTime())
                .contractDuration(12L)
                .password("$2a$12$pPlm0987qW...")
                .maxLoginAttempts(3)
                .LockoutDuration(15)
                .role("Cashier")
                .status("Inactive")
                .createdBy("System")
                .createdAt(cal.getTime())
                .modifiedBy("Admin")
                .modifiedAt(modCal.getTime())
                .build());

        cal.set(2025, Calendar.NOVEMBER, 5, 15, 40, 0);
        userList.add(User.builder()
                .firstName("Shanika")
                .lastName("Fernando")
                .userName("shanika.f")
                .email("shanika.f@ezyshop.lk")
                .phoneNumber("0727778889")
                .userId("USR-20258")
                .startDate(cal.getTime())
                .contractDuration(6L)
                .password("$2a$12$vBnM5643xZ...")
                .maxLoginAttempts(3)
                .LockoutDuration(20)
                .role("Auditor")
                .status("Active")
                .createdBy("Admin")
                .createdAt(cal.getTime())
                .modifiedBy("-")
                .modifiedAt(null)
                .build());

        return userList;
    }
}