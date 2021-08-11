package me.mjnt.gobcoinsmod.commands;

import net.minecraft.client.Minecraft;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import me.mjnt.gobcoinsmod.ConfigHandler;

public class ToggleDisplayCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "td";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "td";
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
        Boolean toggled = ConfigHandler.getBoolean("toggles", "display");
        if (toggled == true) {
            ConfigHandler.writeBooleanConfig("toggles", "display", false);
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD+"Display toggle set to FALSE."));
        } else {
            ConfigHandler.writeBooleanConfig("toggles", "display", true);
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD+"Display toggle set to TRUE."));
        }
    }
}
