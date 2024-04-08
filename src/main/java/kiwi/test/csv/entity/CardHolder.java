package kiwi.test.csv.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardHolder {


    @Id
    private Long cardHolderId;
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String street;
    private String city;
    private String state;
    private Integer zip;
    private Double latitude;
    private Double longitude;
    private Integer city_pop;
    private String job;  // set -> string
    private LocalDateTime dob;

    @Builder
    public CardHolder(Long cardHolderId, String firstName, String lastName, Gender gender, String street, String city, String state, Integer zip, Double latitude, Double longitude, Integer city_pop, String job, LocalDateTime dob) {
        this.cardHolderId = cardHolderId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city_pop = city_pop;
        this.job = job;
        this.dob = dob;
    }

    @Getter
    public enum Gender {
        Male("M"), Female("F"), Unknown("U");

        private String code;

        Gender(String code) {
            this.code = code;
        }

        public Gender convert(String code) {
            return Arrays.stream(Gender.values())
                        .filter(value -> code.equals(value.getCode()))
                        .findFirst()
                        .orElse(Unknown);
        }
    }

}
