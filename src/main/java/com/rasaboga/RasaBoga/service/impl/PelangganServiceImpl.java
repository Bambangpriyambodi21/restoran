package com.rasaboga.RasaBoga.service.impl;

import com.rasaboga.RasaBoga.entity.Pelanggan;
import com.rasaboga.RasaBoga.model.response.PelangganResponse;
import com.rasaboga.RasaBoga.repository.PelangganRepository;
import com.rasaboga.RasaBoga.service.PelangganService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PelangganServiceImpl implements PelangganService {

    private final PelangganRepository pelangganRepository;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(Pelanggan pelanggan) {
        Pelanggan pelanggan1 = Pelanggan.builder()
                .email_pelanggan(pelanggan.getEmail_pelanggan())
                .password_pelanggan(pelanggan.getPassword_pelanggan())
                .nama_pelanggan(pelanggan.getNama_pelanggan())
                .alamat_pelanggan(pelanggan.getAlamat_pelanggan())
                .build();

        Pelanggan save = pelangganRepository.save(pelanggan1);
        return "data created";
    }

    @Override
    public List<PelangganResponse> getAll() {
        List<Pelanggan> all = pelangganRepository.findAll();
        List<PelangganResponse> pelangganResponses = all.stream().map(
                item -> {
                    Pelanggan id = findId(item.getId_pelanggan());
                    return PelangganResponse.builder()
                            .id_pelanggan(id.getId_pelanggan())
                            .email_pelanggan(id.getEmail_pelanggan())
                            .nama_pelanggan(id.getNama_pelanggan())
                            .build();
                }
        ).collect(Collectors.toList());
        return pelangganResponses;
    }

    @Override
    public Pelanggan update(Pelanggan pelanggan) {
        Optional<Pelanggan> byId = pelangganRepository.findById(pelanggan.getId_pelanggan());
        Pelanggan pelanggan1 = Pelanggan.builder()
                .id_pelanggan(byId.get().getId_pelanggan())
                .email_pelanggan(pelanggan.getEmail_pelanggan())
                .alamat_pelanggan(pelanggan.getAlamat_pelanggan())
                .nama_pelanggan(pelanggan.getNama_pelanggan())
                .password_pelanggan(pelanggan.getPassword_pelanggan())
                .build();

        Pelanggan save = pelangganRepository.save(pelanggan1);

        return save;
    }

    @Override
    public String delete(String id) {
        pelangganRepository.deleteById(id);
        return "Data terhapus";
    }

    @Override
    public Pelanggan findId(String id) {
        Optional<Pelanggan> byId = pelangganRepository.findById(id);
        Pelanggan pelanggan = Pelanggan.builder()
                .id_pelanggan(byId.get().getId_pelanggan())
                .password_pelanggan(byId.get().getPassword_pelanggan())
                .nama_pelanggan(byId.get().getNama_pelanggan())
                .alamat_pelanggan(byId.get().getAlamat_pelanggan())
                .email_pelanggan(byId.get().getEmail_pelanggan())
                .build();
        return pelanggan;
    }
}
