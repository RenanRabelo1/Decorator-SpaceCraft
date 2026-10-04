package com.renan.decoratorspacecraft.service;

import com.renan.decoratorspacecraft.domain.spacecraft.Spacecraft;
import com.renan.decoratorspacecraft.domain.spacecraft.decorator.LaserDecorator;
import com.renan.decoratorspacecraft.domain.spacecraft.decorator.MissileDecorator;
import com.renan.decoratorspacecraft.domain.spacecraft.decorator.RepairDecorator;
import com.renan.decoratorspacecraft.domain.spacecraft.decorator.ShieldDecorator;
import com.renan.decoratorspacecraft.domain.spacecraft.decorator.TurboDecorator;
import com.renan.decoratorspacecraft.exception.InvalidUpgradeException;
import java.util.Locale;
import java.util.Objects;

public class UpgradeService {

    public Spacecraft applyUpgrade(Spacecraft spacecraft, String upgrade) {
        Objects.requireNonNull(spacecraft);

        String normalizedUpgrade = upgrade == null
                ? ""
                : upgrade.trim().toUpperCase(Locale.ROOT);

        return switch (normalizedUpgrade) {
            case "TURBO" -> new TurboDecorator(spacecraft);
            case "LASER" -> new LaserDecorator(spacecraft);
            case "ESCUDO" -> new ShieldDecorator(spacecraft);
            case "MISSIL", "MÍSSIL" -> new MissileDecorator(spacecraft);
            case "REPARO" -> new RepairDecorator(spacecraft);
            default -> throw new InvalidUpgradeException(upgrade);
        };
    }
}
