package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.exception.NoFoundIdException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty add(@RequestParam String name, @RequestParam String color) {
        return facultyService.add(name, color);
    }

    @GetMapping
    public String get(@RequestParam Long id) {
        try {
            return facultyService.get(id).toString();
        } catch (NoFoundIdException e) {
            return "Это id не найдено";
        }
    }

    @PutMapping
    public Faculty update(@RequestBody Faculty faculty) {
        return facultyService.update(faculty);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) {
        facultyService.delete(id);
    }

    @GetMapping("/get-by-color")
    public List<Faculty> getFacultyByColor(@RequestParam String color) {
        return facultyService.getFacultyByColor(color);
    }

    @GetMapping("/get-faculty-by-student")
    public List<Faculty> getFacultyByStudent(@RequestParam Long id){
        return facultyService.getFacultyByStudent(id);
    }
}
