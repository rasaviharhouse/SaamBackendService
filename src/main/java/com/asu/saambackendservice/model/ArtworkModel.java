package com.asu.saambackendservice.model;

import lombok.Data;

@Data
public class ArtworkModel {

    String artworkId;

    String description;

    String imageUrl;

    String productionDate;

    String title;

    ClassificationModel classification;

    ArtistModel artist;

    DimensionModel dimension;

    PositionModel position;

}
