package com.hehmdalolkek.spring.kidscareback.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "children")
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

    @NotBlank
    @Size(min = 2, max = 45)
    @Column(name = "surname")
    private String surname;

    @NotBlank
    @Size(min = 2, max = 45)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Size(min = 2, max = 45)
    @Column(name = "patronymic")
    private String patronymic;

    @Past
    @Column(name = "dob")
    private LocalDate dateOfBirthday;

    @Pattern(regexp = "^(|((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10})$")
    @Column(name = "main_phone")
    private String mainPhone;

    @Pattern(regexp = "^(|((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10})$")
    @Column(name = "additional_phone")
    private String additionalPhone;

    @OneToMany(mappedBy = "child", fetch = FetchType.LAZY , cascade = {CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JsonManagedReference("child-allergy")
    private List<Allergy> allergies;

    @OneToMany(mappedBy = "child", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JsonManagedReference("child-vaccination")
    private List<Vaccination> vaccinations;

    public Child() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(LocalDate dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public String getMainPhone() {
        return mainPhone;
    }

    public void setMainPhone(String mainPhone) {
        this.mainPhone = mainPhone;
    }

    public String getAdditionalPhone() {
        return additionalPhone;
    }

    public void setAdditionalPhone(String additionalPhone) {
        this.additionalPhone = additionalPhone;
    }

    public List<Allergy> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<Allergy> allergies) {
        this.allergies = allergies;
    }

    public List<Vaccination> getVaccinations() {
        return vaccinations;
    }

    public void setVaccinations(List<Vaccination> vaccinations) {
        this.vaccinations = vaccinations;
    }
}
