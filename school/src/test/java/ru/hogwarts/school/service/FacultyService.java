package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final Map<Long, Faculty> faculties = new HashMap<>();
    private Long nextId = 1L;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(nextId++);
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty getFaculty(Long id) {
        if (id == null) {
            return null;
        }
        return faculties.get(id);
    }

    public List<Faculty> getAllFaculties() {
        return faculties.values().stream().collect(Collectors.toList());
    }

    public Faculty updateFaculty(Long id, Faculty facultyDetails) {
        if (!faculties.containsKey(id) || facultyDetails == null) {
            return null;
        }
        facultyDetails.setId(id);
        faculties.put(id, facultyDetails);
        return facultyDetails;
    }

    public void deleteFaculty(Long id) {
        if (id != null) {
            faculties.remove(id);
        }
    }

    public List<Faculty> getFacultiesByColor(String color) {
        if (color == null) {
            return List.of();
        }
        return faculties.values().stream()
                .filter(f -> color.equalsIgnoreCase(f.getColor()))
                .collect(Collectors.toList());
    }
}
