package com.renan.decoratorspacecraft.domain.spacecraft.decorator;

import com.renan.decoratorspacecraft.domain.spacecraft.Spacecraft;
import com.renan.decoratorspacecraft.domain.spacecraft.SpacecraftDecorator;

public class RepairDecorator extends SpacecraftDecorator {

    private static final int REPAIR_AMOUNT = 5;

    public RepairDecorator(Spacecraft spacecraft) {
        super(spacecraft);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Reparo";
    }

    @Override
    public int getRepairAmount() {
        return super.getRepairAmount() + REPAIR_AMOUNT;
    }
}
