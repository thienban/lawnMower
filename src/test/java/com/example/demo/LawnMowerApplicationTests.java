package com.example.demo;

import com.example.demo.dto.CoordinatesMowerDto;
import com.example.demo.dto.MowerDto;
import com.example.demo.entities.LawnDimension;
import com.example.demo.robotControl.Control;
import com.example.demo.robotControl.Direction;
import com.example.demo.service.CoordinateService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LawnMowerApplicationTests {

    @InjectMocks
    CoordinateService coordinateService;

    @Test
    void contextLoads() {
    }

    @Test
    void get_coordinates_test() {
        MowerDto mowerA = new MowerDto(new CoordinatesMowerDto(1, 2, "N"), "GAGAGAGAA");
        MowerDto mowerB = new MowerDto(new CoordinatesMowerDto(3, 3, "E"), "AADAADADDA");
        CoordinatesMowerDto coordinatesMowerDtoA = new CoordinatesMowerDto(1, 3, "N");
        CoordinatesMowerDto coordinatesMowerDtoB = new CoordinatesMowerDto(5, 1, "E");
        List<MowerDto> mowerDtoList = List.of(mowerA, mowerB);
        List<CoordinatesMowerDto> coordinatesMowerDtos = coordinateService.getCoordinates(new LawnDimension(5, 5), mowerDtoList);
        assertThat(coordinatesMowerDtos.get(0).getX()).isEqualTo(coordinatesMowerDtoA.getX());
        assertThat(coordinatesMowerDtos.get(0).getY()).isEqualTo(coordinatesMowerDtoA.getY());
        assertThat(coordinatesMowerDtos.get(0).getOrientation()).isEqualTo(coordinatesMowerDtoA.getOrientation());
        assertThat(coordinatesMowerDtos.get(1).getX()).isEqualTo(coordinatesMowerDtoB.getX());
        assertThat(coordinatesMowerDtos.get(1).getY()).isEqualTo(coordinatesMowerDtoB.getY());
        assertThat(coordinatesMowerDtos.get(1).getOrientation()).isEqualTo(coordinatesMowerDtoB.getOrientation());
    }

    @Test
    void execute_instruction_test() {
        CoordinatesMowerDto coordinatesMowerDtoA = new CoordinatesMowerDto(1, 2, "N");
        CoordinatesMowerDto coordinatesMowerDto = coordinateService
                .executeInstruction(coordinatesMowerDtoA, Control.MOVE_FORWARD, new LawnDimension(5, 5));
        assertThat(coordinatesMowerDto.getX()).isEqualTo(1);
        assertThat(coordinatesMowerDto.getY()).isEqualTo(3);
        assertThat(coordinatesMowerDto.getOrientation()).isEqualTo(Direction.N.toString());
    }

}
