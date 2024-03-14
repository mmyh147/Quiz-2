package com.example.quiz.Services;

import com.example.quiz.Model.Student;
import com.example.quiz.Model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class TeacherService {


    ArrayList<Teacher> teachersList = new ArrayList<>();

    public ArrayList<Teacher> getAllTeachers() {
        return teachersList;
    }

    public void addTeacher(Teacher teacher) {

        teachersList.add(teacher);

    }

    public boolean updateTeacher(int id, Teacher teacher){
        for (int i = 0; i < teachersList.size(); i++) {
            if(teachersList.get(i).getId() == id){
                teachersList.set(i, teacher);
                return true;
            }

        }
        return false;


    }

    public boolean deleteTeacher(int id){
        for (int i = 0; i < teachersList.size(); i++) {
            if(teachersList.get(i).getId() == id){
                teachersList.remove(i);
                return true;
            }

        }
        return false;
    }


    public Teacher search(int id){
        for (int i = 0; i < teachersList.size(); i++) {
            if(teachersList.get(i).getId() == id){
                return teachersList.get(i);
            }

        }
        return null;
    }


    public ArrayList<Teacher> getSalaryList(double salary){
        ArrayList<Teacher> salaryList = new ArrayList<>();
        for (int i = 0; i < teachersList.size(); i++) {
            if(salary <= teachersList.get(i).getSalary()){
                salaryList.add(teachersList.get(i));
            }
            }
        return salaryList;
    }



}
