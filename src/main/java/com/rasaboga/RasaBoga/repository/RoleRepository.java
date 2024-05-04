package com.rasaboga.RasaBoga.repository;

import com.rasaboga.RasaBoga.constant.ERole;
import com.rasaboga.RasaBoga.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRole(ERole role);
}
