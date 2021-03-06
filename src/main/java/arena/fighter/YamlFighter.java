package arena.fighter;

import java.util.List;

import lombok.Data;

/**
 * The {@link YamlFighter} is only used as a transport object to parse a fighter from the YAML file into it. See {@link Fighter} for more details.
 *
 * @author Yannick Kirschen
 * @see Fighter
 * @since 1.0.0
 */
@Data
public final class YamlFighter {
    private String name;
    private Integer power;
    private List<YamlSkill> attacks;
    private List<YamlSkill> defenses;
}
