package ca.landonjw.wrappers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class EntityPlayerMPWrapper
{

    private final EntityPlayerMP player;

    protected EntityPlayerMPWrapper(EntityPlayerMP player)
    {
        this.player = player;
    }

    public static EntityPlayerMPWrapper fromString(String username)
    {
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        EntityPlayerMP player = server.getPlayerList().getPlayerByUsername(username);
        return new EntityPlayerMPWrapper(player);
    }

    public static EntityPlayerMPWrapper fromMP(EntityPlayerMP player)
    {
        return new EntityPlayerMPWrapper(player);
    }

    public static EntityPlayerMPWrapper fromSP(EntityPlayer player)
    {
        if (player instanceof EntityPlayerMP)
        {
            return new EntityPlayerMPWrapper((EntityPlayerMP) player);
        }
        throw new IllegalStateException();
    }

    public void setPositionAndUpdate(double x, double y, double z)
    {
        player.setPositionAndUpdate(x, y, z);
    }

    public double getX()
    {
        return player.posX;
    }

    public double getY()
    {
        return player.posY;
    }

    public double getZ()
    {
        return player.posZ;
    }

    public void sendMessage(String message)
    {
        player.sendMessage(new TextComponentString(message));
    }

}
