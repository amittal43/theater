package com.jpmc.theater;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TheaterUtilTest {

  private Showing showing;
  private double discount;

  public TheaterUtilTest(Showing showing, double discount) {
    this.showing = showing;
    this.discount = discount;
  }

  @Test
  public void testCalculateDiscount() {
    assertEquals(discount, TheaterUtil.calculateDiscount(showing), 0.1);
  }

  @Parameterized.Parameters
  public static Collection input() {
    LocalDateProvider provider = new LocalDateProvider();
    Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, SpecialCodes.TWENTY_PERCENT_OFF);
    Movie spiderMan2 = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, SpecialCodes.NONE);
    Showing showing1 =
        new Showing(spiderMan, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 30)));
    Showing showing2 =
        new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 30)));
    Showing showing3 =
        new Showing(spiderMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 30)));
    Showing showing4 =
        new Showing(spiderMan2, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 30)));
    Showing showing5 =
        new Showing(spiderMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 30)));
    Showing showing6 =
        new Showing(spiderMan2, 3, LocalDateTime.of(LocalDate.of(2022, 1, 07), LocalTime.of(16, 30)));
    return Arrays.asList(new Object[][] {
        {showing1, 3}, // $3 off
        {showing2, 2}, //$2 off
        {showing3, 2}, // 20% off
        {showing4, 0}, // no discount
        {showing5, 2.5}, //25% due to time of day
        {showing6, 1} // 7th day of month
    });
  }
}