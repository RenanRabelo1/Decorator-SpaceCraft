package com.renan.decoratorspacecraft.spacecraft;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.renan.decoratorspacecraft.domain.spacecraft.BasicSpacecraft;
import com.renan.decoratorspacecraft.domain.spacecraft.Spacecraft;
import com.renan.decoratorspacecraft.domain.spacecraft.SpacecraftDecorator;
import org.junit.jupiter.api.Test;

class SpacecraftDecoratorTest {

    @Test
    void shouldKeepBaseStatsWhenDecoratorDelegatesToSpacecraft() {
        Spacecraft spacecraft = new BasicSpacecraft();
        Spacecraft decorator = new PassthroughDecorator(spacecraft);

        assertEquals(spacecraft.getStats(), decorator.getStats());
    }

    private static class PassthroughDecorator extends SpacecraftDecorator {

        private PassthroughDecorator(Spacecraft spacecraft) {
            super(spacecraft);
        }
    }
}
