package com.github.rogowskik.converter;

import com.github.rogowskik.holder.Emoji;
import com.github.rogowskik.initalizer.Loader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmojiManager {

    private static final Map<String, Emoji> SYMBOL_TO_EMOJI;

    private static final Map<String, Emoji> ALIAS_TO_EMOJI;

    static {
       Map<String, Emoji> aliasToEmoji = new HashMap<>();
       Map<String, Emoji> symbolToEmoji = new HashMap<>();
       List<Emoji> init = Loader.init();
        init.forEach(emoji -> {
            aliasToEmoji.put(emoji.getAlias(), emoji);
            symbolToEmoji.put(emoji.getSymbol(), emoji);
        });

        ALIAS_TO_EMOJI = Collections.unmodifiableMap(aliasToEmoji);
        SYMBOL_TO_EMOJI = Collections.unmodifiableMap(symbolToEmoji);
    }

    public static String getAliasBySymbol(String symbol) {
        Emoji emoji = SYMBOL_TO_EMOJI.get(symbol);
        return emoji != null ? emoji.getAlias() : "";
    }

    public static String getSymbolByAlias(String alias) {
        Emoji emoji = ALIAS_TO_EMOJI.get(alias);
        return emoji != null ? emoji.getSymbol() : "";
    }
}
