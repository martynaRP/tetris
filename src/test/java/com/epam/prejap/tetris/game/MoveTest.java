package com.epam.prejap.tetris.game;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test(groups = "Game")
public class MoveTest {

    @Test(dataProvider = "keys")
    public void navigationKeysShouldBeConfigurable(char[] providedKeys) {
        //given
        char[] defaultKeys = Move.defaultKeys();

        //when
        char[] actualKeys = Move.modifyNavigationKeys(providedKeys);

        //then
        assertNotEquals(actualKeys, defaultKeys, "Navigation keys should be configurable but are not.");
    }

    @Test(dataProvider = "keys")
    public void navigationKeysCanBeModified(char[] providedKeys) {
        //given

        //when
        char[] actualKeys = Move.modifyNavigationKeys(providedKeys);

        //then
        assertEquals(actualKeys, providedKeys, "Navigation keys should have been modified to provided ones but" +
                " were not.");
    }

    @DataProvider
    public static Object[] keys() {
        return new Object[]{
                new char[]{'a', 's', 'd'},
                new char[]{',', '.', '/'},
                new char[]{'~', '!', '@'},
                new char[]{'1', '2', '3'}
        };
    }
}
