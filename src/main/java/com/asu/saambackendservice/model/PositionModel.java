package com.asu.saambackendservice.model;

import lombok.Data;

@Data
public class PositionModel {

    String positionId;

    FloorModel floor;

    RoomModel room;
}
