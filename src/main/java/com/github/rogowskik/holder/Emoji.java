package com.github.rogowskik.holder;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Emoji {

    private String symbol;
    private String alias;
    private String group;               //Todo implement me
    private String version;             //Todo implement me
    private Boolean skinToneSupport;    //Todo implement me

}