package com.company.mower.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Mower {

    private PositionState currentPositionState;
    private List<Command> commands;

    public void move(Lawn lawn) {
        commands.forEach(command -> {
            PositionState nextPositionState = command.getComputeNextPositionState().apply(currentPositionState);

            if (isNextPositionStateIsRotation(nextPositionState)) {
                currentPositionState = nextPositionState;
            } else {
                final boolean busyPositionUpdated = lawn.updateMowerPosition(currentPositionState, nextPositionState);
                if (busyPositionUpdated) {
                    currentPositionState = nextPositionState;
                }
            }
        });
    }

    @Override
    public String toString() {
        return currentPositionState.toString();
    }


    private boolean isNextPositionStateIsRotation(PositionState nextPositionState) {
        return currentPositionState.equals(nextPositionState);
    }

}
