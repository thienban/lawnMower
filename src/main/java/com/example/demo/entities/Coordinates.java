package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Coordinates {
    private int x;
    private int y;

    public boolean isInside(Coordinates coordinates){
        return coordinates.getX() >= 0
                && coordinates.getY() >= 0
                && coordinates.getX() <= this.x
                && coordinates.getY() <= this.y;
    }

}
