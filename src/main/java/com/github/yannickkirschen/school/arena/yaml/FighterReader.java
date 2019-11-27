package com.github.yannickkirschen.school.arena.yaml;

import com.github.yannickkirschen.school.arena.fighter.YamlFighters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(FighterReader.class);

    private FighterReader() {}

    /**
     * Parses all fighters from the YAML file.
     *
     * @param args The command line arguments. If the first arguments is set, the fighters will be read from this file.
     *
     * @return The parsed fighters.
     */
    public static YamlFighters read(String[] args) {
        InputStream inputStream;
        if (args.length > 0) {
            try {
                inputStream = new FileInputStream(args[1]);
            } catch (FileNotFoundException e) {
                LOGGER.info("The provided file does not exist. Using default one.");
                inputStream = getResourceFile();
            }
        } else {
            inputStream = getResourceFile();
        }
        Yaml yaml = new Yaml(new Constructor(YamlFighters.class));
        return yaml.load(inputStream);
    }

    /**
     * @return The input stream for the default YAML file.
     */
    private static InputStream getResourceFile() {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("fighters.yml");
    }
}
