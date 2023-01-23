package com.asu.saambackendservice.service;

import com.asu.saambackendservice.model.ArtistModel;
import com.asu.saambackendservice.model.ArtworkModel;

import java.util.ArrayList;

public interface SaamBackendService {

    ArrayList<ArtistModel> getArtistData(String name);

    ArrayList<ArtworkModel> getArtworkData(String name);

    ArrayList<ArtworkModel> getRelatedData(ArrayList<String> keywords);

}
