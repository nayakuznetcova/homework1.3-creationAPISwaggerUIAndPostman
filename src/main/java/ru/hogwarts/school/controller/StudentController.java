package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.exception.NoFoundIdException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student add(@RequestParam String name, @RequestParam("age") int age) {
        return studentService.add(name, age);

    }

    @GetMapping
    public String get(@RequestParam("id") Long id) {
        try {
            return studentService.get(id).toString();
        } catch (NoFoundIdException e) {
            return "Это id не найдено";
        }
    }

    @PutMapping
    public Student update(@RequestBody Student student) {
        return studentService.update(student);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) {
        studentService.delete(id);
    }

    @GetMapping("/get-by-age")
    public List<Student> getStudentsByAge(@RequestParam int age) {
        return studentService.getStudentsByAge(age);
    }

    @GetMapping("/get-by-age-between")
    public List<Student> getStudentsByAgeBetween(@RequestParam int min, @RequestParam int max) {
        return studentService.getStudentsByAgeBetween(min, max);
    }

    @GetMapping("/get-students-by-faculty")
    public List<Student> getStudentsByFaculty(@RequestParam String facultyName) {
        return studentService.getStudentsByFaculty(facultyName);
    }

    @GetMapping("/get-count-student")
    public Long getCountStudent(){
        return studentService.getCountStudent();
    }

    @GetMapping("/get-average-age-student")
    public Long getAverageAgeStudent(){
        return studentService.getAverageAgeStudent();
    }

    @GetMapping("/get-last-students")
    public List<Student> getLastStudents(){
        return studentService.getLastStudents();
    }
}
