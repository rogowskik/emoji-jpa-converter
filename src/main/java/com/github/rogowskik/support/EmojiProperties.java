package com.github.rogowskik.support;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EmojiProperties {

    GROUP("group"),
    SKIN_TONE_SUPPORT("skin_tone_support"),
    SHORT_NAME("name"),
    VERSION("emoji_version");

    private String fileValue;
}
