package com.company.mower.reader;

import com.company.mower.domain.*;
import com.company.mower.model.MowerConfigurationModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FileMowerReaderUnitTest {

    @Test
    void toCommands_shouldCreateListOfCommands() {
        //Given
        final String commandLine = "FLR";

        //When
        final List<Command> commands = FileMowerReader.toCommands(commandLine);

        //Then
        assertThat(commands).containsExactly(Command.FORWARD, Command.TURN_LEFT, Command.TURN_RIGHT);
    }


    @Test
    void toPositionState_shouldCreatePositionState() {
        //Given
        final String mowerPositionStateLine = "3 1 W";

        //When
        final PositionState positionState = FileMowerReader.toPositionState(mowerPositionStateLine);

        //Then
        assertThat(positionState.getX()).isEqualTo(3);
        assertThat(positionState.getY()).isEqualTo(1);
        assertThat(positionState.getDirection()).isEqualTo(Direction.WEST);
    }

    @Test
    void toMower_shouldCreateMower() {
        //Given
        final String mowerPositionStateLine = "3 1 W";
        final String commandLine = "FLR";

        //When
        final Mower mower = FileMowerReader.toMower(mowerPositionStateLine, commandLine);

        //Then
        assertThat(mower.getCommands()).containsExactly(Command.FORWARD, Command.TURN_LEFT, Command.TURN_RIGHT);
        assertThat(mower.getCurrentPositionState().getX()).isEqualTo(3);
        assertThat(mower.getCurrentPositionState().getY()).isEqualTo(1);
        assertThat(mower.getCurrentPositionState().getDirection()).isEqualTo(Direction.WEST);
    }

    @Test
    void toLawn_shouldCreateLawn() {
        //Given
        String lawnLine = "3 1";

        //When
        final Lawn lawn = FileMowerReader.toLawn(lawnLine);

        //Then
        assertThat(lawn.getX()).isEqualTo(3);
        assertThat(lawn.getY()).isEqualTo(1);
    }

    @Test
    void read_shouldCreateMowerConfiguration() {
        //Given
        final String fileName = "src/test/resources/test-input.txt";

        //When
        final MowerConfigurationModel mowerConfigurationModel = FileMowerReader.read(fileName);

        //Then
        assertThat(mowerConfigurationModel.getLawn()).isNotNull();
        assertThat(mowerConfigurationModel.getMowers()).isNotEmpty();
    }
}
