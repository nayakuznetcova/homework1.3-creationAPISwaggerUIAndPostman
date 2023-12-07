package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

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
    public Student get(@RequestParam("id") Long id) {
        return studentService.get(id);
    }

    @PutMapping
    public Student set(@RequestBody Student student) {
        return studentService.set(student);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) {
        studentService.delete(id);
    }

    @GetMapping("/getByAge")
    public List<Student> getStudentsByAge(@RequestParam int age) {
        return studentService.getStudentsByAge(age);
    }
}
