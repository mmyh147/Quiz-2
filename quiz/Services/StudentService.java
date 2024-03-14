package com.example.quiz.Services;

import com.example.quiz.Model.Student;
import com.example.quiz.Model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {

    ArrayList<Student> studentsList = new ArrayList<>();

    public ArrayList<Student> getAllStudents() {
        return studentsList;
    }

    public void addStudent(Student student) {

        studentsList.add(student);

    }

    public boolean updateStudent(int id, Student student){
        for (int i = 0; i < studentsList.size(); i++) {
            if(studentsList.get(i).getId() == id){
                studentsList.set(i, student);
                return true;
            }

        }
        return false;


    }

    public boolean deleteStudent(int id){
        for (int i = 0; i < studentsList.size(); i++) {
            if(studentsList.get(i).getId() == id){
                studentsList.remove(i);
                return true;
            }

        }
        return false;
    }


    public Student search(String name){
        for (Student student : studentsList) {
            if (student.getName().equals(name)) {
                return student;
            }

        }
        return null;
    }


    public ArrayList<Student> majorList(String major){
        ArrayList<Student> majorList = new ArrayList<>();
        for (Student student : studentsList) {
            if (student.getMajor().equals(major)) {
                majorList.add(student);
            }

        }
        return majorList;
    }


}
