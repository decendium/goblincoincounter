package me.mjnt.gobcoinsmod.commands;

import net.minecraft.client.Minecraft;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import net.minecraftforge.common.config.Configuration;
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
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {

    }
}
