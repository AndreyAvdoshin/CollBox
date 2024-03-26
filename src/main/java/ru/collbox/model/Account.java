package ru.collbox.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title")
    private String title;

    @Column(name = "balance")
    private Double balance;
}
