package com.rasaboga.RasaBoga.service.impl;

import com.rasaboga.RasaBoga.constant.ERole;
import com.rasaboga.RasaBoga.entity.Role;
import com.rasaboga.RasaBoga.repository.RoleRepository;
import com.rasaboga.RasaBoga.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Role getOrSave(ERole erole) {
        Optional<Role> orSave = roleRepository.findByRole(erole);
        if (orSave.isPresent()) return orSave.get();

        Role role = Role.builder()
                .role(erole)
                .build();
        return roleRepository.save(role);
    }
}
