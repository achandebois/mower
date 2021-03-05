package com.company.mower.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"x", "y"})
public class PositionState {
    private final static int INCREMENT = 1;
    private final int x;
    private final int y;
    private final Direction direction;

    public PositionState move() {
        switch (direction) {
            case NORTH:
                return new PositionState(x, y + INCREMENT, direction);
            case SOUTH:
                return new PositionState(x, y - INCREMENT, direction);
            case EAST:
                return new PositionState(x + INCREMENT, y, direction);
            case WEST:
                return new PositionState(x - INCREMENT, y, direction);
            default:
                throw new IllegalArgumentException("Invalid direction to move the position");
        }
    }

    public PositionState rotateLeft() {
        return new PositionState(x, y, direction.rotateLeft());
    }

    public PositionState rotateRight() {
        return new PositionState(x, y, direction.rotateRight());
    }

    @Override
    public String toString() {
        return x + " " + y + " " + direction.getShortName();
    }
}
