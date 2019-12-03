package com.github.yannickkirschen.school.arena.fighter;

import java.util.Objects;

/**
 * A {@link Skill} defines a single skill a fighter can have in order to attack or defend. The ID gets generated when reading the YAML file with all fighters.
 * <p>
 * Its only purpose is to allow an easy selection of a skill in the console. Though the ID allows identifying a skill, the name must also be unique, so that it
 * does not lead to misunderstandings.
 * <p>
 * The power is an abstract value, saying how strong a fighter is. It is used to calculate, whether a player won or lost a fight.
 * <p>
 * Two skills are equal to each other when their IDs and names are equal.
 *
 * @author Yannick Kirschen
 * @see Fighter
 * @see Mode
 * @since 1.0.0
 */
public final class Skill {
    private final Integer id;
    private final String name;
    private final Integer power;

    private Skill(Integer id, String name, Integer power) {
        this.id = id;
        this.name = name;
        this.power = power;
    }

    /**
     * Constructs a new skill from a {@link YamlSkill}.
     *
     * @param yamlSkill The skill read from the YAML file with all fighters to create the fighter from.
     * @param id        The ID of the new skill. Must be unique.
     *
     * @return A new skill based on the one read from the YAML file.
     */
    static Skill fromYamlSkill(YamlSkill yamlSkill, Integer id) {
        return new Skill(id, yamlSkill.getName(), yamlSkill.getPower());
    }

    Integer getId() { return id; }

    public String getName() { return name; }

    public Integer getPower() { return power; }

    @Override
    public String toString() {
        return "Skill{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", power=" + power +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Skill skill = (Skill) o;
        return id.equals(skill.id) && name.equals(skill.name);
    }

    @Override
    public int hashCode() { return Objects.hash(id, name); }
}
