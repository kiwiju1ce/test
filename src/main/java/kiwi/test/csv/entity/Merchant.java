package kiwi.test.csv.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer merchantId;
    private String name;
    private Double latitude;
    private Double longitude;

    @Builder
    public Merchant(Integer merchantId, String name, Double latitude, Double longitude) {
        this.merchantId = merchantId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
