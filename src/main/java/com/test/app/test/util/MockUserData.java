package com.test.app.test.util;

import com.test.app.test.entity.User;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MockUserData {

    public static List<User> getMockUsers() {
        List<User> userList = new ArrayList<>();

        Calendar cal = Calendar.getInstance();

        // 1. Admin - Kasun Perera
        cal.set(2025, Calendar.JANUARY, 15);
        userList.add(User.builder()
                .firstName("Kasun")
                .lastName("Perera")
                .userName("kasun.p")
                .email("kasun.perera@ezyshop.lk")
                .phoneNumber("0771234567")
                .userId("USR-2025-001")
                .startDate(cal.getTime())
                .contractDuration(36L) // 3 Years
                .password("$2a$12$eImiTXuWVxjMv...") // Mock hashed pass
                .maxLoginAttempts(3)
                .LockoutDuration(15)
                .role("Admin")
                .status("Active")
                .build());

        // 2. Manager - Dilini Silva
        cal.set(2025, Calendar.MARCH, 1);
        userList.add(User.builder()
                .firstName("Dilini")
                .lastName("Silva")
                .userName("dilini.s")
                .email("dilini.silva@ezyshop.lk")
                .phoneNumber("0719876543")
                .userId("USR-2025-002")
                .startDate(cal.getTime())
                .contractDuration(24L) // 2 Years
                .password("$2a$12$kYg821HsnMk...")
                .maxLoginAttempts(3)
                .LockoutDuration(15)
                .role("Manager")
                .status("Active")
                .build());

        // 3. Cashier - Mohamed Rizwan
        cal.set(2026, Calendar.FEBRUARY, 10);
        userList.add(User.builder()
                .firstName("Mohamed")
                .lastName("Rizwan")
                .userName("rizwan.m")
                .email("m.rizwan@ezyshop.lk")
                .phoneNumber("0764567890")
                .userId("USR-2026-045")
                .startDate(cal.getTime())
                .contractDuration(12L) // 1 Year
                .password("$2a$12$xXyZ9871la...")
                .maxLoginAttempts(5)
                .LockoutDuration(30)
                .role("Cashier")
                .status("Active")
                .build());

        // 4. Cashier (Suspended Account) - Thilina Jayasinghe
        cal.set(2024, Calendar.JUNE, 20);
        userList.add(User.builder()
                .firstName("Thilina")
                .lastName("Jayasinghe")
                .userName("thilina.j")
                .email("thilina.j@ezyshop.lk")
                .phoneNumber("0783216549")
                .userId("USR-2024-112")
                .startDate(cal.getTime())
                .contractDuration(12L)
                .password("$2a$12$pPlm0987qW...")
                .maxLoginAttempts(3)
                .LockoutDuration(15)
                .role("Cashier")
                .status("Inactive")
                .build());

        // 5. Inventory Auditor - Shanika Fernando
        cal.set(2025, Calendar.NOVEMBER, 05);
        userList.add(User.builder()
                .firstName("Shanika")
                .lastName("Fernando")
                .userName("shanika.f")
                .email("shanika.f@ezyshop.lk")
                .phoneNumber("0727778889")
                .userId("USR-2025-098")
                .startDate(cal.getTime())
                .contractDuration(6L) // 6 Months short contract
                .password("$2a$12$vBnM5643xZ...")
                .maxLoginAttempts(3)
                .LockoutDuration(20)
                .role("Auditor")
                .status("Active")
                .build());

        return userList;
    }
}
