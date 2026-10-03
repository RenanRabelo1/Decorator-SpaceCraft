package com.renan.decoratorspacecraft.spacecraft;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.renan.decoratorspacecraft.domain.spacecraft.BasicSpacecraft;
import com.renan.decoratorspacecraft.domain.spacecraft.Spacecraft;
import org.junit.jupiter.api.Test;

class BasicSpacecraftTest {

    @Test
    void shouldCreateBasicSpacecraftWithDefaultStats() {
        Spacecraft spacecraft = new BasicSpacecraft();

        assertEquals("Explorer", spacecraft.getStats().name());
        assertEquals(100, spacecraft.getStats().health());
        assertEquals(10, spacecraft.getStats().attack());
        assertEquals(5, spacecraft.getStats().defense());
        assertEquals(10, spacecraft.getStats().speed());
    }
}
