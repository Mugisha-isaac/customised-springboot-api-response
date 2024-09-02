package org.rw.repository;

import org.rw.model.MClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClassRepository extends JpaRepository<MClass, UUID> {
}
