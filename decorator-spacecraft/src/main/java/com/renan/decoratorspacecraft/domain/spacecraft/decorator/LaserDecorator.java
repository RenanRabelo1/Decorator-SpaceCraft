package com.renan.decoratorspacecraft.domain.spacecraft.decorator;

import com.renan.decoratorspacecraft.domain.model.ShipStats;
import com.renan.decoratorspacecraft.domain.spacecraft.Spacecraft;
import com.renan.decoratorspacecraft.domain.spacecraft.SpacecraftDecorator;

public class LaserDecorator extends SpacecraftDecorator {

    private static final int ATTACK_BONUS = 15;

    public LaserDecorator(Spacecraft spacecraft) {
        super(spacecraft);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Laser";
    }

    @Override
    public ShipStats getStats() {
        ShipStats stats = super.getStats();
        return stats.withAttack(stats.attack() + ATTACK_BONUS);
    }
}
