package com.rasaboga.RasaBoga.repository;

import com.rasaboga.RasaBoga.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MenuRepository extends JpaRepository<Menu, String>, JpaSpecificationExecutor<Menu> {

    Page<Menu> findMenuBy(Pageable pageable, String menu);

}
