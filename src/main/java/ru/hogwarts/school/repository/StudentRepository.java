package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAge(int age);

    List<Student> findByAgeBetween(int min, int max);

    @Query(value = "select student.* from student " +
            "inner join faculty on student.faculty_id=faculty.id " +
            "where faculty.name=?1", nativeQuery = true)
    List<Student> getStudentsByFaculty(String facultyName);

    @Query(value = "select count(*) from student", nativeQuery = true)
    Long getCountStudent();

    @Query(value = "select avg(age) from student", nativeQuery = true)
    Long getAverageAgeStudent();

    @Query(value = "select * from student " +
            "order by id desc " +
            "limit 5", nativeQuery = true)
    List<Student> getLastStudents();

}
