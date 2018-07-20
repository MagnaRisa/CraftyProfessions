package com.creedfreak.spigot.container;

import com.google.common.primitives.UnsignedLong;
import com.creedfreak.common.container.IPlayer;
import com.creedfreak.common.professions.IProfession;
import net.milkbowl.vault.economy.*;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/** [HUGE WIP]
 * This is the Player Wrapper for the CraftyProfessions Plugin in which stores all
 * of the information for a CraftyProfessions player.
 */
public class CraftyPlayer implements IPlayer
{
    // This is the players primary key within the Database
    private UnsignedLong mPlayerID;
    private Player mPlayer;
    private BigDecimal mPlayerPool;


    // The Users list of professions.
    private HashMap<String, IProfession> mPlayerProfessions;

    /**
     * This is the Default Constructor for the CraftyPlayer object in which
     * a player will be initialized from the Crafty Professions Database.
     *
     * @param player       - This is the player associated with the CraftyPlayer
     * @param professions  - This is the Professions info stored for the player, this data
     *                    will be updated from the game via commands and or leveling up the
     *                    player, then the data will be stored into the database when the player
     *                    logs out.
     */
    public CraftyPlayer (UnsignedLong dbID, Player player, ArrayList<IProfession> professions)
    {
        mPlayerID = dbID;
        mPlayer = player;
        mPlayerPool = new BigDecimal (0.0).setScale (2, RoundingMode.HALF_UP);

        // Construct an easy way to reference a users professions.
        for (IProfession prof : professions)
        {
            mPlayerProfessions.put (prof.getName(), prof);
        }
    }
    /**
     * This method will return the UUID of the CPPlayer
     *
     * @return The Players UUID
     */
    public UUID getUUID ()
    {
        return mPlayer.getUniqueId ();
    }

    /**
     * This method will "Payout" the players current money ca, This method
     * should be called after x amount of ticks of the player receiving money and
     * right before the player logs off if the Player Pool is more than 0.
     */
    public void payoutPlayerPool (Economy economy)
    {
        if (mPlayer.isOnline ())
        {
            economy.depositPlayer (mPlayer, mPlayerPool.doubleValue ());
        }
        //playerAccount.add (mPlayerPool.doubleValue ());
        //mPlayerPool = new BigDecimal (0.0).setScale (2, RoundingMode.HALF_UP);
    }

    /**
     * This method will display the message to the Player wrapped within
     * the CraftyPlayer of this object, not "getting" a Player from the CraftyPlayer is
     * technically safer.
     *
     * @param message The message to send to the wrapped player.
     */
    public void sendMessage (String message)
    {
        mPlayer.sendMessage (message);
    }

    /**
     * This method checks the CraftyPlayers Permissions
     *
     * @param perm The permission to check against the player
     */
    public boolean checkPerms (final String perm)
    {
        return mPlayer.hasPermission (perm);
    }

    public void increasePool (BigDecimal value)
    {
        // I should probably make this secure.
        mPlayerPool = mPlayerPool.add (value);
    }

    /**
     * Lists the current professions of the User. This will more than likely be a
     * command that the crafty player will be able to use in order to see all of
     * the current professions that they have.
     */
    public void listProfessions ()
    {
        if (mPlayer instanceof Player)
        {
            if (mPlayerProfessions.isEmpty ())
            {
                mPlayer.sendMessage ("You currently have no Professions");
            }
            else
            {
                // Iterate over the list and send the Profession name to the User.
                for (Map.Entry<String, IProfession> prof : mPlayerProfessions.entrySet ())
                {
                    mPlayer.sendMessage (prof.getKey ());
                }
            }
        }
        else
        {
            mPlayer.sendMessage ("You must be a Player to see your Professions");
        }
    }

    // TODO: Implement with SpigotAPI
    public void payoutPlayerPool ()
    {

    }
}
