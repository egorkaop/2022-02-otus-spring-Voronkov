package ru.otus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
