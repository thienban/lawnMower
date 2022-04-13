package com.example.demo.controller;

import com.example.demo.dto.CoordinatesMowerDto;
import com.example.demo.dto.InstructionDto;
import com.example.demo.service.CoordinateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MowerController {

    CoordinateService coordinateService;

    @GetMapping("/coordinates")
    public List<CoordinatesMowerDto> getCoordinates(@RequestBody InstructionDto instructionDto) {
        return coordinateService.getCoordinates(instructionDto.getDimensions(), instructionDto.getMowers());
    }
}
