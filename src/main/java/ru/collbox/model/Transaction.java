package ru.collbox.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.collbox.TransactionType;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@SuperBuilder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "active")
    private boolean active;

    @Column(name = "created")
    private LocalDateTime created;

}
