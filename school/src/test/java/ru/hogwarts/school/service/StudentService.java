package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final Map<Long, Student> students = new HashMap<>();
    private Long nextId = 1L;

    public Student createStudent(Student student) {
        student.setId(nextId++);
        students.put(student.getId(), student);
        return student;
    }

    public Student getStudent(Long id) {
        return students.get(id);
    }

    public List<Student> getAllStudents() {
        return students.values().stream().collect(Collectors.toList());
    }

    public Student updateStudent(Long id, Student studentDetails) {
        if (!students.containsKey(id)) return null;
        studentDetails.setId(id);
        students.put(id, studentDetails);
        return studentDetails;
    }

    public void deleteStudent(Long id) {
        students.remove(id);
    }

    public List<Student> getStudentsByAge(int age) {
        return students.values().stream()
                .filter(s -> s.getAge() == age)
                .collect(Collectors.toList());
    }
}
