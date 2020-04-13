package com.emoji;

import com.emoji.holder.Emoji;
import com.emoji.initalizer.Loader;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class LoaderTest {

    public static final int EMOJIS_SIZE = 1738; // known size

    @Test
    public void load_all_test() throws IOException {
        // GIVEN
        // WHEN
        List<Emoji> emojis = Loader.init();
        // THEN
        assertEquals(EMOJIS_SIZE, emojis.size());
    }


    @Test
    public void all_emojis_have_short_name() {
        // GIVEN
        List<Emoji> emojis = Loader.init();
        // WHEN
        long emojiWithNotEmptyShortName = emojis.stream().filter(x -> StringUtils.isNoneEmpty(x.getAlias())).count();
        // THEN
        assertEquals(EMOJIS_SIZE, emojiWithNotEmptyShortName);
    }

    @Test
    public void all_emojis_have_symbol() {
        // GIVEN
        List<Emoji> emojis = Loader.init();
        // WHEN
        long emojiWithNotEmptySymbol = emojis.stream().filter(x -> StringUtils.isNoneEmpty(x.getSymbol())).count();
        // THEN
        assertEquals(EMOJIS_SIZE, emojiWithNotEmptySymbol);
    }


    @Test
    public void all_emojis_have_key_formatted() {
        // GIVEN
        List<Emoji> emojis = Loader.init();
        // WHEN
        long emojiWithProperKey = emojis
                .stream()
                .filter(x -> (x.getAlias().startsWith(":") && x.getAlias().endsWith(":")))
                .count();
        // THEN
        assertEquals(EMOJIS_SIZE, emojiWithProperKey);
    }
}
