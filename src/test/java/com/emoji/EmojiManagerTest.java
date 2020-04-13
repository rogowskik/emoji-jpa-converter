package com.emoji;

import com.emoji.holder.Emoji;
import com.emoji.support.EmojiManager;
import com.emoji.initalizer.Loader;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class EmojiManagerTest {

    static List<Emoji> emojis;

    @BeforeClass
    public static void initList() {
        emojis = Loader.init();
    }

    @Test
    public void get_alias_by_symbol_returns_empty_string() {
        // GIVEN
        // WHEN
        String emoji = EmojiManager.getAliasBySymbol("dummytext");
        // THEN
        assertTrue(emoji.isEmpty());
    }

    @Test
    public void get_symbol_by_short_name_returns_empty_string() {
        // GIVEN
        // WHEN
        String emoji = EmojiManager.getSymbolByAlias("dummytext");

        // THEN
        assertTrue(emoji.isEmpty());
    }

    @Test
    public void get_alias_by_symbol_returns_empty_string_for_wrong_symbol() {
        // GIVEN

        // WHEN
        String emoji = EmojiManager.getAliasBySymbol("😀+6");

        // THEN
        assertTrue(emoji.isEmpty());
    }

    @Test
    public void get_alias_by_symbol_returns_alias() {
        // GIVEN

        // WHEN
        String emoji = EmojiManager.getAliasBySymbol("😀");

        // THEN
        assertEquals(emoji, ":grinning_face:");
    }

    @Test
    public void get_alias_by_symbol_returns_symbol() {
        // GIVEN

        // WHEN
        String emoji = EmojiManager.getSymbolByAlias(":grinning_face:");

        // THEN
        assertEquals(emoji, "😀");
    }



}
