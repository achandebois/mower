package com.company.mower.domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class MowerUnitTest {

    @Test
    void move_withOnlyRotation_shouldNotMoveTheMowerOnTheLawn() {
        //Given
        Lawn lawn = new Lawn(5, 5);
        PositionState positionState = new PositionState(3, 2, Direction.NORTH);
        List<Command> commands = List.of(Command.TURN_LEFT);
        Mower mower = new Mower(positionState, commands);

        //When
        mower.move(lawn);

        //Then
        assertThat(mower.getCurrentPositionState().getX()).isEqualTo(3);
        assertThat(mower.getCurrentPositionState().getY()).isEqualTo(2);
        assertThat(mower.getCurrentPositionState().getDirection()).isEqualTo(Direction.WEST);
        assertThat(lawn.getMowerPositions()).isEmpty();
    }

    @Test
    void move_withABusyPosition_shouldNotMoveTheMowerOnTheLawn() {
        //Given
        Lawn lawn = new Lawn(5, 5);
        final PositionState busyPositionState = new PositionState(3, 3, Direction.NORTH);
        lawn.initMowerPositions(Set.of(busyPositionState));
        PositionState positionState = new PositionState(3, 2, Direction.NORTH);
        List<Command> commands = List.of(Command.FORWARD);
        Mower mower = new Mower(positionState, commands);

        //When
        mower.move(lawn);

        //Then
        assertThat(mower.getCurrentPositionState().getX()).isEqualTo(3);
        assertThat(mower.getCurrentPositionState().getY()).isEqualTo(2);
        assertThat(mower.getCurrentPositionState().getDirection()).isEqualTo(Direction.NORTH);
        assertThat(lawn.getMowerPositions()).containsExactlyInAnyOrder(busyPositionState);
    }

    @Test
    void move_shouldMoveTheMowerOnTheLawn() {
        //Given
        Lawn lawn = new Lawn(5, 5);
        PositionState positionState = new PositionState(3, 2, Direction.NORTH);
        List<Command> commands = List.of(Command.FORWARD, Command.TURN_LEFT, Command.TURN_RIGHT, Command.FORWARD);
        Mower mower = new Mower(positionState, commands);

        //When
        mower.move(lawn);

        //Then
        assertThat(mower.getCurrentPositionState().getX()).isEqualTo(3);
        assertThat(mower.getCurrentPositionState().getY()).isEqualTo(4);
        assertThat(mower.getCurrentPositionState().getDirection()).isEqualTo(Direction.NORTH);
        assertThat(lawn.getMowerPositions()).containsExactly(mower.getCurrentPositionState());
    }
}
