package com.renan.decoratorspacecraft.domain.spacecraft.decorator;

import com.renan.decoratorspacecraft.domain.model.ShipStats;
import com.renan.decoratorspacecraft.domain.spacecraft.Spacecraft;
import com.renan.decoratorspacecraft.domain.spacecraft.SpacecraftDecorator;

public class ShieldDecorator extends SpacecraftDecorator {

    private static final int DEFENSE_BONUS = 15;

    public ShieldDecorator(Spacecraft spacecraft) {
        super(spacecraft);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Escudo";
    }

    @Override
    public ShipStats getStats() {
        ShipStats stats = super.getStats();
        return stats.withDefense(stats.defense() + DEFENSE_BONUS);
    }
}
