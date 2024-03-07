package ru.hogwarts.school.service;

import liquibase.pro.packaged.F;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.NoFoundIdException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import java.util.List;

@Service
public class FacultyService {
    Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty add(Faculty faculty) {
        logger.info("Добавление факультета");
        return facultyRepository.save(faculty);

    }

    public Faculty get(Long id) throws NoFoundIdException{
        logger.info("Получение факультета");
        return facultyRepository.findById(id).orElseThrow(()->new NoFoundIdException("Это id не найдено"));
    }

    public Faculty update(Faculty faculty) {
        logger.info("Изменение факультета");
        return facultyRepository.save(faculty);
    }

    public void delete(Long id) {
        logger.info("Удаление факультета");
        facultyRepository.deleteById(id);
    }

    public List<Faculty> getFacultyByColor(String color) {
        logger.info("Получение факультета по цвету");
        return facultyRepository.findByColor(color);
    }

    public Faculty getFacultyByStudent(Long id){
        logger.info("Получение факультета по студенту");
        return facultyRepository.getFacultyByStudent(id);
    }

    public String getLongestNameFaculty(){
        List<Faculty> faculty = facultyRepository.findAll();
        return faculty.stream()
                .max((x, y) -> x.getName().length() - y.getName().length())
                .orElseThrow(() -> new RuntimeException("Ошибка"))
                .getName();
    }
}
