package ru.collbox.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public class Category extends BaseEntity {

    @ManyToMany
    @JoinTable(name = "users_categories",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "categories_id")})
    @Column(name = "user_id")
    private Set<User> user;

    @Column(name = "title")
    private String title;
}
