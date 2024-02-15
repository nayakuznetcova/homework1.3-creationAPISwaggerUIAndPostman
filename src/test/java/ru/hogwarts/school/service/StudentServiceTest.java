package ru.hogwarts.school.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.hogwarts.school.exception.NoFoundIdException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentServiceTest {
    private StudentService studentService;
    private StudentRepository studentRepository;
    private String name1 = "Anna";
    private String name2 = "Petr";
    private int age1 = 22;
    private int age2 = 20;
    @BeforeEach
    public void init(){
        studentRepository = Mockito.mock(StudentRepository.class);
        studentService = new StudentService(studentRepository);
    }
    @Test
    public void getTest() throws NoFoundIdException {
        when(studentRepository.findById(2L)).thenReturn(Optional.of(getStudent2()));

        Student student = studentService.get(2L);
        assertEquals(getStudent2(), student);
    }

    @Test
    public void getNoFoundIdExceptionTest(){
        assertThrows(NoFoundIdException.class, ()->studentService.get(3L));
    }

    @Test
    public void addTest(){
        when(studentRepository.save(any(Student.class))).thenReturn(getStudent2());

        Student student = studentService.add(name2, age2);
        assertEquals(getStudent2(), student);
    }

    @Test
    public void updateTest(){
        Student student = getStudent2();
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student studentActual = studentService.update(student);
        assertEquals(student, studentActual);
    }

    @Test
    public void getStudentsByAgeTest(){
        when(studentRepository.findByAge(any(Integer.class))).thenReturn(getStudents());

        List<Student> students = studentService.getStudentsByAge(20);
        assertEquals(getStudents(), students);
    }

    @Test
    public void getStudentsByAgeBetweenTest(){
        when(studentRepository.findByAgeBetween(any(Integer.class),any(Integer.class))).thenReturn(getStudents());

        List<Student> students = studentService.getStudentsByAgeBetween(20, 22);
        assertEquals(getStudents(), students);
    }

    @Test
    public void getStudentsByFacultyTest(){
        when(studentRepository.getStudentsByFaculty(any(String.class))).thenReturn(getStudents());

        List<Student> students = studentService.getStudentsByFaculty("NAME");
        assertEquals(getStudents(), students);
    }

    private List <Student> getStudents(){
        List<Student> studentList = new ArrayList<>();
        studentList.add(getStudent1());
        studentList.add(getStudent2());
        return studentList;
    }

    private Student getStudent1(){
        Student studentTest = new Student();
        studentTest.setName(name1);
        studentTest.setAge(age1);
        studentTest.setId(1L);
        return studentTest;
    }

    private Student getStudent2(){
        Student studentTest = new Student();
        studentTest.setName(name2);
        studentTest.setAge(age2);
        studentTest.setId(2L);
        return studentTest;
    }
}
