package org.example.treecategorybot.bot.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> children;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = true)
    private Category parent;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
    }
}
