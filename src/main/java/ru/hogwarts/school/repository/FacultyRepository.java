package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findByColor(String color);
    @Query(value = "select faculty.* from student " +
            "inner join faculty on student.faculty_id=faculty.id " +
            "where student.id=?1", nativeQuery = true)
    List<Faculty> getFacultyByStudent(Long id);

}


