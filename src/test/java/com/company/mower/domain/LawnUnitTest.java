package com.company.mower.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LawnUnitTest {

    @Test
    void initMowerPositions_shouldAddPositionState() {
        //Given
        Lawn lawn = new Lawn(5, 5);
        PositionState firstPositionState = new PositionState(3, 3, Direction.NORTH);
        PositionState secondPositionState = new PositionState(1, 2, Direction.SOUTH);

        //When
        lawn.initMowerPositions(Stream.of(firstPositionState, secondPositionState).collect(Collectors.toSet()));

        //Then
        assertThat(lawn.getMowerPositions()).containsExactlyInAnyOrder(firstPositionState, secondPositionState);
    }

    @Test
    void updateMowerPosition_shouldRemoveCurrentPositionState_andAddNewPositionState() {
        //Given
        Lawn lawn = new Lawn(5, 5);
        PositionState currentPositionState = new PositionState(3, 3, Direction.NORTH);
        lawn.initMowerPositions(Stream.of(currentPositionState).collect(Collectors.toSet()));
        PositionState nextPositionState = new PositionState(1, 2, Direction.SOUTH);

        //When
        final boolean updated = lawn.updateMowerPosition(currentPositionState, nextPositionState);

        //Then
        assertThat(updated).isTrue();
        assertThat(lawn.getMowerPositions()).containsExactlyInAnyOrder(nextPositionState);
    }

    @Test
    void updateMowerPosition_withOccupiedPosition_shouldNotUpdate() {
        //Given
        Lawn lawn = new Lawn(5, 5);
        PositionState firstPositionState = new PositionState(3, 3, Direction.NORTH);
        PositionState secondPositionState = new PositionState(3, 4, Direction.SOUTH);
        lawn.initMowerPositions(Stream.of(firstPositionState, secondPositionState).collect(Collectors.toSet()));
        PositionState nextPositionState = new PositionState(3, 4, Direction.NORTH);

        //When
        final boolean updated = lawn.updateMowerPosition(firstPositionState, nextPositionState);

        //Then
        assertThat(updated).isFalse();
        assertThat(lawn.getMowerPositions()).containsExactlyInAnyOrder(firstPositionState, secondPositionState);
    }

    @ParameterizedTest(name = "{index} => currentX={0}, currentY={1}, nextX={2}, nextY={3}")
    @CsvSource({
            "5, 5, 5, 6",
            "5, 0, 5, -1",
            "5, 5, 6, 5",
            "0, 5, -1, 5"
    })
    void updateMowerPosition_withPositionStateOutsideTheLawn_shouldNotUpdate(int currentX, int currentY, int nextX, int nextY) {
        //Given
        Lawn lawn = new Lawn(5, 5);
        PositionState currentPositionState = new PositionState(currentX, currentY, Direction.NORTH);
        lawn.initMowerPositions(Stream.of(currentPositionState).collect(Collectors.toSet()));
        PositionState nextPositionState = new PositionState(nextX, nextY, Direction.NORTH);

        //When
        final boolean updated = lawn.updateMowerPosition(currentPositionState, nextPositionState);

        //Then
        assertThat(updated).isFalse();
        assertThat(lawn.getMowerPositions()).containsExactlyInAnyOrder(currentPositionState);
    }
}
