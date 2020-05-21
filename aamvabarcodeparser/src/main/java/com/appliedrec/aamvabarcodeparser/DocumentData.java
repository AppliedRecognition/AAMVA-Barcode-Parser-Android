package com.appliedrec.aamvabarcodeparser;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Base abstract class that represents information found on ID documents
 */
public abstract class DocumentData {

    HashMap<String,DataField> fields = new HashMap<>();

    /**
     * Get parsed value for given key
     * @param key Key for which to get parsed value
     * @return Parsed value
     */
    @Nullable
    public String getValueForKey(String key) {
        DataField field = fields.get(key);
        if (field != null) {
            return field.getParsedValue();
        }
        return null;
    }

    /**
     * Get available keys
     * @return Set of keys
     */
    public Set<String> getKeys() {
        return fields.keySet();
    }

    void setValue(DataField value, String key) {
        fields.put(key, value);
    }

    void appendFieldsFrom(DocumentData other) {
        Iterator<Map.Entry<String,DataField>> iterator = other.fields.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String,DataField> entry = iterator.next();
            if (!fields.containsKey(entry.getKey())) {
                fields.put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * @return {@literal true} if the data is empty
     */
    public boolean isEmpty() {
        return fields.isEmpty();
    }

    /**
     * @return Document holder's first name
     */
    public abstract String getFirstName();

    /**
     * @return Document holder's last name
     */
    public abstract String getLastName();

    /**
     * @return Document holder's address
     */
    public abstract String getAddress();

    /**
     * @return Document holder's date of birth
     */
    public abstract String getDateOfBirth();

    /**
     * @return Date of expiry of the document
     */
    public abstract String getDateOfExpiry();

    /**
     * @return Date of issue of the document
     */
    public abstract String getDateOfIssue();

    /**
     * @return Document number
     */
    public abstract String getDocumentNumber();

    /**
     * @return Document holder's sex
     */
    public abstract String getSex();
}
