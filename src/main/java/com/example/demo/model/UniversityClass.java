package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="universityClass")
public class UniversityClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "year")
    private Integer year;

    @OneToMany(mappedBy = "universityClass")
    List<Student> studentList;

    @Column(nullable = false, name = "number")
    private Integer number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public UniversityClass(Long id, Integer year, Integer number) {
        this.id = id;
        this.year = year;
        this.number = number;
    }

    public UniversityClass() {}

    @Override
    public String toString() {
        String str = "";
        str += "Primary id: " + getId();
        str += "year: " + getYear();
        str += "class num: " + getNumber();
        return str;
    }
}
