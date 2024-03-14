package com.example.quiz.Controller;


import com.example.quiz.ApiResponse.ApiResponse;
import com.example.quiz.Model.Student;
import com.example.quiz.Model.Teacher;
import com.example.quiz.Services.StudentService;
import com.example.quiz.Services.TeacherService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/teacher")
@AllArgsConstructor
public class TeacherController {


    private final TeacherService teacherService;


    @GetMapping("get")
    public ResponseEntity getAllTeachers(){

        ArrayList<Teacher> TeacherList = teacherService.getAllTeachers();
        return ResponseEntity.status(200).body(TeacherList);
    }

    @PostMapping("post")
    public ResponseEntity addTeacher(@RequestBody @Valid Teacher teacher, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(200).body(new ApiResponse("teacher added successfully"));
    }

    @PutMapping("update/{id}")
    public ResponseEntity updateTeacher(@PathVariable int id,@RequestBody Teacher teacher, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        boolean isUpdated = teacherService.updateTeacher(id,teacher);
        if (isUpdated){
            return ResponseEntity.status(400).body(new ApiResponse("teacher successfully updated"));

        }else{
            return ResponseEntity.status(200).body(new ApiResponse("teacher not found"));
        }
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteTeacher(@PathVariable int id) {

        boolean isDeleted = teacherService.deleteTeacher(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("teacher deleted"));

        } else {
            return ResponseEntity.status(400).body(new ApiResponse("teacher not found"));

        }
    }

    @GetMapping("search/{id}")
    public ResponseEntity getAllTeachers(@PathVariable int id){

        Teacher teacher = teacherService.search(id);

        if (teacher == null){
            return ResponseEntity.status(400).body(new ApiResponse("Teacher not found"));
        }

        return ResponseEntity.status(200).body(teacher);
    }


    @GetMapping("salary/{salary}")
    public ResponseEntity getSalaryList(@PathVariable double salary){

        ArrayList<Teacher> salaryList = teacherService.getSalaryList(salary);

        if (salaryList.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("salary not found"));
        }
        return ResponseEntity.status(200).body(salaryList);
    }

}
