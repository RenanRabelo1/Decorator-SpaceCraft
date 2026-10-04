package com.renan.decoratorspacecraft.domain.spacecraft;

import com.renan.decoratorspacecraft.domain.model.ShipStats;

public interface Spacecraft {

    String getDescription();

    ShipStats getStats();

    default boolean hasMissile() {
        return false;
    }

    default int getMissileDamage() {
        return 0;
    }

    default int getRepairAmount() {
        return 0;
    }
}
