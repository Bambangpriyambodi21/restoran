package com.rasaboga.RasaBoga.service;

import com.rasaboga.RasaBoga.model.request.TransaksiRequest;
import com.rasaboga.RasaBoga.model.response.TransaksiResponse;

import java.util.List;

public interface TransaksiService {
    TransaksiResponse create(TransaksiRequest transaksi);
    TransaksiResponse update(TransaksiRequest transaksi);
    List<TransaksiResponse> getAll();
    String delete(String id);
}
