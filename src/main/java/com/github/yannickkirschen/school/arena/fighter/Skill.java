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
 * The type specifies if the skill can be used for attacks or for defends.
 * <p>
 * Two skills are equal to each other when their IDs and names are equal.
 *
 * @author Yannick Kirschen
 * @see Fighter
 * @see Fighters
 * @see Mode
 * @since 1.0.0
 */
public final class Skill {
    private Integer id;
    private String name;
    private Integer power;
    private Mode type;

    private Skill(Integer id, String name, Integer power, Mode type) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.type = type;
    }

    /**
     * Constructs a new skill from a {@link YamlSkill}.
     *
     * @param yamlSkill The skill read from the YAML file with all fighters to create the fighter from.
     * @param type      The type of the skill. (attack/defend)
     * @param id        The ID of the new skill. Must be unique.
     *
     * @return A new skill based on the one read from the YAML file.
     */
    static Skill fromYamlSkill(YamlSkill yamlSkill, Mode type, Integer id) {
        return new Skill(id, yamlSkill.getName(), yamlSkill.getPower(), type);
    }

    Integer getId() { return id; }

    public String getName() { return name; }

    public Integer getPower() { return power; }

    Mode getType() { return type; }

    @Override
    public String toString() {
        return "Skill{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", power=" + power +
            ", type=" + type +
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
