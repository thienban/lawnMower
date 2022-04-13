package com.example.demo.service;

import com.example.demo.dto.CoordinatesMowerDto;
import com.example.demo.dto.MowerDto;
import com.example.demo.entities.Coordinates;
import com.example.demo.entities.LawnDimension;
import com.example.demo.exception.MowerException;
import com.example.demo.robotControl.Control;
import com.example.demo.robotControl.Direction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoordinateService {

    public static final String ORIENTATION_ERROR = "incorrect orientation";
    public static final String INSTRUCTION_ERROR = "incorrect instruction";
    public static final String POSITION_ERROR = "incorrect position";

    /**
     * Move Mower
     *
     * @param coordinatesMower initial coordinates mower
     * @param lawnDimension    lawn dimension
     * @return new mower coordinates
     * @throws MowerException postion error
     */
    public static Coordinates moveMower(CoordinatesMowerDto coordinatesMower, LawnDimension lawnDimension) throws MowerException {
        Coordinates coordinates;
        Coordinates lawnCoordinates = new Coordinates(lawnDimension.getHeight(), lawnDimension.getWidth());
        int x, y;
        switch (Direction.valueOf(coordinatesMower.getOrientation())) {
            case N:
                x = coordinatesMower.getX();
                y = coordinatesMower.getY() + 1;
                break;
            case E:
                x = coordinatesMower.getX() + 1;
                y = coordinatesMower.getY();
                break;
            case S:
                x = coordinatesMower.getX();
                y = coordinatesMower.getY() - 1;
                break;
            case W:
                x = coordinatesMower.getX() - 1;
                y = coordinatesMower.getY();
                break;
            default:
                throw new MowerException(POSITION_ERROR);
        }
        coordinates = new Coordinates(x, y);
        if (coordinates.isInside(lawnCoordinates)) {
            return new Coordinates(coordinatesMower.getX(), coordinatesMower.getY());
        }
        return coordinates;
    }

    /**
     * Turn mower to right
     *
     * @param direction mower direction
     * @return new direction
     * @throws MowerException orientation error
     */
    public static String turnRight(String direction) throws MowerException {
        Direction newDirection;
        switch (Direction.valueOf(direction)) {
            case N:
                newDirection = Direction.E;
                break;
            case E:
                newDirection = Direction.S;
                break;
            case S:
                newDirection = Direction.W;
                break;
            case W:
                newDirection = Direction.N;
                break;
            default:
                throw new MowerException(ORIENTATION_ERROR);
        }
        return newDirection.toString();
    }

    /**
     * Turn mower to left
     *
     * @param direction mower direction
     * @return new direction
     * @throws MowerException orientation error
     */
    public static String turnLeft(String direction) throws MowerException {
        Direction newDirection;
        switch (Direction.valueOf(direction)) {
            case N:
                newDirection = Direction.W;
                break;
            case E:
                newDirection = Direction.N;
                break;
            case S:
                newDirection = Direction.E;
                break;
            case W:
                newDirection = Direction.S;
                break;
            default:
                throw new MowerException(ORIENTATION_ERROR);
        }
        return newDirection.toString();
    }

    private static Control getControlCode(char mowerControl) {
        for (Control control : Control.values()) {
            if (control.getControlCode() == mowerControl) {
                return control;
            }
        }
        return null;
    }

    public CoordinatesMowerDto executeInstruction(CoordinatesMowerDto coordinatesMowerDto, Control control, LawnDimension lawnDimension) throws MowerException {

        switch (control) {
            case MOVE_FORWARD:
                Coordinates coordinates = moveMower(coordinatesMowerDto, lawnDimension);
                coordinatesMowerDto.setX(coordinates.getX());
                coordinatesMowerDto.setY(coordinates.getY());
                break;
            case RIGHT:
                coordinatesMowerDto.setOrientation(turnRight(coordinatesMowerDto.getOrientation()));
                break;
            case LEFT:
                coordinatesMowerDto.setOrientation(turnLeft(coordinatesMowerDto.getOrientation()));
                break;
            default:
                throw new MowerException(INSTRUCTION_ERROR);
        }
        return coordinatesMowerDto;
    }

    public List<CoordinatesMowerDto> getCoordinates(LawnDimension lawnDimension, List<MowerDto> mowers) {
        return mowers.stream().map(mower -> {
            List<Control> controls = parseControl(mower.getInstructions());
            controls.forEach(control -> executeInstruction(mower.getPosition(), control, lawnDimension));
            return mower.getPosition();
        }).collect(Collectors.toList());
    }

    private List<Control> parseControl(String mowerControl) {
        List<Control> listInstruction = new ArrayList<>();
        for (char instruction : mowerControl.toCharArray()) {
            listInstruction.add(getControlCode(instruction));
        }
        return listInstruction;
    }
}
