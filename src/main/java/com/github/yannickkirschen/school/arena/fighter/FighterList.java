package com.github.yannickkirschen.school.arena.fighter;

import java.util.List;
import java.util.Objects;

public class FighterList {
    private List<Fighter> fighters;

    public FighterList() {
        // Needed for YAML serialization
    }

    public Fighter get(String name) {
        for (Fighter fighter : fighters) {
            if (fighter.getName().equals(name)) {
                return fighter;
            }
        }
        return null;
    }

    public List<Fighter> getFighters() { return fighters; }

    public void setFighters(List<Fighter> fighters) { this.fighters = fighters; }

    @Override
    public String toString() {
        return "FighterList{" +
            "fighters=" + fighters +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        FighterList that = (FighterList) o;
        return Objects.equals(fighters, that.fighters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fighters);
    }
}
