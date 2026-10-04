package com.renan.decoratorspacecraft.battle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.renan.decoratorspacecraft.domain.battle.Battle;
import com.renan.decoratorspacecraft.domain.battle.BattleResult;
import com.renan.decoratorspacecraft.domain.battle.EnemyShip;
import com.renan.decoratorspacecraft.domain.model.ShipStats;
import com.renan.decoratorspacecraft.domain.spacecraft.BasicSpacecraft;
import com.renan.decoratorspacecraft.domain.spacecraft.Spacecraft;
import com.renan.decoratorspacecraft.domain.spacecraft.decorator.LaserDecorator;
import com.renan.decoratorspacecraft.domain.spacecraft.decorator.MissileDecorator;
import com.renan.decoratorspacecraft.domain.spacecraft.decorator.RepairDecorator;
import com.renan.decoratorspacecraft.domain.spacecraft.decorator.ShieldDecorator;
import com.renan.decoratorspacecraft.service.BattleService;
import org.junit.jupiter.api.Test;

class BattleServiceTest {

    @Test
    void shouldReturnVictoryWhenEnemyHealthReachesZero() {
        Spacecraft spacecraft = new LaserDecorator(new BasicSpacecraft());
        EnemyShip enemy = new EnemyShip(new ShipStats("Drone", 17, 1, 0, 1));
        Battle battle = new Battle(spacecraft, enemy);
        BattleService battleService = new BattleService();

        battleService.performPlayerAttack(battle);

        assertEquals(BattleResult.PLAYER_VICTORY, battle.getResult());
    }

    @Test
    void shouldUseMissileOnlyOnce() {
        Spacecraft spacecraft = new MissileDecorator(new BasicSpacecraft());
        Battle battle = new Battle(spacecraft, new EnemyShip());
        BattleService battleService = new BattleService();

        int damage = battleService.performPlayerMissileAttack(battle);

        assertEquals(40, damage);
        assertFalse(battle.isMissileAvailable());
    }

    @Test
    void shouldApplyShieldAndRepairDuringEnemyTurn() {
        Spacecraft spacecraft = new RepairDecorator(
                new ShieldDecorator(new BasicSpacecraft())
        );
        Battle battle = new Battle(spacecraft, new EnemyShip());
        BattleService battleService = new BattleService();

        int damage = battleService.performEnemyTurn(battle);

        assertEquals(1, damage);
        assertEquals(100, battle.getPlayerHealth());
    }
}
