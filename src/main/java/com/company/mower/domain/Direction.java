package com.company.mower.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Direction {
    NORTH('N', 'W', 'E'),
    EAST('E', 'N', 'S'),
    SOUTH('S', 'E', 'W'),
    WEST('W', 'S', 'N');

    private final char shortName;
    private final char previous;
    private final char next;

    public static Direction forShortName(final char shortName) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.getShortName() == shortName)
                .findFirst()
                .orElseThrow();
    }

    public Direction rotateLeft() {
        return forShortName(previous);
    }

    public Direction rotateRight() {
        return forShortName(next);
    }
}
