package com.jpmc.theater;

public class Reservation {
    private Customer customer;
    private Showing showing;
    private int audienceCount;
    private double totalFee;

    public Reservation(Customer customer, Showing showing, int audienceCount, double totalFee) {
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
        this.totalFee = totalFee;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public int getAudienceCount() {
        return audienceCount;
    }

    public Showing getShowing() {
        return showing;
    }

    public Customer getCustomer() {
        return customer;
    }
}