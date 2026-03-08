package com.example.reports;

public class RealReport implements Report {

    private final String filename;
    private String content;

    public RealReport(String filename) {
        this.filename = filename;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading report from disk: " + filename);
        try {
            Thread.sleep(2000); // expensive loading
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        content = "Confidential Report Content of " + filename;
    }

    @Override
    public void display(User user) {
        System.out.println("Displaying report for " + user.getName());
        System.out.println(content);
    }
}
