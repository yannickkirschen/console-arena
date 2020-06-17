package arena.fighter;

import java.util.*;

import lombok.*;

/**
 * A {@link Fighter} represents a character in the console game. A fighter has an unique ID, a unique name, a power, skills and a health.
 * <p>
 * The name must be unique, so that it does not lead to misunderstandings.
 * <p>
 * The power is an abstract value, saying how strong a fighter is. It is used to calculate, whether a player won or lost a fight.
 * <p>
 * A fighter can have several skills. These are also in the YAML file with all fighters. A skill may either be an attack (such as "kick") or a defense (such as
 * "shield"). Each skill has a power assigned to it, that is also included in the fight calculation.
 * <p>
 * The fighter's default health is 100. It gets decreased when the fighter lost a fight and cannot be increased. Basically, the result of the fight calculation
 * gets subtracted from the health.
 * <p>
 * Two fighters are equal to each other when their names are equal.
 * <p>
 * Instantiation is only possible via {@link #fromYamlFighter(YamlFighter)}.
 *
 * @author Yannick Kirschen
 * @see Skill
 * @see Mode
 * @since 1.0.0
 */
@ToString
public final class Fighter {
    @Getter
    private final String name;

    @Getter
    private final Integer power;
    private final List<Skill> attacks = new LinkedList<>();
    private final List<Skill> defenses = new LinkedList<>();

    @Getter
    private Integer health = 100;

    private Fighter(String name, Integer power) {
        this.name = name;
        this.power = power;
    }

    /**
     * Constructs a new fighter from a {@link YamlFighter}.
     *
     * @param yamlFighter The fighter read from the YAML file with all fighters to create the fighter from.
     * @return A new fighter based on the one read from the YAML file.
     */
    public static Fighter fromYamlFighter(YamlFighter yamlFighter) {
        Fighter fighter = new Fighter(yamlFighter.getName(), yamlFighter.getPower());

        // Extract attacks
        for (YamlSkill attack : yamlFighter.getAttacks()) {
            fighter.addAttack(Skill.fromYamlSkill(attack));
        }

        // Extract defenses
        for (YamlSkill defense : yamlFighter.getDefenses()) {
            fighter.addDefense(Skill.fromYamlSkill(defense));
        }

        return fighter;
    }

    /**
     * Reduces the health of the fighter by the absolute value a specific number. In fact, this should be the result of the fight calculation.
     *
     * @param value The value to reduce the health by. The absolute value is taken.
     */
    public void reduceHealth(Integer value) {
        health = health - Math.abs(value);
    }

    /**
     * Selects a random skill of a specific mode.
     *
     * @param mode The mode of the skill to randomly select.
     * @return A random skill.
     */
    public Skill getRandomSkill(Mode mode) {
        List<Skill> skillList = getSkills(mode);
        return skillList.get(new Random().nextInt(skillList.size()));
    }

    /**
     * Selects all skill of a specific mode.
     *
     * @param mode The mode to get all the skills to.
     * @return All skills for a specific mode. An empty list, if <code>mode</code> is <code>null</code>.
     */
    public List<Skill> getSkills(Mode mode) {
        if (mode == Mode.ATTACK) {
            return attacks;
        }
        return defenses;
    }

    private void addAttack(Skill skill) {
        attacks.add(skill);
    }

    private void addDefense(Skill skill) {
        defenses.add(skill);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Fighter fighter = (Fighter) o;
        return name.equals(fighter.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
