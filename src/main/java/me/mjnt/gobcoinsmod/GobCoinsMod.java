package me.mjnt.gobcoinsmod;


import me.mjnt.gobcoinsmod.commands.ResetCoinsCommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import org.apache.logging.log4j.Logger;

// Main mod file, will fix things later
// TODO chat detector and screen renderer

// TODO make positions of gui thing customizable with a /setpos command or something that takes x and y coords as parameters

@Mod(modid = GobCoinsMod.MODID, name = GobCoinsMod.NAME, version = GobCoinsMod.VERSION)
public class GobCoinsMod
{
    public static final String MODID = "GobCoins";
    public static final String VERSION = "1.0";
    public static final String NAME = "Goblin Coins Mod";

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        ClientCommandHandler.instance.registerCommand(new ResetCoinsCommand());
    }

    @EventHandler
    public void init(FMLServerStartingEvent event)
    {
        logger.info("initialise FMLServerStartingEvent: " + NAME);
    }
}
