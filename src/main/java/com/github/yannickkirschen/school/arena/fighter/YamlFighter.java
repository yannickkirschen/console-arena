package com.github.yannickkirschen.school.arena.fighter;

import java.util.List;
import java.util.Objects;

/**
 * The {@link YamlFighter} is only used as a transport object to parse a fighter from the YAML file into it. See {@link Fighter} for more details.
 *
 * @author Yannick Kirschen
 * @see Fighter
 * @since 1.0.0
 */
public final class YamlFighter {
    private String name;
    private Integer power;
    private List<YamlSkill> attacks;
    private List<YamlSkill> defenses;

    public YamlFighter() {
        // Needed for YAML serialization
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Integer getPower() { return power; }

    public void setPower(Integer power) { this.power = power; }

    public List<YamlSkill> getAttacks() { return attacks; }

    public void setAttacks(List<YamlSkill> attacks) { this.attacks = attacks; }

    public List<YamlSkill> getDefenses() { return defenses; }

    public void setDefenses(List<YamlSkill> defenses) { this.defenses = defenses; }

    @Override
    public String toString() {
        return "YamlFighters{" +
            "name='" + name + '\'' +
            ", power=" + power +
            ", attacks=" + attacks +
            ", defenses=" + defenses +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        YamlFighter that = (YamlFighter) o;
        return Objects.equals(name, that.name) &&
            Objects.equals(power, that.power) &&
            Objects.equals(attacks, that.attacks) &&
            Objects.equals(defenses, that.defenses);
    }

    @Override
    public int hashCode() { return Objects.hash(name, power, attacks, defenses); }
}
