package org.rw.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rw.enums.EDepartment;

import java.util.UUID;

@Table
@Entity(name = "t_class")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MClass {
    @GeneratedValue(generator = "UUID")
    @Id
    private UUID id;
    private String code;
    private String name;
    @Enumerated(EnumType.STRING)
    private EDepartment department;
}
