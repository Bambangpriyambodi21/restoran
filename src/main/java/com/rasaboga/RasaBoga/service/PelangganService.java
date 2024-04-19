package com.rasaboga.RasaBoga.service;

import com.rasaboga.RasaBoga.entity.Pelanggan;
import com.rasaboga.RasaBoga.model.request.SearchPelangganRequest;
import com.rasaboga.RasaBoga.model.response.PelangganResponse;

import java.util.List;

public interface PelangganService {
    Pelanggan create(Pelanggan pelanggan);

    List<PelangganResponse> getAll(SearchPelangganRequest searchPelangganRequest);
    Pelanggan update(Pelanggan pelanggan);
    String delete(String id);
    Pelanggan findId(String id);
}
