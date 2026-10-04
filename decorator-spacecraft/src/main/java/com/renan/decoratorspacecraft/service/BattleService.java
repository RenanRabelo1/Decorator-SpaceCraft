package com.renan.decoratorspacecraft.service;

import com.renan.decoratorspacecraft.domain.battle.Battle;
import com.renan.decoratorspacecraft.domain.battle.BattleResult;

public class BattleService {

    public int performPlayerAttack(Battle battle) {
        return battle.playerAttack();
    }

    public int performPlayerMissileAttack(Battle battle) {
        return battle.playerUseMissile();
    }

    public int performEnemyTurn(Battle battle) {
        if (battle.getResult() != BattleResult.IN_PROGRESS) {
            return 0;
        }

        int damage = battle.enemyAttack();
        battle.repairPlayer();
        return damage;
    }
}
