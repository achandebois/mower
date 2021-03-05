package com.company.mower.model;

import com.company.mower.domain.Lawn;
import com.company.mower.domain.Mower;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MowerConfigurationModel {
    private final Lawn lawn;
    private final List<Mower> mowers;
}
