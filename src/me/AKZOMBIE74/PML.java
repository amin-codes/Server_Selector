package me.AKZOMBIE74;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

/**
 * Created by AKZOMBIE74 on 1/27/2016.
 */
public class PML implements PluginMessageListener{

    private String serverip = null;

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }

        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("PlayerCount")) {
            // Use the code sample in the 'Response' sections below to read
            // the data.
            String server = in.readUTF(); // Name of server, as given in the arguments
            Selector.getInstance().getPlayerCounts().put(server, in.readInt());
        } else if (subchannel.equals("PlayerList")){
            String server = in.readUTF(); // The name of the server you got the player list of, as given in args.
            Selector.getInstance().getPlayerLists().put(server, in.readUTF().split(", "));
        } else if (subchannel.equals("ServerIP")) {
            String servername = in.readUTF();
            serverip = in.readUTF();
            int port = in.readUnsignedShort();
        }

    }


    public String getServerip() {
        return serverip;
    }
}
