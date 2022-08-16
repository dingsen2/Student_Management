package com.example.demo.service;

import com.example.demo.Dao.UniversityClassDao;
import com.example.demo.exception.InvalidUniversityClassException;
import com.example.demo.model.UniversityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class UniversityClassService {
    private UniversityClassDao universityClassDao;

    @Autowired
    public UniversityClassService(UniversityClassDao universityClassDao) {
        this.universityClassDao = universityClassDao;
    }

    public List<UniversityClass> getAllClasses() {
        return (List<UniversityClass>) universityClassDao.findAll();
    }

    public UniversityClass addUniversityClass(UniversityClass universityClass) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (universityClass.getYear() < currentYear) {
            throw new InvalidUniversityClassException("Cannot add past year class");
        }
        if (universityClass.getYear() > currentYear + 1) {
            throw new InvalidUniversityClassException("Cannot add too far future class");
        }
        return universityClassDao.save(universityClass);
    }
}
