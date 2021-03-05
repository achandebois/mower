package com.company.mower.reader;

import com.company.mower.domain.*;
import com.company.mower.model.MowerConfigurationModel;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileMowerReader {

    private FileMowerReader() {
    }

    @SneakyThrows
    public static MowerConfigurationModel read(String fileName) {

        try (BufferedReader br = new BufferedReader(new FileReader(new File(fileName)))) {
            Lawn lawn = toLawn(br.readLine());

            List<Mower> mowers = new ArrayList<>();
            String mowerPositionStateLine;
            String commandLine;
            while ((mowerPositionStateLine = br.readLine()) != null && (commandLine = br.readLine()) != null) {
                final Mower mower = toMower(mowerPositionStateLine, commandLine);
                mowers.add(mower);
            }

            return new MowerConfigurationModel(lawn, mowers);
        }
    }

    public static Lawn toLawn(final String firstLine) {
        final String[] split = firstLine.split(" ");
        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[1]);
        return new Lawn(x, y);
    }

    public static Mower toMower(String firstLine, String secondLine) {
        PositionState positionState = toPositionState(firstLine);
        List<Command> commands = toCommands(secondLine);
        return new Mower(positionState, commands);
    }

    public static PositionState toPositionState(String mowerPositionStateLine) {
        final String[] split = mowerPositionStateLine.split(" ");
        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[1]);
        final Direction direction = Direction.forShortName(split[2].charAt(0));
        return new PositionState(x, y, direction);
    }

    public static List<Command> toCommands(String commandLine) {
        List<Command> moveCommands = new ArrayList<>();
        for (char c : commandLine.toCharArray()) {
            moveCommands.add(Command.forShortName(c));
        }
        return moveCommands;
    }

}