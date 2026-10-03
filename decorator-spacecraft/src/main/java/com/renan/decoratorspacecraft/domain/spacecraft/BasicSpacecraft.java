package com.renan.decoratorspacecraft.domain.spacecraft;

import com.renan.decoratorspacecraft.domain.model.ShipStats;

public class BasicSpacecraft implements Spacecraft {

    private static final ShipStats DEFAULT_STATS = new ShipStats(
            "Explorer",
            100,
            10,
            5,
            10
    );

    @Override
    public ShipStats getStats() {
        return DEFAULT_STATS;
    }
}
