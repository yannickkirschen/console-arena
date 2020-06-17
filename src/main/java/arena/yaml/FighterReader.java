package arena.yaml;

import java.io.*;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import arena.fighter.*;
import lombok.extern.log4j.Log4j2;

/**
 * The {@link FighterReader} allows reading the default YAML file and parsing all fighters.
 *
 * @author Yannick Kirschen
 * @see YamlFighters
 * @see YamlFighter
 * @see YamlSkill
 * @since 1.0.0
 */
@Log4j2
public final class FighterReader {
    private FighterReader() {
    }

    /**
     * Parses all fighters from the YAML file.
     *
     * @param args The command line arguments. If the first arguments is set, the fighters will be read from this file.
     * @return The parsed fighters.
     */
    public static YamlFighters read(String[] args) {
        InputStream inputStream;
        if (args.length > 0) {
            try {
                inputStream = new FileInputStream(args[1]);
            } catch (FileNotFoundException e) {
                log.info("The provided file does not exist. Using default one.");
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
