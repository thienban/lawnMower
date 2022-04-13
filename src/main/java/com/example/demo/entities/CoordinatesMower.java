package com.example.demo.entities;

import com.example.demo.robotControl.Direction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CoordinatesMower {

    private Coordinates coordinates;
    private Direction orientation;
}
