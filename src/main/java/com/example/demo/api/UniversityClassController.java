package com.example.demo.api;

import com.example.demo.Dao.UniversityClassDao;
import com.example.demo.exception.InvalidUniversityClassException;
import com.example.demo.model.UniversityClass;
import com.example.demo.service.UniversityClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/class")
public class UniversityClassController {
    private UniversityClassService universityClassService;

    @Autowired
    public UniversityClassController(UniversityClassService universityClassService) {
        this.universityClassService = universityClassService;
    }

    @GetMapping
    public List<UniversityClass> getAllClasses() {
        return universityClassService.getAllClasses();
    }

    @PostMapping
    @RequestMapping("/add")
    public ResponseEntity<String> addClass(@RequestBody UniversityClass universityClass) {
        try {
            UniversityClass universityClass1 = universityClassService.addUniversityClass(universityClass);
            return ResponseEntity.ok("Registered Student: " + universityClass1.toString());
        } catch (InvalidUniversityClassException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
