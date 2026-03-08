package com.example.reports;

public class ReportProxy implements Report {

    private final String filename;
    private final String requiredRole;
    private RealReport realReport;

    public ReportProxy(String filename, String requiredRole) {
        this.filename = filename;
        this.requiredRole = requiredRole;
    }

    @Override
    public void display(User user) {

        System.out.println("Access check for user: " + user.getName());

        if (!user.getRole().equals(requiredRole)) {
            System.out.println("Access Denied!");
            return;
        }

        if (realReport == null) {
            System.out.println("Lazy loading real report...");
            realReport = new RealReport(filename);
        } else {
            System.out.println("Using cached report.");
        }

        realReport.display(user);
    }
}
