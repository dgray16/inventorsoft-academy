package com.inventorsoft.domain.converter;

import com.inventorsoft.domain.dictionary.Nationality;
import javax.persistence.AttributeConverter;
import java.util.Arrays;

import static java.util.Objects.isNull;

public class NationalityConverter implements AttributeConverter<Nationality, String> {

    @Override
    public String convertToDatabaseColumn(Nationality attribute) {
        return isNull(attribute) ? null : attribute.getName();
    }

    @Override
    public Nationality convertToEntityAttribute(String dbData) {
        return isNull(dbData) 
                ? null
                : Arrays.stream(Nationality.values())
                    .filter(enumValue -> enumValue.getName().equals(dbData))
                    .findFirst()
                    .orElseThrow(RuntimeException::new);
    }

}
