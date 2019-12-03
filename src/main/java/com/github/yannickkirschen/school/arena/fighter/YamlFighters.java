package com.github.yannickkirschen.school.arena.fighter;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * The {@link YamlFighters} are only used as a transport object to parse all fighters from the YAML file into it.
 *
 * @author Yannick Kirschen
 * @since 1.0.0
 */
public final class YamlFighters {
    private List<YamlFighter> fighters;

    public YamlFighters() {
        // Needed for YAML serialization
    }

    public List<Fighter> asFighters() {
        List<Fighter> fighterList = new LinkedList<>();

        for (int i = 0; i < fighters.size(); i++) {
            fighterList.add(Fighter.fromYamlFighter(fighters.get(i), i));
        }

        return fighterList;
    }

    public List<YamlFighter> getFighters() { return fighters; }

    @SuppressWarnings("unused")
    public void setFighters(List<YamlFighter> fighters) { this.fighters = fighters; }

    @Override
    public String toString() {
        return "YamlFighters{" +
            "fighters=" + fighters +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        YamlFighters that = (YamlFighters) o;
        return Objects.equals(fighters, that.fighters);
    }

    @Override
    public int hashCode() { return Objects.hash(fighters); }
}
