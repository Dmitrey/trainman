package com.myteam.game.service;

import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.Fixture;

public class MyFilter implements ContactFilter {
    @Override
    public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
        if (fixtureA.getFilterData().categoryBits == 0x0004 && fixtureB.getFilterData().categoryBits == 0x0001
        || fixtureA.getFilterData().categoryBits == 0x0001 && fixtureB.getFilterData().categoryBits == 0x0004)
            return false;
        if (fixtureA.getFilterData().categoryBits == 0x0008 && fixtureB.getFilterData().categoryBits == 0x0008)
            return true;
        return fixtureA.getFilterData().categoryBits != fixtureB.getFilterData().categoryBits;

    }
}
