package arena.fighter;

import java.util.*;

import lombok.Data;

/**
 * The {@link YamlFighters} are only used as a transport object to parse all fighters from the YAML file into it.
 *
 * @author Yannick Kirschen
 * @since 1.0.0
 */
@Data
public final class YamlFighters {
    private List<YamlFighter> fighters;

    public List<Fighter> asFighters() {
        List<Fighter> fighterList = new LinkedList<>();

        for (YamlFighter fighter : fighters) {
            fighterList.add(Fighter.fromYamlFighter(fighter));
        }

        return fighterList;
    }
}
