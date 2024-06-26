package com.rasaboga.RasaBoga.service.impl;

import com.rasaboga.RasaBoga.entity.Pelanggan;
import com.rasaboga.RasaBoga.model.request.SearchPelangganRequest;
import com.rasaboga.RasaBoga.model.response.PelangganResponse;
import com.rasaboga.RasaBoga.repository.PelangganRepository;
import com.rasaboga.RasaBoga.service.PelangganService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PelangganServiceImpl implements PelangganService {

    private final PelangganRepository pelangganRepository;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Pelanggan create(Pelanggan pelanggan) {
        Pelanggan pelanggan1 = Pelanggan.builder()
                .email_pelanggan(pelanggan.getEmail_pelanggan())
                .password_pelanggan(pelanggan.getPassword_pelanggan())
                .nama_pelanggan(pelanggan.getNama_pelanggan())
                .alamat_pelanggan(pelanggan.getAlamat_pelanggan())
                .build();

        Pelanggan save = pelangganRepository.save(pelanggan1);
        return save;
    }

    @Override
    public List<PelangganResponse> getAll(SearchPelangganRequest searchPelangganRequest) {
        PageRequest pageRequest = PageRequest.of(searchPelangganRequest.getHalaman(), searchPelangganRequest.getUkuran());
        Specification<Pelanggan> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (searchPelangganRequest.getPelanggan()!=null){
                Predicate predicate = criteriaBuilder.like(root.get("nama_pelanggan"), "%"+searchPelangganRequest.getPelanggan()+"%");
                predicates.add(predicate);
            }

            return query.where(predicates.toArray(new jakarta.persistence.criteria.Predicate[]{})).getRestriction();
        };
        Page<Pelanggan> all = pelangganRepository.findAll(specification, pageRequest);
        List<PelangganResponse> pelangganResponses = all.getContent().stream()
                .map(pelanggan -> PelangganResponse.builder()
                        .nama_pelanggan(pelanggan.getNama_pelanggan())
                        .email_pelanggan(pelanggan.getEmail_pelanggan())
                        .id_pelanggan(pelanggan.getId_pelanggan())
                        .build())
                .collect(Collectors.toList());
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
