package com.example.demo;

import com.google.gson.Gson;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.function.Predicate;

@RestController
public class HomeController implements InitializingBean {

    ArrayList<Student> students = new ArrayList<>();

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @GetMapping("/students")
    public String getAllStudent() {

        Gson gson = new Gson();
        return gson.toJson(students);
    }

    @PutMapping("/students/{id}")
    public String editStudent(@PathVariable("id") int id, @RequestBody Student student) {
        students.removeIf(student1 -> {
            if(student.getId()==id){
                return true;
            }else {
                return false;
            }
        });
        students.add(student);
        return new Gson().toJson(students);
    }

    @PostMapping("/students")
    public String addStudent(@RequestBody Student student) {
        students.add(student);
        return new Gson().toJson(students);
    }

    //Todo @PathVarible

    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable("id") int id) {
        students.removeIf(student -> {
            if (student.getId() == id) {
                return true;
            } else {
                return false;
            }
        });
        return id +"";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        students.add(new Student(1, "Hoang", 12));
        students.add(new Student(2, "Ngoc", 12));
        students.add(new Student(3, "Bao", 12));
    }
}
