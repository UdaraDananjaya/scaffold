package lk.gtsactive.scaffold.util.time;

import java.time.*;
import java.time.format.DateTimeFormatter;

public final class TimeUtils {

    public static long currentEpochMillis() {
        return Instant.now().toEpochMilli();
    }

    public static long currentEpochSeconds() {
        return Instant.now().getEpochSecond();
    }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public static LocalDate today() {
        return LocalDate.now();
    }

    public static String format(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime parse(String date, String pattern) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern));
    }

    public static ZonedDateTime nowUtc() {
        return ZonedDateTime.now(ZoneOffset.UTC);
    }

    public static LocalDateTime toUtc(LocalDateTime localDateTime) {
        return localDateTime
                .atZone(ZoneId.systemDefault())
                .withZoneSameInstant(ZoneOffset.UTC)
                .toLocalDateTime();
    }

    public static LocalDateTime fromUtc(LocalDateTime utcDateTime) {
        return utcDateTime
                .atZone(ZoneOffset.UTC)
                .withZoneSameInstant(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static LocalDateTime addMinutes(LocalDateTime dateTime, long minutes) {
        return dateTime.plusMinutes(minutes);
    }

    public static LocalDateTime addHours(LocalDateTime dateTime, long hours) {
        return dateTime.plusHours(hours);
    }

    public static LocalDateTime addDays(LocalDateTime dateTime, long days) {
        return dateTime.plusDays(days);
    }

    public static boolean isExpired(Instant expiryInstant) {
        return Instant.now().isAfter(expiryInstant);
    }

    public static Instant minutesFromNow(long minutes) {
        return Instant.now().plus(Duration.ofMinutes(minutes));
    }

    public static Instant hoursFromNow(long hours) {
        return Instant.now().plus(Duration.ofHours(hours));
    }
}
