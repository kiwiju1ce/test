package kiwi.test.csv.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Transaction {

    @Id
    private Integer transactionId;
    private LocalDateTime trans_date_trans_time;
    private String category;
    private Long unix_time;
    private Boolean isFraud;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "merchantInfo", nullable = false)
    private Merchant merchant;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cardHolderInfo", nullable = false)
    private CardHolder cardHolder;

    @Builder
    public Transaction(Integer transactionId, LocalDateTime trans_date_trans_time, String category, Long unix_time, Boolean isFraud, Merchant merchant, CardHolder cardHolder) {
        this.transactionId = transactionId;
        this.trans_date_trans_time = trans_date_trans_time;
        this.category = category;
        this.unix_time = unix_time;
        this.isFraud = isFraud;
        this.merchant = merchant;
        this.cardHolder = cardHolder;
    }
}
