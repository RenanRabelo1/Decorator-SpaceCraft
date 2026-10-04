package com.renan.decoratorspacecraft.domain.battle;

import com.renan.decoratorspacecraft.domain.model.ShipStats;
import java.util.Objects;

public class EnemyShip {

    private static final ShipStats DEFAULT_STATS = new ShipStats(
            "Destroyer X",
            120,
            18,
            8,
            12
    );

    private final ShipStats stats;

    public EnemyShip() {
        this(DEFAULT_STATS);
    }

    public EnemyShip(ShipStats stats) {
        this.stats = Objects.requireNonNull(stats);
    }

    public ShipStats getStats() {
        return stats;
    }
}
