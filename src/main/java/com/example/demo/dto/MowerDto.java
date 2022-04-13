package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MowerDto {
    private CoordinatesMowerDto position;
    private String instructions;
}
