package com.company.mower.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectionUnitTest {

    @ParameterizedTest
    @ValueSource(chars = {'N', 'S', 'E', 'W'})
    void forShortName_shouldLookUpEnum(char shortName) {
        //Given

        //When
        final Direction direction = Direction.forShortName(shortName);

        //Then
        assertThat(direction).isNotNull();
    }

    @Test
    void forShortName_withInvalidShortName_shouldThrowException() {
        //Given
        char invalidShortName = 'Q';

        //When
        //Then
        Assertions.assertThatThrownBy(() -> Direction.forShortName(invalidShortName))
                .isInstanceOf(NoSuchElementException.class);
    }

    @ParameterizedTest(name = "{index} => currentDirection={0}, expectedDirection={1}")
    @CsvSource({
            "N, W",
            "W, S",
            "S, E",
            "E, N"
    })
    void rotateLeft_shouldChangeDirectionInInvertClockwise(char currentDirection, char expectedDirection) {
        //Given
        final Direction northDirection = Direction.forShortName(currentDirection);

        //When
        final Direction direction = northDirection.rotateLeft();

        //Then
        assertThat(direction).isEqualTo(Direction.forShortName(expectedDirection));
    }

    @ParameterizedTest(name = "{index} => currentDirection={0}, expectedDirection={1}")
    @CsvSource({
            "N, E",
            "E, S",
            "S, W",
            "W, N"
    })
    void rotateRight_shouldChangeDirectionInClockwise(char currentDirection, char expectedDirection) {
        //Given
        final Direction northDirection = Direction.forShortName(currentDirection);

        //When
        final Direction direction = northDirection.rotateRight();

        //Then
        assertThat(direction).isEqualTo(Direction.forShortName(expectedDirection));
    }
}
