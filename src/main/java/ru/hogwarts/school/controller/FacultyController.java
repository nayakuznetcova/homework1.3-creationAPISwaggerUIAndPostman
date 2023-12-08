package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
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
        } catch (NoSuchElementException e) {
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

    @GetMapping("/getByColor")
    public List<Faculty> getFacultyByColor(@RequestParam String color) {
        return facultyService.getFacultyByColor(color);
    }
}
