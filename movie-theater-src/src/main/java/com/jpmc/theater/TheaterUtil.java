package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class TheaterUtil {

  public static double calculateDiscount(Showing showing) {
    double percentDiscount = 0;
    Movie movie = showing.getMovie();
    int showSequence = showing.getSequenceOfTheDay();

    // if we have time based discount, don't need to check for special one since it's lesser
    LocalDateTime afterNoonDiscountTime = LocalDateTime.of(showing.getStartTime().toLocalDate(), LocalTime.of(16, 1));
    if (showing.getStartTime().getHour() >= 11 && (showing.getStartTime().isBefore(afterNoonDiscountTime))) {
      percentDiscount = movie.getTicketPrice() * 0.25; // 25% discount for afternoon movie
    }  else if (movie.getSpecialCode().equals(SpecialCodes.TWENTY_PERCENT_OFF)) {
      percentDiscount = movie.getTicketPrice() * 0.2;  // 20% discount for special movie
    }

    double dateTimeDiscount = 0;
    if (showSequence == 1) {
      dateTimeDiscount = 3; // $3 discount for 1st show
    } else if (showSequence == 2) {
      dateTimeDiscount = 2; // $2 discount for 2nd show
    } else if (showing.getStartTime().getDayOfMonth() == 7) {
      dateTimeDiscount = 1; // $1 discount for 7th day
    }
    // biggest discount wins
    return percentDiscount > dateTimeDiscount ? percentDiscount : dateTimeDiscount;
  }

  public static String humanReadableFormat(Duration duration) {
    long hour = duration.toHours();
    long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

    return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
  }

  // (s) postfix should be added to handle plural correctly
  private static String handlePlural(long value) {
    if (value == 1) {
      return "";
    }
    else {
      return "s";
    }
  }
}
