package com.example.demo.mapper;

import com.example.demo.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface StudentMapper {

    //SELECT * FROM Student WHERE name LIKE %T%
    @Select("SELECT * FROM Student WHERE name LIKE #{name}")
     List<Student> studentNameContainString(@Param("name") String str);

    //SELECT * FROM Student WHERE university_class_id IN
    //(SELECT id FROM university_class WHERE year = 2021 AND number = 1);
    @Select("SELECT * FROM Student WHERE university_class_id IN" +
            "(SELECT id FROM university_class WHERE year = 2022 AND number = 1)")
    List<Student> getStudentInYearClass(@Param("year") int year, @Param("number") int number);

}
