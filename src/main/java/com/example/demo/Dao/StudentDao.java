package com.example.demo.Dao;

import com.example.demo.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StudentDao extends CrudRepository<Student, Long> {
    List<Student> findByName(String name);
}
