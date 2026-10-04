package com.renan.decoratorspacecraft.battle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.renan.decoratorspacecraft.domain.battle.Battle;
import com.renan.decoratorspacecraft.domain.battle.EnemyShip;
import com.renan.decoratorspacecraft.domain.spacecraft.BasicSpacecraft;
import com.renan.decoratorspacecraft.domain.spacecraft.Spacecraft;
import com.renan.decoratorspacecraft.domain.spacecraft.decorator.LaserDecorator;
import org.junit.jupiter.api.Test;

class BattleTest {

    @Test
    void shouldReduceEnemyHealthAfterAttack() {
        Spacecraft spacecraft = new LaserDecorator(new BasicSpacecraft());
        Battle battle = new Battle(spacecraft, new EnemyShip());

        int damage = battle.playerAttack();

        assertEquals(17, damage);
        assertEquals(103, battle.getEnemyHealth());
    }
}
