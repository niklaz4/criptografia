package com.example.encryption.converter;

import com.example.encryption.EncryptionService;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AttributeEncryptor implements AttributeConverter<String, String> {
    private final EncryptionService encryptionService;

    public AttributeEncryptor() {
        this.encryptionService = new EncryptionService();
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return attribute != null ? encryptionService.encrypt(attribute) : null;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData != null ? encryptionService.decrypt(dbData) : null;
    }
}