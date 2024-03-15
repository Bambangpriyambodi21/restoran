package com.rasaboga.RasaBoga.service.impl;

import com.rasaboga.RasaBoga.entity.Menu;
import com.rasaboga.RasaBoga.model.response.MenuResponse;
import com.rasaboga.RasaBoga.repository.MenuRepository;
import com.rasaboga.RasaBoga.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Override
    public Menu create(Menu menu) {
        Menu menu1 = Menu.builder()
                .menu(menu.getMenu())
                .stok(menu.getStok())
                .harga(menu.getHarga())
                .build();
        Menu save = menuRepository.save(menu1);
        return save;
    }

    @Override
    public Menu update(Menu menu) {
        Optional<Menu> byId = menuRepository.findById(menu.getId_menu());

        Menu menu1 = Menu.builder()
                .id_menu(byId.get().getId_menu())
                .menu(menu.getMenu())
                .stok(menu.getStok())
                .harga(menu.getHarga())
                .build();

        Menu save = menuRepository.save(menu1);

        return save;
    }

    @Override
    public List<MenuResponse> findAll() {
        List<Menu> all = menuRepository.findAll();
        List<MenuResponse> menuResponses = all.stream()
                .map(item -> {
                    Menu menu = findId(item.getId_menu());
                    return MenuResponse.builder()
                            .menu(menu.getMenu())
                            .stok(menu.getStok())
                            .harga(menu.getHarga())
                            .build();
                }).collect(Collectors.toList());
        return menuResponses;
    }

    @Override
    public String delete(String id) {
        menuRepository.deleteById(id);
        return "Data Menu Deleted";
    }

    @Override
    public Menu findId(String id) {
        Optional<Menu> byId = menuRepository.findById(id);
        Menu menu = Menu.builder()
                .id_menu(byId.get().getId_menu())
                .menu(byId.get().getMenu())
                .harga(byId.get().getHarga())
                .stok(byId.get().getStok())
                .build();
        return menu;
    }
}
