package com.renan.decoratorspacecraft.spacecraft;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.renan.decoratorspacecraft.domain.spacecraft.BasicSpacecraft;
import com.renan.decoratorspacecraft.domain.spacecraft.Spacecraft;
import com.renan.decoratorspacecraft.domain.spacecraft.decorator.LaserDecorator;
import com.renan.decoratorspacecraft.domain.spacecraft.decorator.MissileDecorator;
import com.renan.decoratorspacecraft.domain.spacecraft.decorator.RepairDecorator;
import com.renan.decoratorspacecraft.domain.spacecraft.decorator.ShieldDecorator;
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

    @Test
    void shouldCombineAllAvailableUpgrades() {
        Spacecraft spacecraft = new RepairDecorator(
                new ShieldDecorator(
                        new MissileDecorator(
                                new LaserDecorator(
                                        new TurboDecorator(
                                                new BasicSpacecraft()
                                        )
                                )
                        )
                )
        );

        assertEquals("Explorer + Turbo + Laser + Míssil + Escudo + Reparo", spacecraft.getDescription());
        assertEquals(25, spacecraft.getStats().attack());
        assertEquals(20, spacecraft.getStats().defense());
        assertEquals(20, spacecraft.getStats().speed());
        assertEquals(true, spacecraft.hasMissile());
        assertEquals(40, spacecraft.getMissileDamage());
        assertEquals(5, spacecraft.getRepairAmount());
    }
}
