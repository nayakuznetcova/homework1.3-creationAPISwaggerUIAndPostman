package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {
    private Map<Long, Student> students = new HashMap<>();
    private Long key = 1L;

    public Student add(String name, int age) {
        Student student = new Student(key, name, age);
        students.put(key, student);
        key++;
        return student;
    }

    public Student get(Long id) {
        return students.get(id);
    }

    public Student set(Student student) {
        return students.put(student.getId(), student);
    }

    public Student delete(Long id) {
        return students.remove(id);
    }

    public List<Student> getStudentsByAge(int age) {
        List<Student> studentsByAge = new ArrayList<>();
        for (Student student : students.values()) {
            if (student.getAge() == age) {
                studentsByAge.add(student);
            }

        }
        return studentsByAge;
    }

}
