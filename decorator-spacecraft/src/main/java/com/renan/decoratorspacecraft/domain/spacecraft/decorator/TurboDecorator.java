package com.renan.decoratorspacecraft.domain.spacecraft.decorator;

import com.renan.decoratorspacecraft.domain.model.ShipStats;
import com.renan.decoratorspacecraft.domain.spacecraft.Spacecraft;
import com.renan.decoratorspacecraft.domain.spacecraft.SpacecraftDecorator;

public class TurboDecorator extends SpacecraftDecorator {

    private static final int SPEED_BONUS = 10;

    public TurboDecorator(Spacecraft spacecraft) {
        super(spacecraft);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Turbo";
    }

    @Override
    public ShipStats getStats() {
        ShipStats stats = super.getStats();
        return stats.withSpeed(stats.speed() + SPEED_BONUS);
    }
}
