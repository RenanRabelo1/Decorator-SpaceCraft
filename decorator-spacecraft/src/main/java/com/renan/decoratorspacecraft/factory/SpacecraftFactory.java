package com.renan.decoratorspacecraft.factory;

import com.renan.decoratorspacecraft.domain.spacecraft.BasicSpacecraft;
import com.renan.decoratorspacecraft.domain.spacecraft.Spacecraft;

public class SpacecraftFactory {

    public Spacecraft createBasicSpacecraft() {
        return new BasicSpacecraft();
    }
}
