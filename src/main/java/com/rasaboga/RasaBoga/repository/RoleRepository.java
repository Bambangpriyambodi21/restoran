package com.rasaboga.RasaBoga.repository;

import com.rasaboga.RasaBoga.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
