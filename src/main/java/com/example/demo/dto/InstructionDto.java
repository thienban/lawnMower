package com.example.demo.dto;

import com.example.demo.entities.LawnDimension;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class InstructionDto {
    private LawnDimension dimensions;
    private List<MowerDto> mowers;
}
