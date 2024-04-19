package com.rasaboga.RasaBoga.service.impl;

import com.rasaboga.RasaBoga.entity.Menu;
import com.rasaboga.RasaBoga.entity.Pelanggan;
import com.rasaboga.RasaBoga.entity.Transaksi;
import com.rasaboga.RasaBoga.model.request.SearchTransaksiRequest;
import com.rasaboga.RasaBoga.model.request.TransaksiRequest;
import com.rasaboga.RasaBoga.model.response.TransaksiResponse;
import com.rasaboga.RasaBoga.repository.TransaksiRepository;
import com.rasaboga.RasaBoga.service.MenuService;
import com.rasaboga.RasaBoga.service.PelangganService;
import com.rasaboga.RasaBoga.service.TransaksiService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransaksiServiceImpl implements TransaksiService {
    private final TransaksiRepository transaksiRepository;
    private final MenuService menuService;
    private final PelangganService pelangganService;

    @Override
    public TransaksiResponse create(TransaksiRequest transaksi) {
        Menu menu = menuService.findId(transaksi.getMenu());
        Pelanggan pelanggan = pelangganService.findId(transaksi.getPelanggan());
        if (menu.getStok()<transaksi.getQuantity()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        Transaksi transaksi1 = Transaksi.builder()
                .menu(menu)
                .pelanggan(pelanggan)
                .bill(menu.getHarga()*transaksi.getQuantity())
                .quantity(transaksi.getQuantity())
                .build();
        Transaksi save = transaksiRepository.save(transaksi1);

        Menu menu1 = Menu.builder()
                .id_menu(menu.getId_menu())
                .harga(menu.getHarga())
                .menu(menu.getMenu())
                .stok(menu.getStok()-transaksi.getQuantity())
                .build();
        menuService.update(menu1);

        TransaksiResponse transaksiResponse = TransaksiResponse.builder()
                .id(save.getId())
                .menu(save.getMenu().getId_menu())
                .pelanggan(save.getPelanggan().getId_pelanggan())
                .bill(save.getBill())
                .quantity(save.getQuantity())
                .build();
        return transaksiResponse;
    }

    @Override
    public TransaksiResponse update(TransaksiRequest transaksi) {
        Optional<Transaksi> byId = transaksiRepository.findById(transaksi.getId());
        Pelanggan pelanggan = pelangganService.findId(transaksi.getPelanggan());
        Menu menu = menuService.findId(transaksi.getMenu());
        Transaksi transaksi1 = Transaksi.builder()
                .id(byId.get().getId())
                .pelanggan(pelanggan)
                .bill(transaksi.getBill())
                .quantity(transaksi.getQuantity())
                .menu(menu)
                .build();

        Transaksi save = transaksiRepository.save(transaksi1);

        TransaksiResponse transaksiResponse = TransaksiResponse.builder()
                .id(save.getId())
                .menu(save.getMenu().getId_menu())
                .pelanggan(save.getPelanggan().getId_pelanggan())
                .bill(save.getBill())
                .quantity(save.getQuantity())
                .build();

        return transaksiResponse;
    }

    @Override
    public List<TransaksiResponse> getAll(SearchTransaksiRequest searchTransaksiRequest) {
        PageRequest pageRequest = PageRequest.of(searchTransaksiRequest.getHalaman(), searchTransaksiRequest.getUkuran());
        Specification<Transaksi> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
              if (searchTransaksiRequest.getId()!=null){
                  Predicate predicate = criteriaBuilder.like(root.get("id"),"%"+searchTransaksiRequest.getId()+"%");
                  predicates.add(predicate);
              }

              return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };

        Page<Transaksi> all = transaksiRepository.findAll(specification, pageRequest);
        List<TransaksiResponse> transaksiResponses = all.getContent().stream()
                .map(item -> TransaksiResponse.builder()
                        .id(item.getId())
                        .bill(item.getBill())
                        .pelanggan(item.getPelanggan().getNama_pelanggan())
                        .menu(item.getMenu().getMenu())
                        .quantity(item.getQuantity())
                        .build())
                .collect(Collectors.toList());
        return transaksiResponses;
    }

    @Override
    public String delete(String id) {
        transaksiRepository.deleteById(id);
        return "Data Transaksi Deleted";
    }
}
