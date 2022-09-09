package com.jpmc.theater;

import java.time.LocalDateTime;

public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    public double calculateFee() {
        return movie.getTicketPrice() - TheaterUtil.calculateDiscount(this);
    }

    @Override
    public String toString() {
        return sequenceOfTheDay + ": " + showStartTime + " " + movie.getTitle() +
            " " + TheaterUtil.humanReadableFormat(movie.getRunningTime()) + " $" + calculateFee();
    }
}
