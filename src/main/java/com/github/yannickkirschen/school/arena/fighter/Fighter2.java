package com.github.yannickkirschen.school.arena.fighter;

import java.util.List;
import java.util.Objects;

public class Fighter2 {
    private Integer id;
    private String name;
    private List<Skill> skills;
    private Integer power;

    public Fighter2() {
        // Needed for YAML serialization
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Skill> getSkills() { return skills; }

    public void setSkills(List<Skill> skills) { this.skills = skills; }

    public Integer getPower() { return power; }

    public void setPower(Integer power) { this.power = power; }

    @Override
    public String toString() {
        return "Fighter2{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", skills=" + skills +
            ", power=" + power +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Fighter2 fighter2 = (Fighter2) o;
        return Objects.equals(id, fighter2.id) &&
            Objects.equals(name, fighter2.name) &&
            Objects.equals(skills, fighter2.skills) &&
            Objects.equals(power, fighter2.power);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, skills, power);
    }
}
