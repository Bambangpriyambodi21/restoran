package com.rasaboga.RasaBoga.service;

import com.rasaboga.RasaBoga.constant.ERole;
import com.rasaboga.RasaBoga.entity.Role;

public interface RoleService {
    Role getOrSave(ERole role);
}
