package com.renan.decoratorspacecraft.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.renan.decoratorspacecraft.domain.spacecraft.BasicSpacecraft;
import com.renan.decoratorspacecraft.domain.spacecraft.Spacecraft;
import com.renan.decoratorspacecraft.exception.InvalidUpgradeException;
import com.renan.decoratorspacecraft.service.UpgradeService;
import org.junit.jupiter.api.Test;

class UpgradeServiceTest {

    private final UpgradeService upgradeService = new UpgradeService();

    @Test
    void shouldApplyLaserUpgrade() {
        Spacecraft spacecraft = new BasicSpacecraft();

        Spacecraft upgraded = upgradeService.applyUpgrade(spacecraft, "LASER");

        assertEquals(25, upgraded.getStats().attack());
    }

    @Test
    void shouldRejectUnknownUpgrade() {
        assertThrows(
                InvalidUpgradeException.class,
                () -> upgradeService.applyUpgrade(new BasicSpacecraft(), "PLASMA")
        );
    }
}
