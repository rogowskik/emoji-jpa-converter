package com.github.rogowskik.initalizer;

import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import com.github.rogowskik.holder.Emoji;
import com.github.rogowskik.support.EmojiProperties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Loader {

    private static final String EMOJS_PATH = "/data-emojis.json";

    public static List<Emoji> init() {
        String reader = getEmojisAsString();
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



    private static String getEmojisAsString() {
        InputStream resourceAsStream = Loader.class.getResourceAsStream(EMOJS_PATH);
        return new BufferedReader(new InputStreamReader(resourceAsStream))
                .lines()
                .parallel()
                .collect(Collectors.joining("\n"));
    }


}
