package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Faculty> add(@RequestBody Faculty faculty) {
        Faculty newFaculty = facultyService.add(faculty);
        return ResponseEntity.ok(newFaculty);
    }

    @GetMapping
    public ResponseEntity<Faculty> get(@RequestParam Long id) {
        try {
            Faculty faculty = facultyService.get(id);
            return ResponseEntity.ok(faculty);
        } catch (NoFoundIdException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Faculty> update(@RequestBody Faculty faculty) {
        Faculty faculty1 = facultyService.update(faculty);
        return ResponseEntity.ok(faculty1);

    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        facultyService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-by-color")
    public ResponseEntity<List<Faculty>> getFacultyByColor(@RequestParam String color) {
        List<Faculty> facultyByColor = facultyService.getFacultyByColor(color);
        return ResponseEntity.ok(facultyByColor);

    }

    @GetMapping("/get-faculty-by-student")
    public ResponseEntity<Faculty> getFacultyByStudent(@RequestParam Long id) {
        Faculty facultyByStudent = facultyService.getFacultyByStudent(id);
        return ResponseEntity.ok(facultyByStudent);
    }

    @GetMapping("/get-longest-name-faculty")
    public ResponseEntity<String> getLongestNameFaculty() {
        String nameFaculty = facultyService.getLongestNameFaculty();
        return ResponseEntity.ok(nameFaculty);
    }
}
