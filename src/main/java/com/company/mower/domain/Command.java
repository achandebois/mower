package com.company.mower.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.function.Function;

@Getter
@AllArgsConstructor
public enum Command {
    FORWARD('F', PositionState::move),
    TURN_LEFT('L', PositionState::rotateLeft),
    TURN_RIGHT('R', PositionState::rotateRight);

    private final char shortName;
    private final Function<PositionState, PositionState> computeNextPositionState;

    public static Command forShortName(final char shortName) {
        return Arrays.stream(Command.values())
                .filter(command -> command.getShortName() == shortName)
                .findFirst()
                .orElseThrow();
    }
}
