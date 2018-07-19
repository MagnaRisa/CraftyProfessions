package com.magnarisa.common.container;

import net.milkbowl.vault.economy.Economy;

import java.math.BigDecimal;
import java.util.UUID;

public interface IPlayer
{
    /**
     * Returns the UUID of the current player.
     *
     * @return The players unique identifier
     */
    public UUID getUUID ();

    /**
     * Sends a message to the target player.
     *
     * @param message The message to send to the target player.
     */
    public void sendMessage (String message);

    /**
     * Checks the permission of the current player.
     *
     * @param perm The permission to test if the player currently has.
     *
     * @return True - If the player has the permission.
     *         False - If the player does not have that permission.
     */
    public boolean checkPerms (final String perm);

    /**
     * Payout the players value pool to their economy account
     * If there is no economy then only add points to the players account.
     */
    public void payoutPlayerPool (Economy economy);

    /**
     * Add money to the players value pool.
     *
     * @param value The amount to add to the value pool.
     *              This parameter and internal pool tracks decimals up to
     *              two decimal places.
     */
    public void increasePool (BigDecimal value);

    /**
     * Displays the current professions the User currently have.
     */
    public void listProfessions ();
}
