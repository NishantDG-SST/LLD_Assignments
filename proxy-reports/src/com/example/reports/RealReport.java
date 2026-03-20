package com.example.reports;

public class RealReport implements Report {

    private final String filename;
    private String content;

    public RealReport(String filename) {
        this.filename = filename;
    }

    @Override
    public void display(User user) {
        System.out.println("Displaying report for " + user.getName());
        System.out.println(content);
    }
}
