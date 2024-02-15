package ru.hogwarts.school.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.hogwarts.school.exception.NoFoundIdException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FacultyServiceTest {
    private FacultyService facultyService;
    private FacultyRepository facultyRepository;
    private String name1 = "Hufflepuff";
    private String name2 = "Ravenclaw";
    private String color1 = "purple";
    private String color2 = "yellow";
    @BeforeEach
    public void init(){
        facultyRepository = Mockito.mock(FacultyRepository.class);
        facultyService = new FacultyService(facultyRepository);
    }

    @Test
    public void getTest() throws NoFoundIdException {
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(getFaculty1()));

        Faculty faculty = facultyService.get(1L);
        assertEquals(getFaculty1(), faculty);
    }

    @Test
    public void getNoFoundIdExceptionTest(){
        assertThrows(NoFoundIdException.class, ()->facultyService.get(3L));
    }

    @Test
    public void addTest(){
        when(facultyRepository.save(any(Faculty.class))).thenReturn(getFaculty1());

        Faculty faculty = facultyService.add(new Faculty(name1, color1));
        assertEquals(getFaculty1(), faculty);
    }

    @Test
    public void updateTest(){
        Faculty faculty = getFaculty1();
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        Faculty facultyActual = facultyService.update(faculty);
        assertEquals(faculty, facultyActual);
    }

    @Test
    public void getFacultyByColorTest(){
        when(facultyRepository.findByColor(any(String.class))).thenReturn(getFaculties());

        List<Faculty> faculties = facultyService.getFacultyByColor("violet");
        assertEquals(getFaculties(), faculties);
    }

    @Test
    public void getFacultyByStudentTest(){
        Faculty faculty1 = getFaculty1();
        when(facultyRepository.getFacultyByStudent(any(Long.class))).thenReturn(faculty1);

        Faculty facultyActual = facultyService.getFacultyByStudent(1L);
        assertEquals(faculty1, facultyActual);
    }

    private List <Faculty> getFaculties(){
        List<Faculty> facultyList = new ArrayList<>();
        facultyList.add(getFaculty1());
        facultyList.add(getFaculty2());
        return facultyList;
    }

    private Faculty getFaculty1(){
        Faculty facultyTest = new Faculty();
        facultyTest.setName(name1);
        facultyTest.setColor(color1);
        facultyTest.setId(1L);
        return facultyTest;
    }

    private Faculty getFaculty2(){
        Faculty facultyTest = new Faculty();
        facultyTest.setName(name2);
        facultyTest.setColor(color2);
        facultyTest.setId(2L);
        return facultyTest;

    }

}
