package kiwi.test.csv;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Set;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.Double.parseDouble;

@Getter
@ToString
public class Fraud {
    private final Integer id;
    private final LocalDateTime trans_date_trans_time;
    private final Long cc_num;
    private final Set<String> merchant;
    private final String category;
    private final Double amt;
    private final String first;
    private final String last;
    private final String gender;
    private final String street;
    private final String city;
    private final String state;
    private final Integer zip;
    private final Double latitude;
    private final Double longitude;
    private final Integer city_pop;
    private final Set<String> job;
    private final LocalDateTime dob;
    private final String trans_num;
    private final Long unix_time;
    private final Double merch_lat;
    private final Double merch_long;
    private final Boolean is_fraud;

    @Builder
    public Fraud(Integer id, String trans_date_trans_time, String cc_num, Set<String> merchant, String category, Double amt, String first, String last, String gender, String street, String city, String state, Integer zip, Double latitude, Double longitude, Integer city_pop, Set<String> job, String dob, String trans_num, Long unix_time, Double merch_lat, Double merch_long, String is_fraud) {
        this.id = id;
        this.trans_date_trans_time = convertTimeLDT(trans_date_trans_time);
        this.cc_num = (long) parseDouble(cc_num);
        this.merchant = merchant;
        this.category = category;
        this.amt = amt;
        this.first = first;
        this.last = last;
        this.gender = gender;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city_pop = city_pop;
        this.job = job;
        this.dob = convertDateLDT(dob);
        this.trans_num = trans_num;
        this.unix_time = unix_time;
        this.merch_lat = merch_lat;
        this.merch_long = merch_long;
        this.is_fraud = convertBoolean(is_fraud);
    }

    private LocalDateTime convertTimeLDT(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(date, dateTimeFormatter);
    }

    private LocalDateTime convertDateLDT(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, dateTimeFormatter).atStartOfDay();
    }

    private Boolean convertBoolean(String bool) {
        return Objects.equals(bool, "0")? FALSE : TRUE;
    }
}
