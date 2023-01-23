package com.asu.saambackendservice.controller;

import com.asu.saambackendservice.model.ArtistModel;
import com.asu.saambackendservice.model.ArtworkModel;
import com.asu.saambackendservice.service.SaamBackendService;
import com.mindstix.web.rest.baseline.common.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping("/saam")
public class SaamController {

    @Autowired
    SaamBackendService saamService;

    @GetMapping(path = "/artist")
    public ApiResponse<ArrayList<ArtistModel>> getArtistData(@RequestParam String name) {
        ApiResponse<ArrayList<ArtistModel>> apiResponse = new ApiResponse<>();
        apiResponse.setData(saamService.getArtistData(name));
        apiResponse.setStatus(HttpStatus.OK.value());
        return apiResponse;
    }

    @GetMapping(path = "/artwork")
    public ApiResponse<ArrayList<ArtworkModel>> getArtworkData(@RequestParam String name) {
        ApiResponse<ArrayList<ArtworkModel>> apiResponse = new ApiResponse<>();
        apiResponse.setData(saamService.getArtworkData(name));
        apiResponse.setStatus(HttpStatus.OK.value());
        return apiResponse;
    }

    @GetMapping(path = "/general")
    public ApiResponse<ArrayList<ArtworkModel>> getRelatedData(@RequestParam ArrayList<String> keywords) {
        ApiResponse<ArrayList<ArtworkModel>> apiResponse = new ApiResponse<>();
        apiResponse.setData(saamService.getRelatedData(keywords));
        apiResponse.setStatus(HttpStatus.OK.value());
        return apiResponse;
    }

}
