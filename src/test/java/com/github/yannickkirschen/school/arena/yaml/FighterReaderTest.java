package com.github.yannickkirschen.school.arena.yaml;

import com.github.yannickkirschen.school.arena.fighter.YamlFighter;
import com.github.yannickkirschen.school.arena.fighter.YamlFighters;
import com.github.yannickkirschen.school.arena.fighter.YamlSkill;

import org.junit.Assert;
import org.junit.Test;

public class FighterReaderTest {
    private static final String[] ARGS = { "FighterReaderTest.class", "src/test/resources/fighters_test.yml" };
    private static final YamlFighters FIGHTERS = FighterReader.read(ARGS);
    private static final YamlFighter FIGHTER_1 = FIGHTERS.getFighters().get(0);
    private static final YamlFighter FIGHTER_2 = FIGHTERS.getFighters().get(1);

    private static final YamlSkill ATTACK_1_1 = FIGHTER_1.getAttacks().get(0);
    private static final YamlSkill ATTACK_1_2 = FIGHTER_1.getAttacks().get(1);

    private static final YamlSkill ATTACK_2_1 = FIGHTER_2.getAttacks().get(0);
    private static final YamlSkill ATTACK_2_2 = FIGHTER_2.getAttacks().get(1);

    private static final YamlSkill DEFENSE_1_1 = FIGHTER_1.getDefenses().get(0);
    private static final YamlSkill DEFENSE_1_2 = FIGHTER_1.getDefenses().get(1);

    private static final YamlSkill DEFENSE_2_1 = FIGHTER_2.getDefenses().get(0);
    private static final YamlSkill DEFENSE_2_2 = FIGHTER_2.getDefenses().get(1);

    @Test
    public void readName1Test() { Assert.assertEquals("Vader", FIGHTER_1.getName()); }

    @Test
    public void readName2Test() { Assert.assertEquals("Yoda", FIGHTER_2.getName()); }

    @Test
    public void readPower1test() { Assert.assertEquals(Integer.valueOf(18), FIGHTER_1.getPower()); }

    @Test
    public void readPower2test() { Assert.assertEquals(Integer.valueOf(20), FIGHTER_2.getPower()); }

    @Test
    public void read1Attack1NameTest() { Assert.assertEquals("punch", ATTACK_1_1.getName()); }

    @Test
    public void read1Attack2NameTest() { Assert.assertEquals("kick", ATTACK_1_2.getName()); }

    @Test
    public void read1Defense1NameTest() { Assert.assertEquals("shield", DEFENSE_1_1.getName()); }

    @Test
    public void read1Defense2NameTest() { Assert.assertEquals("run away", DEFENSE_1_2.getName()); }

    @Test
    public void read2Attack1NameTest() { Assert.assertEquals("punch", ATTACK_2_1.getName()); }

    @Test
    public void read2Attack2NameTest() { Assert.assertEquals("kick", ATTACK_2_2.getName()); }

    @Test
    public void read2Defense1NameTest() { Assert.assertEquals("shield", DEFENSE_2_1.getName()); }

    @Test
    public void read2Defense2NameTest() { Assert.assertEquals("run away", DEFENSE_2_2.getName()); }

    @Test
    public void read1Attack1PowerTest() { Assert.assertEquals(Integer.valueOf(1), ATTACK_1_1.getPower()); }

    @Test
    public void read1Attack2PowerTest() { Assert.assertEquals(Integer.valueOf(2), ATTACK_1_2.getPower()); }

    @Test
    public void read1Defense1PowerTest() { Assert.assertEquals(Integer.valueOf(1), DEFENSE_1_1.getPower()); }

    @Test
    public void read1Defense2PowerTest() { Assert.assertEquals(Integer.valueOf(2), DEFENSE_1_2.getPower()); }

    @Test
    public void read2Attack1PowerTest() { Assert.assertEquals(Integer.valueOf(1), ATTACK_2_1.getPower()); }

    @Test
    public void read2Attack2PowerTest() { Assert.assertEquals(Integer.valueOf(2), ATTACK_2_2.getPower()); }

    @Test
    public void read2Defense1PowerTest() { Assert.assertEquals(Integer.valueOf(1), DEFENSE_2_1.getPower()); }

    @Test
    public void read2Defense2PowerTest() { Assert.assertEquals(Integer.valueOf(2), DEFENSE_2_2.getPower()); }
}
