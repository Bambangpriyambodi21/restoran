package com.rasaboga.RasaBoga.controller;

import com.rasaboga.RasaBoga.entity.Pelanggan;
import com.rasaboga.RasaBoga.model.response.PelangganResponse;
import com.rasaboga.RasaBoga.service.PelangganService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@CrossOrigin
public class PelangganController {
    private final PelangganService pelangganService;

    @PostMapping(path = "/pelanggan")
    public ResponseEntity<?> create(@RequestBody Pelanggan pelanggan){
        Pelanggan pelanggan1 = pelangganService.create(pelanggan);
        return ResponseEntity.status(HttpStatus.CREATED).body(pelanggan1);
    }

    @GetMapping(path = "/pelanggan")
    public ResponseEntity<?> getAll(){
        List<PelangganResponse> all = pelangganService.getAll();
        return ResponseEntity.status(HttpStatus.CREATED).body(all);
    }

    @PutMapping(path = "/pelanggan")
    public ResponseEntity<?> update(@RequestBody Pelanggan pelanggan){
        Pelanggan update = pelangganService.update(pelanggan);
        return ResponseEntity.status(HttpStatus.CREATED).body(update);
    }

    @DeleteMapping(path = "/pelanggan/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        String delete = pelangganService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(delete);
    }

}
