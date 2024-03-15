package com.rasaboga.RasaBoga.service;

import com.rasaboga.RasaBoga.entity.Menu;
import com.rasaboga.RasaBoga.model.response.MenuResponse;

import java.util.List;

public interface MenuService {
    Menu create(Menu menu);
    Menu update(Menu menu);
    List<MenuResponse> findAll();
    String delete(String id);
    Menu findId(String id);
}
