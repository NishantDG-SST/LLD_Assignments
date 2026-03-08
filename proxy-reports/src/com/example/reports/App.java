package com.example.reports;

public class App {

    public static void main(String[] args) {

        User admin = new User("Kshitij", "ADMIN");
        User student = new User("Ayaan", "STUDENT");

        Report secureReport = new ReportProxy("placement-report.txt", "ADMIN");

        ReportViewer viewer = new ReportViewer(secureReport);

        System.out.println("\n-- Student Trying --");
        viewer.open(student);

        System.out.println("\n-- Admin First Attempt --");
        viewer.open(admin);

        System.out.println("\n-- Admin's Second Attempt (Should Use Cache) --");
        viewer.open(admin);
    }
}
