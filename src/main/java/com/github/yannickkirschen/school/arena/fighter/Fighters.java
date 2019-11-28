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
    private final List<Fighter> fighterList = new LinkedList<>();

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
        for (Fighter fighter : fighterList) {
            if (id.equals(fighter.getId())) {
                fighterList.remove(fighter);
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
    public Fighter getRandom() { return fighterList.get(new Random().nextInt(fighterList.size())); }

    private void addFighter(Fighter fighter) { fighterList.add(fighter); }

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

        for (Fighter fighter : fighterList) {
            sb.append("\n").append(fighter.getId()).append(" - ").append(fighter.getName()).append(" (Power: ").append(fighter.getPower()).append(")");
        }

        return sb.append("\n").toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Fighters fighters1 = (Fighters) o;
        return Objects.equals(fighterList, fighters1.fighterList);
    }

    @Override
    public int hashCode() { return Objects.hash(fighterList); }
}
