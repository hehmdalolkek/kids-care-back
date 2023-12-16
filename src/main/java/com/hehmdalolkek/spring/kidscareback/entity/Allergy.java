package com.hehmdalolkek.spring.kidscareback.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "allergies")
public class Allergy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "child_id")
    @JsonBackReference("child-allergy")
    private Child child;

    @NotBlank
    @Size(min = 2, max = 45)
    @Column(name = "title")
    private String title;

    public Allergy() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Allergy allergy = (Allergy) o;
        return id == allergy.id && Objects.equals(child, allergy.child) && Objects.equals(title, allergy.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, child, title);
    }
}
