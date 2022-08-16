package com.example.demo.service;

import com.example.demo.Dao.StudentDao;
import com.example.demo.Dao.UniversityClassDao;
import com.example.demo.exception.InvalidUniversityClassException;
import com.example.demo.exception.StudentEmptyNameException;
import com.example.demo.exception.StudentNonExistException;
import com.example.demo.model.Student;
import com.example.demo.model.UniversityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentDao studentDao;
    private UniversityClassDao universityClassDao;

    @Autowired
    public StudentService(StudentDao studentDao, UniversityClassDao universityClassDao) {
        this.studentDao = studentDao;
        this.universityClassDao = universityClassDao;
    }

    public Student addStudent(Student student) {
        if (student.getName().isEmpty()) {
            throw new StudentEmptyNameException("student's name cannot be empty");
        }
        return studentDao.save(student);
    }

    public Student updateStudentById(Student student) {
        if (student.getId() == null || ! studentDao.existsById(student.getId())) {
            throw new StudentNonExistException("cannot find student's id");
        }
        return studentDao.save(student);
    }

    public List<Student> getAllStudent() {
        return (List<Student>) studentDao.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentDao.findById(id);
    }


    public Student assignClass(Long studentId, Long classId) {
        if (! studentDao.existsById(studentId)) {
            throw new StudentNonExistException("cannot find student's id: " + studentId);
        }
        if (! universityClassDao.existsById(classId)) {
            throw new InvalidUniversityClassException("cannot find class id: " + classId);
        }
        Student student = getStudentById(studentId).get();
        UniversityClass universityClass = universityClassDao.findById(classId).get();
        student.setUniversityClass(universityClass);
        return studentDao.save(student);
    }

    public List<Student> getStudentsByName(String name) {
        return studentDao.findByName(name);
    }
}
