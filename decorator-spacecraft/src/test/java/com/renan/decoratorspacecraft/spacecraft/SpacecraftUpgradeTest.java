package com.renan.decoratorspacecraft.spacecraft;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.renan.decoratorspacecraft.domain.spacecraft.BasicSpacecraft;
import com.renan.decoratorspacecraft.domain.spacecraft.Spacecraft;
import com.renan.decoratorspacecraft.domain.spacecraft.decorator.LaserDecorator;
import com.renan.decoratorspacecraft.domain.spacecraft.decorator.TurboDecorator;
import org.junit.jupiter.api.Test;

class SpacecraftUpgradeTest {

    @Test
    void shouldAddTurboAndLaserStats() {
        Spacecraft spacecraft = new LaserDecorator(
                new TurboDecorator(
                        new BasicSpacecraft()
                )
        );

        assertEquals("Explorer + Turbo + Laser", spacecraft.getDescription());
        assertEquals(25, spacecraft.getStats().attack());
        assertEquals(20, spacecraft.getStats().speed());
    }
}
