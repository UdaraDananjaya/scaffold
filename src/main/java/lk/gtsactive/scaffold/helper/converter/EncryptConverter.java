package lk.gtsactive.scaffold.helper.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lk.gtsactive.scaffold.util.crypto.CryptoUtil;

@Converter(autoApply = false)
public class EncryptConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (attribute == null) return null;
        return CryptoUtil.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return CryptoUtil.decrypt(dbData);
    }
}