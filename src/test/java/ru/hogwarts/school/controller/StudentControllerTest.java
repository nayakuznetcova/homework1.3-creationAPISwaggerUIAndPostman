package ru.hogwarts.school.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(StudentController.class)
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @SpyBean
    private StudentService studentService;
    @MockBean
    private StudentRepository studentRepository;
    private String name1 = "Anna";
    private String name2 = "Petr";
    private int age1 = 22;
    private int age2 = 20;
    private Long id1 = 1L;
    private Long id2 = 2L;

    @Test
    public void postTest() throws Exception {
        when(studentRepository.save(any(Student.class))).thenReturn(getStudent1());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student?name=" + name1 + "&age=" + age1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id1))
                .andExpect(jsonPath("$.name").value(name1))
                .andExpect(jsonPath("$.age").value(age1));

    }

    @Test
    public void getTest() throws Exception {
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(getStudent1()));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student?id=" + id1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTest() throws Exception {
        when(studentRepository.save(any(Student.class))).thenReturn(getStudent1());
        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name1);
        studentObject.put("age", age1);


        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id1))
                .andExpect(jsonPath("$.name").value(name1))
                .andExpect(jsonPath("$.age").value(age1));
    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student?id=" + id1))
                .andExpect(status().isOk());
    }

    @Test
    public void getStudentsByAgeTest() throws Exception {
        when(studentRepository.findByAge(any(Integer.class))).thenReturn(getStudents());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/get-by-age?age=" + age1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].age").value(age1))
                .andExpect(jsonPath("$[0].name").value(name1))
                .andExpect(jsonPath("$[0].id").value(id1))
                .andExpect(jsonPath("$[1].age").value(age2))
                .andExpect(jsonPath("$[1].name").value(name2))
                .andExpect(jsonPath("$[1].id").value(id2));
    }

    @Test
    public void getStudentsByAgeBetweenTest() throws Exception {
        when(studentRepository.findByAgeBetween(any(Integer.class),any(Integer.class))).thenReturn(getStudents());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/student/get-by-age-between?min=20&max=22"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].age").value(age1))
                .andExpect(jsonPath("$[0].name").value(name1))
                .andExpect(jsonPath("$[0].id").value(id1))
                .andExpect(jsonPath("$[1].age").value(age2))
                .andExpect(jsonPath("$[1].name").value(name2))
                .andExpect(jsonPath("$[1].id").value(id2));
    }

    @Test
    public void getStudentsByFacultyTest() throws Exception {
        when(studentRepository.getStudentsByFaculty(any(String.class))).thenReturn(getStudents());
        mockMvc.perform(MockMvcRequestBuilders
                .get("/student/get-students-by-faculty?facultyName=name"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].age").value(age1))
                .andExpect(jsonPath("$[0].name").value(name1))
                .andExpect(jsonPath("$[0].id").value(id1))
                .andExpect(jsonPath("$[1].age").value(age2))
                .andExpect(jsonPath("$[1].name").value(name2))
                .andExpect(jsonPath("$[1].id").value(id2));
    }

    private Student getStudent1() {
        Student studentTest = new Student();
        studentTest.setName(name1);
        studentTest.setAge(age1);
        studentTest.setId(id1);
        return studentTest;

    }

    private Student getStudent2() {
        Student studentTest = new Student();
        studentTest.setName(name2);
        studentTest.setAge(age2);
        studentTest.setId(id2);
        return studentTest;
    }

    private List<Student> getStudents() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(getStudent1());
        studentList.add(getStudent2());
        return studentList;
    }
}
