package com.github.yannickkirschen.school.arena.fighter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Fighter {
    private String name;
    private Map<String, Integer> skills;
    private Map<String, Integer> defenses;
    private Integer power;

    private Integer health = 100;

    public Fighter() {
        // Needed for YAML serialization
    }

    public Fighter(String name, Map<String, Integer> skills, Map<String, Integer> defenses, Integer power, Integer health) {
        this.name = name;
        this.skills = skills;
        this.defenses = defenses;
        this.power = power;
        this.health = health;
    }

    public List<String> getDefenseKeys() {
        List<String> keys = new ArrayList<>(defenses.size());
        keys.addAll(defenses.keySet());
        return keys;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Map<String, Integer> getSkills() { return skills; }

    public void setSkills(Map<String, Integer> skills) { this.skills = skills; }

    public Map<String, Integer> getDefenses() { return defenses; }

    public void setDefenses(Map<String, Integer> defenses) { this.defenses = defenses; }

    public Integer getPower() { return power; }

    public void setPower(Integer power) { this.power = power; }

    public Integer getHealth() { return health; }

    public void reduceHealth(Integer sub) { health -= sub; }

    @Override
    public String toString() {
        return "Fighter{" +
            "name='" + name + '\'' +
            ", skills=" + skills +
            ", defenses=" + defenses +
            ", power=" + power +
            ", health=" + health +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Fighter fighter = (Fighter) o;
        return Objects.equals(name, fighter.name) &&
            Objects.equals(skills, fighter.skills) &&
            Objects.equals(defenses, fighter.defenses) &&
            Objects.equals(power, fighter.power) &&
            Objects.equals(health, fighter.health);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, skills, defenses, power, health);
    }
}
