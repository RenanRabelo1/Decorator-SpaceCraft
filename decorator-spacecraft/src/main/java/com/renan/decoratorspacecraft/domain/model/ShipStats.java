package com.renan.decoratorspacecraft.domain.model;

public record ShipStats(
        String name,
        int health,
        int attack,
        int defense,
        int speed
) {
}
