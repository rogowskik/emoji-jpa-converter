package com.github.rogowskik.converter;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class EmojiConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String toDatabaseColumn) {
        if (StringUtils.isEmpty(toDatabaseColumn)) {
            return toDatabaseColumn;
        }
        try {
            return EmojiUtils.toAlias(toDatabaseColumn);
        } catch (Exception e) {
            return toDatabaseColumn;
        }
    }

    @Override
    public String convertToEntityAttribute(String toEntity) {
        if (StringUtils.isEmpty(toEntity)) {
            return toEntity;
        }
        try {
            return EmojiUtils.toEmoji(toEntity);
        } catch (Exception e) {
            return toEntity;
        }
    }
}
