package org.rw.dto;

import lombok.Data;
import org.rw.enums.EDepartment;

@Data
public class CreateClassDto {
    private String code;
    private String name;
    private EDepartment department;
}
