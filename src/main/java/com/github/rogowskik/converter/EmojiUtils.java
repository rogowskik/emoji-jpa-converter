package com.github.rogowskik.converter;

import com.github.rogowskik.holder.AliasHolder;
import com.github.rogowskik.holder.UnicodeHolder;
import lombok.experimental.UtilityClass;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class EmojiUtils {

    private static final String EMOJI_REGEX = "[\uD83C-\uDBFF\uDC00-\uDFFF]+";
    private static final Pattern EMOJI_PATTERN = Pattern.compile(EMOJI_REGEX);

    private static final String ALIAS_REGEX = "(?<=:)\\+?([a-zA-Z_])+(?=:)";
    private static final Pattern ALIAS_PATTERN = Pattern.compile(ALIAS_REGEX);


   public static String toEmoji(String input) {
        List<AliasHolder> aliasHolders = getPossibleAliases(input);
        String result = input;
        for (AliasHolder candidate : aliasHolders) {
            String alias = ":" + candidate.getAlias() + ":";
            String symbolByShortName = EmojiManager.getSymbolByAlias(alias);
            result = result.replace(
                    alias,
                    symbolByShortName
            );
        }
        return result;
    }

    private static List<AliasHolder> getPossibleAliases(String input) {
        List<AliasHolder> candidates = new ArrayList<>();
        Matcher matcher = ALIAS_PATTERN.matcher(input);
        matcher.useTransparentBounds(true);
        while (matcher.find()) {
            String match = matcher.group();
            candidates.add(new AliasHolder(match));
        }
        return candidates;
    }

    public static String toAlias(String text) {
        String input = new String(text.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        Matcher matcher = EMOJI_PATTERN.matcher(input);

        List<UnicodeHolder> unicodeHolders = new ArrayList<>();
        while (matcher.find()) {
            UnicodeHolder unicodeHolder = new UnicodeHolder(
                    matcher.start(),
                    matcher.end()
            );
            unicodeHolders.add(unicodeHolder);
        }
        return unicodeHolders.isEmpty() ? text : transform(text, unicodeHolders);
    }

    private static String transform(String input, List<UnicodeHolder> unicodeHolders) {
        int prev = 0;
        StringBuilder sb = new StringBuilder(input.length());
        for (UnicodeHolder candidate : unicodeHolders) {
            sb.append(input, prev, candidate.getStart());
            for (int i = candidate.getStart(); i < candidate.getEnd(); i++) {
                i++;
                sb.append(EmojiManager.getAliasBySymbol(new String(Character.toChars(input.codePointAt(candidate.getStart())))));
            }
            prev = candidate.getEnd();
        }
        return sb.append(input.substring(prev)).toString();
    }
}

