package com.rasaboga.RasaBoga.service.impl;

import com.rasaboga.RasaBoga.entity.Menu;
import com.rasaboga.RasaBoga.model.request.SearchMenuRequest;
import com.rasaboga.RasaBoga.model.response.MenuResponse;
import com.rasaboga.RasaBoga.repository.MenuRepository;
import com.rasaboga.RasaBoga.service.MenuService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
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
    public ArrayList<MenuResponse> findAll(SearchMenuRequest menu) {
        PageRequest pageable = PageRequest.of(menu.getHalaman(), menu.getUkuran());
        Specification<Menu> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (menu.getMenu()!= null){
                Predicate namePredicate = criteriaBuilder.like(root.get("menu"), "%"+menu.getMenu()+"%");
                predicates.add(namePredicate);
            }

            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
        Page<Menu> all = menuRepository.findAll(spec, pageable);
        List<MenuResponse> menuResponses = all.getContent().stream()
                .map(item -> MenuResponse.builder()
                        .menu(item.getMenu())
                        .stok(item.getStok())
                        .harga(item.getHarga())
                        .build())
                .collect(Collectors.toList());

        return new ArrayList<>(menuResponses);
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
