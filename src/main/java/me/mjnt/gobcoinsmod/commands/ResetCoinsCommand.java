package me.mjnt.gobcoinsmod.commands;

import me.mjnt.gobcoinsmod.GobCoinsMod;
import net.minecraft.client.Minecraft;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import me.mjnt.gobcoinsmod.ConfigHandler;

public class ResetCoinsCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "rc";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "rc";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        GobCoinsMod.coinAmount = 0;
        ConfigHandler.writeIntConfig("coins", "goblins", 0);
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD+"Goblin coin counter set to 0."));
    }
}
