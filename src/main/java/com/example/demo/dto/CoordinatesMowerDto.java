package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CoordinatesMowerDto {

    private int x;
    private int y;
    private String orientation;
}