package com.renan.decoratorspacecraft.domain.model;

public record ShipStats(
        String name,
        int health,
        int attack,
        int defense,
        int speed
) {

    public ShipStats withAttack(int newAttack) {
        return new ShipStats(name, health, newAttack, defense, speed);
    }

    public ShipStats withSpeed(int newSpeed) {
        return new ShipStats(name, health, attack, defense, newSpeed);
    }
}
