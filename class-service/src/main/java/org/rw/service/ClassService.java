package org.rw.service;

import com.github.dockerjava.api.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rw.dto.CreateClassDto;
import org.rw.model.MClass;
import org.rw.repository.ClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassService {

    private final ClassRepository classRepository;

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
}
