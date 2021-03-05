package com.company.mower.service;

import com.company.mower.domain.Lawn;
import com.company.mower.domain.Mower;
import com.company.mower.model.MowerConfigurationModel;
import com.company.mower.reader.FileMowerReader;
import lombok.SneakyThrows;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MowerService {

    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    @SneakyThrows
    public List<Mower> run(String filename) {

        final MowerConfigurationModel mowerConfiguration = FileMowerReader.read(filename);

        Lawn lawn = mowerConfiguration.getLawn();
        List<Mower> mowers = mowerConfiguration.getMowers();

        lawn.initMowerPositions(
                mowers.stream()
                        .map(Mower::getCurrentPositionState)
                        .collect(Collectors.toSet())
        );

        runMowers(lawn, mowers);

        displayFinalMowersState(mowers);

        return mowers;
    }


    private void runMowers(Lawn lawn, List<Mower> mowers) throws InterruptedException {
        mowers.forEach(mower -> executorService.submit(() -> mower.move(lawn)));

        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);
    }

    private void displayFinalMowersState(List<Mower> mowers) {
        mowers.forEach(mower -> System.out.println(mower.toString()));
    }
}
