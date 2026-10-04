package com.renan.decoratorspacecraft.domain.spacecraft.decorator;

import com.renan.decoratorspacecraft.domain.spacecraft.Spacecraft;
import com.renan.decoratorspacecraft.domain.spacecraft.SpacecraftDecorator;

public class MissileDecorator extends SpacecraftDecorator {

    private static final int MISSILE_DAMAGE = 40;

    public MissileDecorator(Spacecraft spacecraft) {
        super(spacecraft);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Míssil";
    }

    @Override
    public boolean hasMissile() {
        return true;
    }

    @Override
    public int getMissileDamage() {
        return MISSILE_DAMAGE;
    }
}
