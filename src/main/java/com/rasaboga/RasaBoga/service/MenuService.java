package com.rasaboga.RasaBoga.service;

import com.rasaboga.RasaBoga.entity.Menu;
import com.rasaboga.RasaBoga.model.request.SearchMenuRequest;
import com.rasaboga.RasaBoga.model.response.MenuResponse;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public interface MenuService {
    Menu create(Menu menu);
    Menu update(Menu menu);
    ArrayList<MenuResponse> findAll(SearchMenuRequest menu);
    String delete(String id);
    Menu findId(String id);
}
