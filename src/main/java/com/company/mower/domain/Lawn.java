package com.company.mower.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public class Lawn {
    private final int x;
    private final int y;
    private Set<PositionState> mowerPositions = new HashSet<>();

    public void initMowerPositions(Set<PositionState> positions) {
        mowerPositions = positions;
    }

    public synchronized boolean updateMowerPosition(PositionState oldPositionState, PositionState nextPositionState) {
        if (isPositionNotBusy(nextPositionState) && isPositionInside(nextPositionState)) {
            mowerPositions.remove(oldPositionState);
            mowerPositions.add(nextPositionState);
            return true;
        }

        return false;
    }

    private boolean isPositionNotBusy(final PositionState position) {
        return !mowerPositions.contains(position);
    }

    private boolean isPositionInside(final PositionState positionState) {
        return (positionState.getX() >= 0 && positionState.getX() <= x)
                && (positionState.getY() >= 0 && positionState.getY() <= y);
    }
}
