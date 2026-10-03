package com.renan.decoratorspacecraft.domain.spacecraft;

import com.renan.decoratorspacecraft.domain.model.ShipStats;
import java.util.Objects;

public abstract class SpacecraftDecorator implements Spacecraft {

    protected final Spacecraft spacecraft;

    protected SpacecraftDecorator(Spacecraft spacecraft) {
        this.spacecraft = Objects.requireNonNull(spacecraft);
    }

    @Override
    public String getDescription() {
        return spacecraft.getDescription();
    }

    @Override
    public ShipStats getStats() {
        return spacecraft.getStats();
    }
}
