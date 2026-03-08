package com.example.reports;

public class ReportViewer {

    private final Report report;

    public ReportViewer(Report report) {
        this.report = report;
    }

    public void open(User user) {
        report.display(user);
    }
}
