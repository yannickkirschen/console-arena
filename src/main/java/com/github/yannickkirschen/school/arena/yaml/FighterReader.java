package com.github.yannickkirschen.school.arena.yaml;

import com.github.yannickkirschen.school.arena.fighter.YamlFighters;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

/**
 * The {@link FighterReader} allows reading the default YAML file and parsing all fighters.
 *
 * @author Yannick Kirschen
 * @see com.github.yannickkirschen.school.arena.fighter.YamlFighters
 * @see com.github.yannickkirschen.school.arena.fighter.YamlFighter
 * @see com.github.yannickkirschen.school.arena.fighter.YamlSkill
 * @since 1.0.0
 */
public final class FighterReader {
    private FighterReader() {}

    /**
     * Parses all fighters from the default YAML file.
     *
     * @return The parsed fighters.
     */
    public static YamlFighters read() {
        Yaml yaml = new Yaml(new Constructor(YamlFighters.class));
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("fighters.yml");
        return yaml.load(inputStream);
    }
}
