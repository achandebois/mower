package com.company.mower.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PositionStateUnitTest {

    @Test
    void rotateLeft_shouldDelegateRotation_andReturnNewPositionState() {
        //Given
        PositionState positionState = new PositionState(3, 2, Direction.NORTH);

        //When
        final PositionState nextPositionState = positionState.rotateLeft();

        //Then
        assertThat(nextPositionState.getX()).isEqualTo(3);
        assertThat(nextPositionState.getY()).isEqualTo(2);
        assertThat(nextPositionState.getDirection()).isEqualTo(Direction.WEST);
    }

    @Test
    void rotateRight_shouldDelegateRotation_andReturnNewPositionState() {
        //Given
        PositionState positionState = new PositionState(1, 5, Direction.EAST);

        //When
        final PositionState nextPositionState = positionState.rotateRight();

        //Then
        assertThat(nextPositionState.getX()).isEqualTo(1);
        assertThat(nextPositionState.getY()).isEqualTo(5);
        assertThat(nextPositionState.getDirection()).isEqualTo(Direction.SOUTH);
    }

    @ParameterizedTest(name = "{index} => currentDirection={0}, currentX={1}, currentY={2}, expectedX={3}, expectedX={4}")
    @CsvSource({
            "N, 2, 1, 2, 2",
            "S, 3, 4, 3, 3",
            "E, 4, 1, 5, 1",
            "W, 3, 1, 2, 1"
    })
    void move_shouldComputeNextCoordinatesAccordingToTheDirection_andReturnNewPositionState(
            char currentDirection,
            int currentX,
            int currentY,
            int expectedX,
            int expectedY
    ) {
        //Given
        PositionState positionState = new PositionState(currentX, currentY, Direction.forShortName(currentDirection));

        //When
        final PositionState nextPositionState = positionState.move();

        //Then
        assertThat(nextPositionState.getX()).isEqualTo(expectedX);
        assertThat(nextPositionState.getY()).isEqualTo(expectedY);
        assertThat(nextPositionState.getDirection()).isEqualTo(Direction.forShortName(currentDirection));
    }

    @Test
    void toString_shouldDisplayCoordinates_andShortNameDirection() {
        //Given
        PositionState positionState = new PositionState(1, 5, Direction.WEST);

        //When
        final String currentDisplayState = positionState.toString();

        //Then
        assertThat(currentDisplayState).isEqualTo("1 5 W");
    }
}
