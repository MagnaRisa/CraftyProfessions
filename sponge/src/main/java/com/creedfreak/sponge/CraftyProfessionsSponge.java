package com.creedfreak.sponge;

import com.creedfreak.common.ICraftyProfessions;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.slf4j.Logger;

import javax.inject.Inject;

@Plugin (id = "craftyprofessions", name = "Crafty Professions", version = "0.1-dev")
public class CraftyProfessionsSponge
{
    @Inject
    private Logger mLogger;

    @Listener
    public void onServerStart (GameStartedServerEvent event)
    {
        mLogger.info ("Spigot Crafty Professions is currently Running!");
    }
}
