package com.github.yannickkirschen.school.arena.fighter;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * A {@link Fighter} represents a character in the console game. A fighter has an unique ID, a unique name, a power, skills and a health.
 * <p>
 * The ID gets generated when reading the YAML file with all fighters. Its only purpose is to allow an easy selection of a fighter in the console. Though the ID
 * allows identifying a fighter, the name must also be unique, so that it does not lead to misunderstandings.
 * <p>
 * The power is an abstract value, saying how strong a fighter is. It is used to calculate, whether a player won or lost a fight.
 * <p>
 * A fighter can have several skills. These are also in the YAML file with all fighters. A skill may either be an attack (such as "kick") or a defense (such as
 * "shield"). Each skill has a power assigned to it, that is also included in the fight calculation.
 * <p>
 * The fighter's default health is 100. It gets decreased when the fighter lost a fight and cannot be increased. Basically, the result of the fight calculation
 * gets subtracted from the health.
 * <p>
 * Two fighters are equal to each other when their IDs and names are equal.
 * <p>
 * Instantiation is only possible via {@link #fromYamlFighter(YamlFighter, Integer)}.
 *
 * @author Yannick Kirschen
 * @see Skill
 * @see Mode
 * @since 1.0.0
 */
public final class Fighter {
    private final Integer id;
    private final String name;
    private final Integer power;
    private Integer health = 100;

    private List<Skill> attacks = new LinkedList<>();
    private List<Skill> defenses = new LinkedList<>();

    private Fighter(Integer id, String name, Integer power) {
        this.id = id;
        this.name = name;
        this.power = power;
    }

    /**
     * Constructs a new fighter from a {@link YamlFighter}.
     *
     * @param yamlFighter The fighter read from the YAML file with all fighters to create the fighter from.
     * @param id          The ID of the new fighter. Must be unique.
     *
     * @return A new fighter based on the one read from the YAML file.
     */
    public static Fighter fromYamlFighter(YamlFighter yamlFighter, Integer id) {
        Fighter fighter = new Fighter(id, yamlFighter.getName(), yamlFighter.getPower());

        // Extract attacks
        List<YamlSkill> attacks = yamlFighter.getAttacks();
        for (int i = 0; i < attacks.size(); i++) {
            fighter.addAttack(Skill.fromYamlSkill(attacks.get(i), i));
        }

        // Extract defenses.
        List<YamlSkill> defenses = yamlFighter.getDefenses();
        for (int i = 0; i < defenses.size(); i++) {
            fighter.addDefense(Skill.fromYamlSkill(defenses.get(i), i));
        }

        return fighter;
    }

    /**
     * Reduces the health of the fighter by the absolute value a specific number. In fact, this should be the result of the fight calculation.
     *
     * @param value The value to reduce the health by. The absolute value is taken.
     */
    public void reduceHealth(Integer value) { health = health - Math.abs(value); }

    /**
     * Constructs all skills of a specific mode in order to be displayed as a menu for the user. Such as:
     *
     * <pre>
     *     How do you want to attack?
     *
     *     0 - Punch (Power: 1)
     *     1 - Kick (Power: 2)
     * </pre>
     * <p>
     * The user can now select which attack to use.
     *
     * @param mode The mode of the skills to display.
     *
     * @return All skills of a specific mode as a pretty string.
     */
    public String skillsAsString(Mode mode) {
        StringBuilder sb = new StringBuilder().append("\n").append("How do you want to ").append(mode == Mode.ATTACK ? "attack" : "defend").append("?");

        for (Skill skill : getSkills(mode)) {
            sb.append("\n").append(skill.getId()).append(" - ").append(skill.getName()).append("(Power: ").append(skill.getPower()).append(")");
        }

        return sb.append("\n").toString();
    }

    /**
     * Selects a random skill of a specific mode.
     *
     * @param mode The mode of the skill to randomly select.
     *
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
     *
     * @return All skills for a specific mode. An empty list, if <code>mode</code> is <code>null</code>.
     */
    public List<Skill> getSkills(Mode mode) {
        if (mode == Mode.ATTACK) {

            return attacks;
        } else if (mode == Mode.DEFENSE) {
            return defenses;
        }
        return new LinkedList<>();
    }

    public Integer getId() { return id; }

    public String getName() { return name; }

    public Integer getPower() { return power; }

    public Integer getHealth() { return health; }

    private void addAttack(Skill skill) { attacks.add(skill); }

    private void addDefense(Skill skill) { defenses.add(skill); }

    @Override
    public String toString() {
        return "Fighter{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", power=" + power +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Fighter fighter = (Fighter) o;
        return id.equals(fighter.id) && name.equals(fighter.name);
    }

    @Override
    public int hashCode() { return Objects.hash(id, name); }
}
