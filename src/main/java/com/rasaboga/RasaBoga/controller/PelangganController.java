package com.rasaboga.RasaBoga.controller;

import com.rasaboga.RasaBoga.entity.Pelanggan;
import com.rasaboga.RasaBoga.model.request.SearchPelangganRequest;
import com.rasaboga.RasaBoga.model.response.PagingResponse;
import com.rasaboga.RasaBoga.model.response.PelangganResponse;
import com.rasaboga.RasaBoga.model.response.WebResponse;
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
        WebResponse<Object> webResponse= WebResponse.builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Succes Create Data")
                .data(pelanggan1)
                .build();
        return ResponseEntity.ok(webResponse);
    }

    @GetMapping(path = "/pelanggan")
    public ResponseEntity<?> getAll(@RequestParam(required = false, defaultValue = "0") Integer halaman,
                                    @RequestParam(required = false, defaultValue = "10") Integer ukuran,
                                    @RequestParam(required = false) String namaPelanggan){
        SearchPelangganRequest searchPelangganRequest = SearchPelangganRequest.builder()
                .pelanggan(namaPelanggan)
                .halaman(halaman)
                .ukuran(ukuran)
                .build();
        List<PelangganResponse> all = pelangganService.getAll(searchPelangganRequest);
        WebResponse<Object> build = WebResponse.builder()
                .message("Get All Data")
                .status(HttpStatus.OK.getReasonPhrase())
                .data(all)
                .paging(PagingResponse.builder()
                        .totalElements(1l)
                        .size(10)
                        .page(1)
                        .totalPage(1)
                        .build())
                .build();
        return ResponseEntity.ok(build);
    }

    @PutMapping(path = "/pelanggan")
    public ResponseEntity<?> update(@RequestBody Pelanggan pelanggan){
        Pelanggan update = pelangganService.update(pelanggan);
        WebResponse<Object> build = WebResponse.builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Data Updated")
                .data(update)
                .build();
        return ResponseEntity.ok(build);
    }

    @DeleteMapping(path = "/pelanggan/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        String delete = pelangganService.delete(id);
        WebResponse<Object> build = WebResponse.builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Data Deleted")
                .data(delete)
                .build();
        return ResponseEntity.ok(build);
    }

}
