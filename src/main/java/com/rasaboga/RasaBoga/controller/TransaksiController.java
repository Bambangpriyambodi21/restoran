package com.rasaboga.RasaBoga.controller;

import com.rasaboga.RasaBoga.model.request.TransaksiRequest;
import com.rasaboga.RasaBoga.model.response.TransaksiResponse;
import com.rasaboga.RasaBoga.service.TransaksiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TransaksiController {
    private final TransaksiService transaksiService;

    @PostMapping("/transaksi")
    public ResponseEntity<?> create(@RequestBody TransaksiRequest transaksi){
        TransaksiResponse transaksi1 = transaksiService.create(transaksi);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaksi1);
    }

    @PutMapping("/transaksi")
    public ResponseEntity<?> update(@RequestBody TransaksiRequest transaksi){
        TransaksiResponse update = transaksiService.update(transaksi);
        return ResponseEntity.status(HttpStatus.CREATED).body(update);
    }

    @GetMapping("/transaksi")
    public ResponseEntity<?> getall(){
        List<TransaksiResponse> all = transaksiService.getAll();
        return ResponseEntity.status(HttpStatus.CREATED).body(all);
    }

    @DeleteMapping("/transaksi/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        String delete = transaksiService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(delete);
    }
}
