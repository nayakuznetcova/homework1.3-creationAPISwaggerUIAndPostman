package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacultyService {
    private Map<Long, Faculty> faculties = new HashMap<>();
    private Long key = 1L;

    public Faculty add(String name, String color){
        Faculty faculty = new Faculty(key, name, color);
        faculties.put(key, faculty);
        key++;
        return faculty;
    }

    public Faculty get(Long id) {
        return faculties.get(id);
    }

    public Faculty set(Faculty faculty) {
        return faculties.put(faculty.getId(), faculty);
    }

    public Faculty delete(Long id) {
        return faculties.remove(id);
    }

    public List<Faculty> getFacultyByColor(String color) {
        List<Faculty> facultyByColor = new ArrayList<>();
        for (Faculty faculty : faculties.values()) {
            if (faculty.getColor().equals(color)) {
                facultyByColor.add(faculty);
            }

        }
        return facultyByColor;
    }


}
