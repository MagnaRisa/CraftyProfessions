package magnarisa.craftyprofessions.container;

import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.UUID;

/** [HUGE WIP]
 * This is the Player Wrapper for the CraftyProfessions Plugin in which stores all
 * of the information for a CraftyProfessions player.
 */
public class CraftyPlayer
{
    private Player mPlayer;

    // This is the Profession name and the Modifier Which is apart of it
    private HashMap<String, Double> mPlayerData;

    // Unless the money received from professions is set super high
    // This should never be a problem.
    private BigDecimal mPlayerPool;

    /**
     * This is the Default Constructor for the CraftyPlayer object in which
     * a player will be initialized from the Crafty Professions Database.
     *
     * @param player      - This is the player associated with the CraftyPlayer
     * @param playerData  - This is the Professions info stored for the player, this data
     *                    will be updated from the game via commands and or leveling up the
     *                    player, then the data will be stored into the database when the player
     *                    logs out.
     */
    public CraftyPlayer (Player player, HashMap<String, Double> playerData)
    {
        mPlayer = player;

        mPlayerData = playerData;

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
     * This method will "Payout" or return the current players pool, This method
     * should be called after x amount of ticks of the player receiving money and
     * right before the player logs off if the Player Pool is more than 0.
     *
     * @return The player pool
     */
    public BigDecimal payoutPlayerPool ()
    {
        return mPlayerPool;
    }
}
