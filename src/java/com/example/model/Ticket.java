package com.example.model;

public class Ticket {
    private int ticketId;
    private int customerId;
    private int storeId;
    private String issue;
    private String status;

    public Ticket(int ticketId, int customerId, int storeId, String issue, String status) {
        this.ticketId = ticketId;
        this.customerId = customerId;
        this.storeId = storeId;
        this.issue = issue;
        this.status = status;
    }

    // Getters and setters

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getstoreId() {
        return storeId;
    }

    public void setShopId(int storeId) {
        this.storeId = storeId;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
