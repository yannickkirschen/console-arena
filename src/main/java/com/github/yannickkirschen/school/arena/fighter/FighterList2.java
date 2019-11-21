package com.github.yannickkirschen.school.arena.fighter;

import java.util.List;
import java.util.Objects;

public class FighterList2 {
    private List<Fighter2> fighters;

    public FighterList2() {
        // Needed for YAML serialization
    }

    public List<Fighter2> getFighters() {
        return fighters;
    }

    public void setFighters(List<Fighter2> fighters) {
        this.fighters = fighters;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Fighter2 fighter : fighters) {
            sb.append("\n").append(fighter.getId()).append(" - ").append(fighter.getName()).append(" (Power: ").append(fighter.getPower()).append(")");
        }

        return sb.append("\n").toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        FighterList2 that = (FighterList2) o;
        return Objects.equals(fighters, that.fighters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fighters);
    }
}
