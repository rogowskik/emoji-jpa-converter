package com.emoji.initalizer;

import com.emoji.holder.Emoji;
import com.emoji.support.EmojiProperties;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Loader {

    private static final String EMOJS_PATH = "data-emojis.json";

    public static List<Emoji> init() {
        Reader reader = getReader();
        List<Emoji> emojiList = new ArrayList<>();
        try {
            JsonObject jsonObject = (JsonObject) Jsoner.deserialize(reader);
            jsonObject.keySet().stream()
                    .filter(key -> jsonObject.get(key) instanceof JsonObject).forEach(key -> {
                JsonObject json = (JsonObject) jsonObject.get(key);
                emojiList.add(buildEmoji(key, json));
            });

        } catch (JsonException e) {
            throw new EmojiModuleInitialiationException("Error while parsing to emoji model");
        }
        return emojiList;
    }

    private static Emoji buildEmoji(String key, JsonObject json) {
        return Emoji.builder()
                .symbol(key)
                .group((String) json.get(EmojiProperties.GROUP.getFileValue()))
                .alias(":" + json.get(EmojiProperties.SHORT_NAME.getFileValue()) + ":")
                .skinToneSupport((Boolean) json.get(EmojiProperties.SKIN_TONE_SUPPORT.getFileValue()))
                .version((String) json.get(EmojiProperties.VERSION.getFileValue()))
                .build();
    }

    private static Reader getReader() {
        Reader reader;
        try {
            URI uri = Thread.currentThread().getContextClassLoader().getResource(EMOJS_PATH).toURI();
            reader = Files.newBufferedReader(Paths.get(uri));
        } catch (URISyntaxException | IOException e) {
            throw new EmojiModuleInitialiationException("Error while reading emojis json file");
        }
        return reader;
    }


}
