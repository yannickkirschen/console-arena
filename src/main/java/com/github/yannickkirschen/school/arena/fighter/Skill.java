package com.github.yannickkirschen.school.arena.fighter;

import java.util.Objects;

public class Skill {
    private Integer id;
    private String name;
    private SkillType type;
    private Integer strength;

    public Skill() {
        // Needed for YAML serialization
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public SkillType getType() { return type; }

    public void setType(SkillType type) { this.type = type; }

    public Integer getStrength() { return strength; }

    public void setStrength(Integer strength) { this.strength = strength; }

    @Override
    public String toString() {
        return "Skill{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", type=" + type +
            ", strength=" + strength +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Skill skill = (Skill) o;
        return Objects.equals(id, skill.id) &&
            Objects.equals(name, skill.name) &&
            type == skill.type &&
            Objects.equals(strength, skill.strength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, strength);
    }
}
