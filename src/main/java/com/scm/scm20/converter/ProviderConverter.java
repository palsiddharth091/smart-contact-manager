package com.scm.scm20.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.scm.scm20.constants.PROVIDER;


// To handle unknown enum values gracefully in JPA/Hibernate (for example, mapping them to a default or null instead of throwing an exception), you can use a custom attribute converter.
@Converter(autoApply = true)
public class ProviderConverter implements AttributeConverter<PROVIDER, String> {

    @Override
    public String convertToDatabaseColumn(PROVIDER attribute) {
        return attribute != null ? attribute.name() : null;
    }

    @Override
    public PROVIDER convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        try {
            return PROVIDER.valueOf(dbData);
        } catch (IllegalArgumentException ex) {
            // Unknown value in DB, handle gracefully:
            return PROVIDER.SELF; // or return null, or a special UNKNOWN value
        }
    }
}
