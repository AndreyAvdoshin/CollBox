package ru.collbox.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public class Category extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title")
    private String title;
}
