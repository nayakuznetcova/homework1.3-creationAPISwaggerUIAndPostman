package ru.hogwarts.school.service;

import liquibase.pro.packaged.S;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.NoFoundIdException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
public class StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class);
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student add(String name, int age) {
        Student student = new Student(name, age);
        logger.info("Добавление студент");
        return studentRepository.save(student);
    }

    public Student get(Long id) throws NoFoundIdException {
        logger.info("Поиск студента");
        return studentRepository.findById(id).orElseThrow(()->new NoFoundIdException("Это id не найдено"));
    }

    public Student update(Student student) {
        logger.info("Изменение студента");
        return studentRepository.save(student);
    }

    public void delete(Long id) {
        logger.info("Удаление студента");
        studentRepository.deleteById(id);
    }

    public List<Student> getStudentsByAge(int age) {
        logger.info("Поиск студента по возрасту");
        return studentRepository.findByAge(age);
    }

    public List<Student> getStudentsByAgeBetween(int min, int max) {
        logger.info("Поиск студентов между возрастами");
        return studentRepository.findByAgeBetween(min, max);
    }

    public List<Student> getStudentsByFaculty(String facultyName){
        logger.info("Поиск студентов по факультету");
        return studentRepository.getStudentsByFaculty(facultyName);
    }

    public Long getCountStudent(){
        logger.info("Подсчет колличества студентов");
        return studentRepository.getCountStudent();
    }

    public Long getAverageAgeStudent(){
        logger.info("Поиск среднего возраста студентов");
        return studentRepository.getAverageAgeStudent();
    }

    public List<Student> getLastStudents(){
        logger.info("Получение последних в списке студентов");
        return studentRepository.getLastStudents();
    }

    public List<Student> getStudentsByNameWithA(){
        List<Student> students = studentRepository.findAll();
        return students.stream().parallel()
                .filter(student -> student.getName().startsWith("A"))
                .collect(Collectors.toList());
    }

    public Integer getAverageAge(){
        List<Student> students = studentRepository.findAll();
        double average = students.stream().parallel()
                .mapToInt(student -> student.getAge())
                .summaryStatistics()
                .getAverage();
        return (int) average;
    }
}