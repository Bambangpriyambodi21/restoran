package com.rasaboga.RasaBoga.controller;

import com.rasaboga.RasaBoga.entity.Menu;
import com.rasaboga.RasaBoga.model.request.SearchMenuRequest;
import com.rasaboga.RasaBoga.model.response.MenuResponse;
import com.rasaboga.RasaBoga.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @PostMapping("/menu")
    public ResponseEntity<?> create(@RequestBody Menu menu){
        Menu menu1 = menuService.create(menu);
        return ResponseEntity.status(HttpStatus.CREATED).body(menu1);
    }

    @PutMapping("/menu")
    public ResponseEntity<?> update(@RequestBody Menu menu){
        Menu menu1 = menuService.update(menu);
        return ResponseEntity.status(HttpStatus.CREATED).body(menu1);
    }

    @GetMapping("/menu")
    public ResponseEntity<?> getAll(@RequestParam(required = false) String menu,
                                    @RequestParam(required = false, defaultValue = "0") Integer halaman,
                                    @RequestParam(required = false, defaultValue = "10") Integer ukuran){
        SearchMenuRequest searchMenuRequest = SearchMenuRequest.builder()
                .menu(menu)
                .halaman(halaman)
                .ukuran(ukuran)
                .build();
        ArrayList<MenuResponse> all = menuService.findAll(searchMenuRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(all);
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        String delete = menuService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(delete);
    }
}
