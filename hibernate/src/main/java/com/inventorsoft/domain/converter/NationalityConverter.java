package com.inventorsoft.domain.converter;

import com.inventorsoft.domain.dictionary.Nationality;
import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Objects;

public class NationalityConverter implements AttributeConverter<Nationality, String> {

    @Override
    public String convertToDatabaseColumn(Nationality attribute) {
        return Objects.isNull(attribute) ? null : attribute.getName();
    }

    @Override
    public Nationality convertToEntityAttribute(String dbData) {
        return Objects.isNull(dbData)
                ? null
                : Arrays.stream(Nationality.values())
                    .filter(enumValue -> enumValue.getName().equals(dbData))
                    .findFirst()
                    .orElseThrow(RuntimeException::new);
    }

}
