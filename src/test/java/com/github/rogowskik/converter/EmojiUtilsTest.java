package com.github.rogowskik.converter;

import com.github.rogowskik.holder.Emoji;
import com.github.rogowskik.initalizer.Loader;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class EmojiUtilsTest {

    static List<Emoji> emojis;

    @BeforeClass
    public static void initList() {
        emojis = Loader.init();
    }


    @Test
    public void string_without_emojis() {
        // GIVEN
        String str = "That's a nice joke!!!!";

        // WHEN
        String result = EmojiUtils.toAlias(str);
        // THEN
        assertEquals(
                "That's a nice joke!!!!",
                result
        );
    }

    @Test
    public void string_starting_with_emoji() {
        // GIVEN
        String str = "🤣😀🤣😉";

        // WHEN
        String result = EmojiUtils.toAlias(str);
        // THEN
        assertEquals(
                ":rolling_on_the_floor_laughing::rolling_on_the_floor_laughing::rolling_on_the_floor_laughing::rolling_on_the_floor_laughing:",
                result
        );
    }

    @Test
    public void text_starting_with_emoji() {
        // GIVEN
        String str = "🎇test%";

        // WHEN
        String result = EmojiUtils.toAlias(str);
        // THEN
        assertEquals(
                ":sparkler:test%",
                result
        );
    }


    @Test
    public void text_ends_with_emoji() {
        // GIVEN
        String str = "test🧨";

        // WHEN
        String result = EmojiUtils.toAlias(str);
        // THEN
        assertEquals(
                "test:firecracker:",
                result
        );
    }


    @Test
    public void html_parsing_without_emojis() {
        // GIVEN
        String str = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<h1>00:12:00 AM</h1>\n" +
                "\n" +
                "<p>Food lover <3</p>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n";

        // WHEN
        String result = EmojiUtils.toAlias(str);
        // THEN
        assertEquals(
                str,
                result
        );
    }

    @Test
    public void html_parsing_with_emojis() {
        // GIVEN
        String str = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<h1>00:12:00 🎐 AM</h1>\n" +
                "\n" +
                "<p>Food lover <3</p>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n";

        // WHEN
        String result = EmojiUtils.toAlias(str);
        // THEN
        assertEquals(
                "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<body>\n" +
                        "\n" +
                        "<h1>00:12:00 :wind_chime: AM</h1>\n" +
                        "\n" +
                        "<p>Food lover <3</p>\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>\n",
                result
        );
    }

    @Test
    public void should_return_symbol() {
        // GIVEN
        String alias = ":smiling_face_with_halo:";
        String result = EmojiUtils.toEmoji(alias);
        // THEN
        assertEquals(
                "😇",
                result
        );
    }

    @Test
    public void should_return_three_symbols() {
        // GIVEN
        String alias = ":smiling_face_with_halo:";
        String result = EmojiUtils.toEmoji(alias + alias + alias);
        // THEN
        assertEquals(
                "😇😇😇",
                result
        );
    }
    @Test
    public void should_return_symbol_with_normal_text() {
        // GIVEN
        String alias = ":smiling_face_with_halo: I am sending you a :love_letter: :upside_down_face::upside_down_face:";
        //WHEN
        String result = EmojiUtils.toEmoji(alias);

        // THEN
        assertEquals(
                "😇 I am sending you a 💌 🙃🙃",
                result
        );
    }

    @Test
    public void html_should_remain_unmodified() {
        // GIVEN
        String str = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<h1>00:12:00 AM</h1>\n" +
                "\n" +
                "<p>Food lover <3</p>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n";
        //WHEN
        String result = EmojiUtils.toEmoji(str);
        System.out.println(result);
        // THEN
        assertEquals(
                str,
                result
        );
    }

}