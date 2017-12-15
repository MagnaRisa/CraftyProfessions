package com.magnarisa.craftyprofessions.container;

import com.magnarisa.craftyprofessions.professions.IProfession;
import net.milkbowl.vault.VaultEco;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.UUID;

/** [HUGE WIP]
 * This is the Player Wrapper for the CraftyProfessions Plugin in which stores all
 * of the information for a CraftyProfessions player.
 */
public class CraftyPlayer
{
    private Player mPlayer;
    private ArrayList<IProfession> mPlayerProfessions;
    private BigDecimal mPlayerPool;

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
    public CraftyPlayer (Player player, ArrayList<IProfession> professions)
    {
        mPlayer = player;

        mPlayerProfessions = professions;

        mPlayerPool = new BigDecimal (0.0).setScale (2, RoundingMode.HALF_UP);
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
    public void payoutPlayerPool (VaultEco.VaultAccount playerAccount)
    {
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
}
