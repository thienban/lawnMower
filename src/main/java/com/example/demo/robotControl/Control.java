package com.example.demo.robotControl;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Control {
    RIGHT('D', "Turn to right"),
    LEFT('G', "Turn to left"),
    MOVE_FORWARD('A', "Move forward");

    private char controlCode;
    private String label;
}
