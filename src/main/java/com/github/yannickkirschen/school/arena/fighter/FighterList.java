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

    public Fighter get(Integer id) { return fighters.get(id); }

    public Fighter getAndRemove(Integer id) {
        Fighter fighter = fighters.get(id);
        fighters.remove(fighter);
        return fighter;
    }

    public Integer length() { return fighters.size(); }

    public void setFighters(List<Fighter> fighters) { this.fighters = fighters; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("These are the available fighters:");
        sb.append("\n");

        for (int i = 0; i < fighters.size(); i++) {
            Fighter fighter = fighters.get(i);
            sb.append("\n").append(i).append(" - ").append(fighter.getName()).append(" - Power: ").append(fighter.getPower());
        }

        return sb.append("\n").toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        FighterList that = (FighterList) o;
        return Objects.equals(fighters, that.fighters);
    }

    @Override
    public int hashCode() { return Objects.hash(fighters); }
}
