package com.github.yannickkirschen.school.arena.fighter;

import java.util.Objects;

/**
 * The {@link YamlSkill} is only used as a transport object to parse a skill from the YAML file into it. See {@link Skill} for more details.
 *
 * @author Yannick Kirschen
 * @implNote Though it is bad practice to use {@link SuppressWarnings}, it is used in that class, so that the IDE won't grumble.
 * @see Skill
 * @since 1.0.0
 */
public final class YamlSkill {
    private String name;
    private Integer power;

    @SuppressWarnings("unused")
    public YamlSkill() {
        // Needed for YAML serialization
    }

    public String getName() { return name; }

    @SuppressWarnings("unused")
    public void setName(String name) { this.name = name; }

    public Integer getPower() { return power; }

    @SuppressWarnings("unused")
    public void setPower(Integer power) { this.power = power; }

    @Override
    public String toString() {
        return "YamlSkill{" +
            "name='" + name + '\'' +
            ", power=" + power +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        YamlSkill yamlSkill = (YamlSkill) o;
        return Objects.equals(name, yamlSkill.name) &&
            Objects.equals(power, yamlSkill.power);
    }

    @Override
    public int hashCode() { return Objects.hash(name, power); }
}
