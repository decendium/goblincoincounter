package me.mjnt.gobcoinsmod;


import me.mjnt.gobcoinsmod.commands.ResetCoinsCommand;
import me.mjnt.gobcoinsmod.commands.SetPosCommand;
import me.mjnt.gobcoinsmod.commands.ToggleDisplayCommand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

import java.text.NumberFormat;
import java.util.Locale;

@Mod(modid = GobCoinsMod.MODID, name = GobCoinsMod.NAME, version = GobCoinsMod.VERSION)
public class GobCoinsMod
{
    public static final String MODID = "GobCoins";
    public static final String VERSION = "1.0";
    public static final String NAME = "Goblin Coins Mod";

    public static int coinAmount = -1;
    public static int posX = -1;
    public static int posY = -1;
    public static Boolean displayToggled = null;

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        ClientCommandHandler.instance.registerCommand(new ResetCoinsCommand());
        ClientCommandHandler.instance.registerCommand(new ToggleDisplayCommand());
        ClientCommandHandler.instance.registerCommand(new SetPosCommand());
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        logger.info("initialise FMLServerStartingEvent: " + NAME);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();
        if (message.startsWith("The") && message.contains("coins!") && !message.endsWith("for being generous")) {
            String[] splittedMessage = message.split("\\+");
            message = splittedMessage[1].replace(",", "");
            message = message.replace(" Coins!", "");
            int coins = Integer.parseInt(message);
            int configCoins = ConfigHandler.getInt("coins", "goblins");
            int totalCoins = coins + configCoins;
            coinAmount = totalCoins;
            ConfigHandler.writeIntConfig("coins", "goblins", totalCoins);
        }
    }

    @SubscribeEvent
    public void render(RenderGameOverlayEvent event) {
        if (event.isCancelable() || event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE) {
            return;
        }

        if (displayToggled == null) {
            displayToggled = ConfigHandler.getBoolean("toggles", "display");
        }

        if (coinAmount == -1) {
            coinAmount = ConfigHandler.getInt("coins", "goblins");
        }

        if (posX == -1 && posY == -1) {
            posX = ConfigHandler.getInt("location", "x");
            posY = ConfigHandler.getInt("location", "y");
        }

        String coinTotalStr = NumberFormat.getNumberInstance(Locale.US).format(coinAmount);
        if (displayToggled == true) {
                FontRenderer fRender = Minecraft.getMinecraft().fontRendererObj;
                fRender.drawString(EnumChatFormatting.GOLD + "Goblin Coin Drops: " + EnumChatFormatting.WHITE + coinTotalStr, posX, posY, 0);
        }
    }
}
