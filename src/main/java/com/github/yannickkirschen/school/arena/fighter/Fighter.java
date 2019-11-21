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

    private List<String> skillList;
    private List<String> defenseList;

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

    public List<String> getSkillKeys() {
        if (skillList == null) {
            skillList = new ArrayList<>(skills.keySet());
        }
        return skillList;
    }

    public List<String> getDefenseKeys() {
        if (defenseList == null) {
            defenseList = new ArrayList<>(defenses.size());
        }
        return defenseList;
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

    public String getPrettySkills() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < skillList.size(); i++) {
            String skill = skillList.get(i);
            sb.append("\n").append(i).append(" - ").append(skill).append(" - Power: ").append(skills.get(skill));
        }

        return sb.append("\n").toString();
    }

    public String getPrettyDefenses() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < defenseList.size(); i++) {
            String defense = defenseList.get(i);
            sb.append("\n").append(i).append(" - ").append(defense).append(" - Power: ").append(defenses.get(defense));
        }

        return sb.append("\n").toString();
    }

    public String getSkillName(Integer id) {
        return skillList.get(id);
    }

    public String getDefenseName(Integer id) {
        return defenseList.get(id);
    }

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
