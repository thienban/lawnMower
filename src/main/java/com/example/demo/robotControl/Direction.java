package com.example.demo.robotControl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Direction {
    N("north"),
    E("east"),
    W("west"),
    S("south");

    private String label;
}
