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
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
public class FacultyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @SpyBean
    private FacultyService facultyService;
    @MockBean
    private FacultyRepository facultyRepository;
    private String name1 = "Hufflepuff";
    private String name2 = "Ravenclaw";
    private String color1 = "yellow";
    private String color2 = "purple";

    @Test
    public void postTest() throws Exception {
        when(facultyRepository.save(any(Faculty.class))).thenReturn(getFaculty1());
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name1);
        facultyObject.put("color", color1);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(facultyObject.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value(name1))
                .andExpect(jsonPath("$.color").value(color1));
    }

    @Test
    public void getTest() throws Exception {
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(getFaculty1()));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty?id=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value(name1))
                .andExpect(jsonPath("$.color").value(color1));
    }

    @Test
    public void updateTest() throws Exception {
        when(facultyRepository.save(any(Faculty.class))).thenReturn(getFaculty1());
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name1);
        facultyObject.put("color", color1);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(facultyObject.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value(name1))
                .andExpect(jsonPath("$.color").value(color1));
    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty?id=1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getFacultyByColorTest() throws Exception {
        when(facultyRepository.findByColor(any(String.class))).thenReturn(getFaculties());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/get-by-color?color=yellow")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value(name1))
                .andExpect(jsonPath("$[0].color").value(color1))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value(name2))
                .andExpect(jsonPath("$[1].color").value(color2));
    }

    @Test
    public void getFacultyByStudentTest() throws Exception {
        when(facultyRepository.getFacultyByStudent(any(Long.class))).thenReturn(getFaculty1());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/get-faculty-by-student?id=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value(name1))
                .andExpect(jsonPath("$.color").value(color1));

    }

    private List<Faculty> getFaculties() {
        List<Faculty> facultyList = new ArrayList<>();
        facultyList.add(getFaculty1());
        facultyList.add(getFaculty2());
        return facultyList;
    }

    private Faculty getFaculty1() {
        Faculty facultyTest = new Faculty();
        facultyTest.setName(name1);
        facultyTest.setColor(color1);
        facultyTest.setId(1L);
        return facultyTest;
    }

    private Faculty getFaculty2() {
        Faculty facultyTest = new Faculty();
        facultyTest.setName(name2);
        facultyTest.setColor(color2);
        facultyTest.setId(2L);
        return facultyTest;
    }
}
