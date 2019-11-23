package com.github.yannickkirschen.school.arena.fighter;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * {@link Fighters} is a wrapper for a list of all fighters read from the YAML file. It also allows some convenient functions for working with all fighters.
 * <p>
 * Instantiation is only possible via {@link #fromYamlFighters(YamlFighters)}.
 *
 * @author Yannick Kirschen)
 * @see Fighter
 * @since 1.0.0
 */
public final class Fighters {
    private List<Fighter> fighters = new LinkedList<>();

    private Fighters() {}

    /**
     * Constructs a new list of fighters from {@link YamlFighters}.
     *
     * @param yamlFighters The fighters read from the YAML file.
     *
     * @return A new list of fighters based on those read from the YAML file.
     */
    public static Fighters fromYamlFighters(YamlFighters yamlFighters) {
        Fighters fighters = new Fighters();

        List<YamlFighter> yamlFighterList = yamlFighters.getFighters();
        for (int i = 0; i < yamlFighterList.size(); i++) {
            fighters.addFighter(Fighter.fromYamlFighter(yamlFighterList.get(i), i));
        }

        return fighters;
    }

    /**
     * Selects a fighter based on its ID from the list and removes it afterwards.
     *
     * @param id The ID of the fighter to look up.
     *
     * @return The fighter for the specified ID. <code>null</code> if there is no fighter with that ID.
     */
    public Fighter getAndRemove(Integer id) {
        for (Fighter fighter : fighters) {
            if (id.equals(fighter.getId())) {
                fighters.remove(fighter);
                return fighter;
            }
        }
        return null;
    }

    /**
     * Selects a fighter from the lost.
     *
     * @return A random fighter.
     */
    public Fighter getRandom() { return fighters.get(new Random().nextInt(fighters.size())); }

    private void addFighter(Fighter fighter) { fighters.add(fighter); }

    /**
     * Constructs all fighters in order to be displayed as a menu for the user. Such as:
     *
     * <pre>
     *     Choose a player:
     *
     *     0 - Vader (Power: 20)
     *     1 - Yoda (Power: 22)
     * </pre>
     * <p>
     * The user can now select a player.
     *
     * @return All fighters as a pretty string.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append("Choose a player: ");

        for (Fighter fighter : fighters) {
            sb.append("\n").append(fighter.getId()).append(" - ").append(fighter.getName()).append(" (Power: ").append(fighter.getPower()).append(")");
        }

        return sb.append("\n").toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Fighters fighters1 = (Fighters) o;
        return Objects.equals(fighters, fighters1.fighters);
    }

    @Override
    public int hashCode() { return Objects.hash(fighters); }
}
