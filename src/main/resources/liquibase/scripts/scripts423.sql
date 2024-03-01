select s.name, age, f.name from faculty f
inner join student as s on faculty_id = f.id;

select s.* from student as s
inner join avatar as a on student_id = s.id;