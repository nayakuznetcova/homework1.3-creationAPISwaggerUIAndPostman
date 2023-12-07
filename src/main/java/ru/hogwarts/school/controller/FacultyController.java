package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

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
    public Faculty get(@RequestParam Long id) {
        return facultyService.get(id);
    }

    @PutMapping
    public Faculty set(@RequestBody Faculty faculty) {
        return facultyService.set(faculty);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) {
        facultyService.delete(id);
    }

    @GetMapping("/getByColor")
    public List<Faculty> getFacultyByColor(@RequestParam String color) {
        return facultyService.getFacultyByColor(color);
    }
}
