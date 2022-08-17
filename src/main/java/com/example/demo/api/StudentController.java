package com.example.demo.api;

import com.example.demo.exception.InvalidUniversityClassException;
import com.example.demo.exception.StudentEmptyNameException;
import com.example.demo.exception.StudentNonExistException;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Struct;
import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudent () {
        return studentService.getAllStudent();
    }

    @RequestMapping("/register")
    @PostMapping
    public ResponseEntity<String> registerStudent (@RequestBody Student student) {
        try {
            Student student1 = studentService.addStudent(student);
            return ResponseEntity.ok("Registered Student: " + student1.getName() + student1.getId());
        } catch (StudentEmptyNameException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @RequestMapping("/assignclass/{sid}/{cid}")
    @PostMapping
    public ResponseEntity<String> assignClass(@PathVariable("sid") Long studentId,
                                              @PathVariable("cid") Long classId) {
        try {
            Student updatedStudent = studentService.assignClass(studentId, classId);
            return ResponseEntity.ok("Assigned class. " + updatedStudent.toString());
        } catch (StudentNonExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (InvalidUniversityClassException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @RequestMapping("/name")
    @GetMapping
    public List<Student> getStudentByName(@RequestParam String name) {
        return studentService.getStudentsByName(name);
    }

    @RequestMapping("/contain_name")
    @GetMapping
    //contain_name?contain_name=T
    public List<Student> getStudentByNameContainStr(@RequestParam String name) {
        return studentService.getStudentsByNameContainStr(name);
    }

    @RequestMapping("/year_class")
    @GetMapping
    //year_class?year=2022&number=1
    public List<Student> getStudentByNameContainStr(@RequestParam int year, @RequestParam int number) {
        return studentService.getStudentsInYearClass(year, number);
    }
}
