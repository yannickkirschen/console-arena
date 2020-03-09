package com.github.yannickkirschen.school.arena.fighter;

import lombok.Data;

/**
 * The {@link YamlSkill} is only used as a transport object to parse a skill from the YAML file into it. See {@link Skill} for more details.
 *
 * @author Yannick Kirschen
 * @see Skill
 * @since 1.0.0
 */
@Data
public final class YamlSkill {
    private String name;
    private Integer power;
}
