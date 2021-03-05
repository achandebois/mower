package com.company.mower.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

public class CommandUnitTest {

    @ParameterizedTest
    @ValueSource(chars = {'F', 'L', 'R'})
    void forShortName_shouldLookUpEnum(char shortName) {
        //Given

        //When
        final Command command = Command.forShortName(shortName);

        //Then
        assertThat(command).isNotNull();
    }

    @Test
    void forShortName_withInvalidShortName_shouldThrowException() {
        //Given
        char invalidShortName = 'Q';

        //When
        //Then
        Assertions.assertThatThrownBy(() -> Command.forShortName(invalidShortName))
                .isInstanceOf(NoSuchElementException.class);
    }
}
