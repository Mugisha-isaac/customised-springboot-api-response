package org.rw.service;

import com.github.dockerjava.api.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rw.clients.StudentFeignClient;
import org.rw.dto.CreateClassDto;
import org.rw.model.MClass;
import org.rw.repository.ClassRepository;
import org.rw.security.dto.StudentDto;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassService {

    private final ClassRepository classRepository;
    private final StudentFeignClient studentFeignClient;

    public List<MClass> getAllClasses() {
        return classRepository.findAll();
    }

    public MClass createNewClass(CreateClassDto  newClass) {

        MClass savedClass = MClass.builder()
                .code(newClass.getCode())
                .name(newClass.getName())
                .department(newClass.getDepartment())
                .build();
        return classRepository.save(savedClass);
    }

    public StudentDto enrollStudent(UUID classId, UUID studentId) throws OpenApiResourceNotFoundException {
        System.out.println("Inside the service ************");
        MClass mClass = classRepository.findById(classId)
                .orElseThrow(() -> new BadRequestException("Class not found"));
        System.out.println("Class: " + mClass);
        StudentDto student = studentFeignClient.getStudentById(studentId).getData();
        System.out.println("Student: " + student);
        return null;
    }
}
