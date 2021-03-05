package com.company.mower.service;

import com.company.mower.domain.Direction;
import com.company.mower.domain.Mower;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MowerServiceUnitTest {

    @Test
    void run_shouldReadConfigurationFromFile_andMoveMowersOverLawn() {
        //Given
        MowerService mowerService = new MowerService();

        //When
        final List<Mower> mowers = mowerService.run("src/test/resources/input.txt");

        //Then
        assertThat(mowers).isNotEmpty();
        assertThat(mowers).hasSize(2);
        assertThat(mowers.get(0).getCurrentPositionState().getX()).isEqualTo(1);
        assertThat(mowers.get(0).getCurrentPositionState().getY()).isEqualTo(3);
        assertThat(mowers.get(0).getCurrentPositionState().getDirection()).isEqualTo(Direction.NORTH);
        assertThat(mowers.get(1).getCurrentPositionState().getX()).isEqualTo(5);
        assertThat(mowers.get(1).getCurrentPositionState().getY()).isEqualTo(1);
        assertThat(mowers.get(1).getCurrentPositionState().getDirection()).isEqualTo(Direction.EAST);
    }
}
