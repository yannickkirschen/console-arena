package arena.match;

import arena.fighter.*;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Match {
    private final Arena arena = new Arena();

    private final Fighter player;
    private final Fighter computer;

    public Match(Fighter player, Fighter computer) {
        this.player = player;
        this.computer = computer;
    }

    public void start() {
        while (player.getHealth() > 0 && computer.getHealth() > 0) {
            healthInfo();
            if (fight(Mode.ATTACK) || fight(Mode.DEFENSE)) {
                break;
            }
        }
        log.info("{} won the game!", (player.getHealth() > 0 ? player : computer).getName());
    }

    /**
     * Lets the player and the computer fight against each other. Checks, if the loser of this fight has lost the game.
     *
     * @param mode The mode of the fight (attack/defend) for the player.
     * @return <code>true</code>, if the loser of the fight has no more health (and the game is over). Otherwise <code>false</code>.
     */
    private boolean fight(Mode mode) {
        Fighter winner = arena.fight(player, computer, mode);
        if (winner == null) {
            log.info("There was a tie.");
        } else {
            log.info("\n\n<<< {} won the fight! >>>\n", winner.getName());
            Fighter loser = oppositeFighter(winner, player, computer);
            return loser.getHealth() <= 0;
        }
        return false;
    }

    /**
     * Gets the opposite fighter of two fighters. Given you have two fighters <code>A</code> and <code>B</code>. When the actual fighter is <code>A</code>, the
     * opposite is <code>B</code>. When the actual fighter is <code>B</code>, the opposite is <code>A</code>.
     *
     * @param actual The fighter to get the opposite from.
     * @param one    The first fighter.
     * @param two    The second fighter.
     * @return The opposite fighter of <code>actual</code>.
     */
    private Fighter oppositeFighter(Fighter actual, Fighter one, Fighter two) {
        return actual.equals(one) ? two : one;
    }

    /**
     * Prints the health of both fighters.
     */
    private void healthInfo() {
        log.info("{}'s health: {}", player.getName(), player.getHealth());
        log.info("{}'s health: {}", computer.getName(), computer.getHealth());
    }
}
