package com.asu.saambackendservice.service.impl;

import com.asu.saambackendservice.model.ArtistModel;
import com.asu.saambackendservice.model.ArtworkModel;
import com.asu.saambackendservice.model.ClassificationModel;
import com.asu.saambackendservice.service.SaamBackendService;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import static com.asu.saambackendservice.util.QueryGenerationUtil.*;

@Service
public class SaamBackendServiceImpl implements SaamBackendService {

    @Value("${artist.url}")
    private String artistUrl;

    @Value("${artwork.url}")
    private String artworkUrl;

    @Value("${namespace}")
    private String namespace;

    @Override
    public ArrayList<ArtistModel> getArtistData(String name) {
        return fetchArtists(generateArtistQuery(name));
    }

    @Override
    public ArrayList<ArtworkModel> getArtworkData(String name) {
        return fetchArtworks(generateArtWorkQuery(name));
    }

    @Override
    public ArrayList<ArtworkModel> getRelatedData(ArrayList<String> keywords) {
        ArrayList<ArtworkModel> artworks = new ArrayList<>();
        for (String s : keywords) {
            artworks.addAll(fetchArtworks(generateArtWorkQuery(s)));
        }
        return artworks;
    }

    public ArrayList<ArtworkModel> fetchArtworks(String requestQuery) {
        ArrayList<ArtworkModel> artworks = new ArrayList<>();
        String query = artworkUrl + "/" + namespace + "/" + "sparql";
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(query, requestQuery);
        ResultSet result = queryExecution.execSelect();
        HashSet<String> uniqueRows = new HashSet<>();
        try {
            while (result.hasNext()) {
                ArtworkModel artwork = new ArtworkModel();
                ArtistModel artist = new ArtistModel();
                ClassificationModel classification = new ClassificationModel();
                QuerySolution solution = result.nextSolution();
                if (solution.get("title") != null) {
                    if (uniqueRows.contains(solution.get("title").toString())) {
                        continue;
                    }
                    artwork.setTitle(solution.get("title").toString());
                    uniqueRows.add(solution.get("title").toString());
                }
                if (solution.get("classificationID") != null) {
                    classification.setClassificationId(solution.get("classificationID").toString());
                }
                if (solution.get("artistId") != null) {
                    artist.setArtistId(solution.get("artistId").toString());
                }
                if (solution.get("artistName") != null) {
                    artist.setArtistName(solution.get("artistName").toString());
                }
                if (solution.get("url") != null) {
                    artwork.setImageUrl(solution.get("url").toString());
                }
                if (solution.get("des") != null) {
                    artwork.setDescription(solution.get("des").toString());
                }
                if (solution.get("title") != null) {
                    artwork.setTitle(solution.get("title").toString());
                }
                if (solution.get("productionDate") != null) {
                    artwork.setProductionDate(solution.get("productionDate").toString());
                }

                if (solution.get("classification") != null) {
                    classification.setClassification(solution.get("classification").toString());

                }
                if (solution.get("subClassification") != null) {
                    classification.setSubClassification(solution.get("subClassification").toString());
                }
                if (solution.get("medium") != null) {
                    classification.setMediums(solution.get("medium").toString());
                }
                if (classification != null) {
                    artwork.setClassification(classification);
                }
                if (artist != null) {
                    artwork.setArtist(artist);
                }
                artworks.add(artwork);
            }
        } finally {
            queryExecution.close();
        }
        return artworks;
    }

    public ArrayList<ArtistModel> fetchArtists(String requestQuery) {
        ArrayList<ArtistModel> artists = new ArrayList<>();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(artistUrl + "/" + namespace + "/" + "sparql", requestQuery);
        ResultSet result = queryExecution.execSelect();
        try {
            while (result.hasNext()) {
                ArtistModel artist = new ArtistModel();
                QuerySolution solution = result.nextSolution();
                if (solution.get("artistId") != null) {
                    artist.setArtistId(solution.get("artistId").toString());
                }
                if (solution.get("artistName") != null) {
                    artist.setArtistName(solution.get("artistName").toString());
                }
                if (solution.get("nationalityId") != null) {
                    artist.setNationalityId(solution.get("nationalityId").toString());
                }
                if (solution.get("deathDate") != null) {
                    artist.setDeathDate(solution.get("deathDate").toString());
                }
                if (solution.get("birthDate") != null) {
                    artist.setBirthDate(solution.get("birthDate").toString());
                }
                if (solution.get("biography") != null) {
                    artist.setBiography(solution.get("biography").toString());
                }
                if (solution.get("imageLink") != null) {
                    artist.setImageLink(solution.get("imageLink").toString());
                }
                printArtistName(solution);
                artists.add(artist);
            }
        } finally {
            queryExecution.close();
        }
        return artists;
    }

    private static void printArtistName(QuerySolution solution) {
        for (Iterator<String> it = solution.varNames(); it.hasNext(); ) {
            String x = it.next();
            System.out.println(x);
        }
    }

}