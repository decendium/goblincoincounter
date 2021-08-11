package me.mjnt.gobcoinsmod.commands;

import me.mjnt.gobcoinsmod.ConfigHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class SetPosCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "setpos";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "setpos x y";
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
        String posXstr = args[0];
        String posYstr = args[1];
        int posX = Integer.parseInt(posXstr);
        int posY = Integer.parseInt(posYstr);
        ConfigHandler.writeIntConfig("location", "x", posX);
        ConfigHandler.writeIntConfig("location", "y", posY);
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD+"X position set to " + posXstr + ", and Y position set to " + posYstr + "."));
    }
}
