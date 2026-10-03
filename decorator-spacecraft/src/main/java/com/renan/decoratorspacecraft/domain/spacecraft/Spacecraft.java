package com.renan.decoratorspacecraft.domain.spacecraft;

import com.renan.decoratorspacecraft.domain.model.ShipStats;

public interface Spacecraft {

    String getDescription();

    ShipStats getStats();
}
