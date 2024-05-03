package com.rasaboga.RasaBoga.repository;

import com.rasaboga.RasaBoga.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, String> {
}
