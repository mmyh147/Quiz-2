package com.example.quiz.Controller;


import com.example.quiz.ApiResponse.ApiResponse;
import com.example.quiz.Model.Student;
import com.example.quiz.Model.Teacher;
import com.example.quiz.Services.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @GetMapping("get")
    public ResponseEntity getAllStudents(){

        ArrayList<Student> studentsList = studentService.getAllStudents();
        return ResponseEntity.status(200).body(studentsList);
    }

    @PostMapping("post")
    public ResponseEntity addStudent(@RequestBody @Valid Student student, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("Student added successfully"));
    }

    @PutMapping("update/{id}")
    public ResponseEntity updateStudent(@PathVariable int id,@RequestBody Student student, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        boolean isUpdated = studentService.updateStudent(id,student);
        if (isUpdated){
            return ResponseEntity.status(400).body(new ApiResponse("student successfully updated"));

        }else{
            return ResponseEntity.status(200).body(new ApiResponse("student not found"));
        }
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable int id) {

        boolean isDeleted = studentService.deleteStudent(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("student deleted"));

        } else {
            return ResponseEntity.status(400).body(new ApiResponse("student not found"));

        }
    }



    @GetMapping("search/{name}")
    public ResponseEntity getstudentByName(@PathVariable String name){

        Student student = studentService.search(name);

        if (student == null){
            return ResponseEntity.status(400).body(new ApiResponse("student not found"));
        }

        return ResponseEntity.status(200).body(student);
    }


    @GetMapping("major/{major}")
    public ResponseEntity getMajorList(@PathVariable String major){

        ArrayList<Student> majorList = studentService.majorList(major);

        if (majorList.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("no students in provided major"));
        }
        return ResponseEntity.status(200).body(majorList);
    }


}
