package com.company.mower.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Direction {
    NORTH(1, 'N'),
    EAST(2, 'E'),
    SOUTH(3, 'S'),
    WEST(4, 'W');

    private final int code;
    private final char shortName;

    public static Direction forShortName(final char shortName) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.getShortName() == shortName)
                .findFirst()
                .orElseThrow();
    }

    public Direction rotateLeft() {
        return forCode(this.getCode() - 1);
    }

    public Direction rotateRight() {
        return forCode(this.getCode() + 1);
    }

    //TODO refactor find a smarter way to do it
    private static Direction forCode(final int code) {
        if (code == 0) {
            return WEST;
        } else if (code == 5) {
            return NORTH;
        } else {
            return Arrays.stream(Direction.values())
                    .filter(direction -> direction.getCode() == code)
                    .findFirst()
                    .orElseThrow();
        }
    }
}
