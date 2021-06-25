/*
 *  Copyright (c) 2020. Zyonic Software - Spark61 | hallo1142 | UltimatumGamer
 *  This File, its contents and by extension the corresponding project is property of Spark61 and Zyonic Software and may not be used without explicit permission to do so.
 *
 *  spark61@sv-studios.net
 *  management@sv-studios.net
 *  info@zyonicsoftware.com
 */

package de.spark61.config;

import com.google.gson.*;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

public class Document {

    public final static Gson GSON = new GsonBuilder().serializeNulls().setPrettyPrinting().disableHtmlEscaping().create();
    private JsonObject jsonObject;

    public Document(@NotNull final JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public Document(@NotNull final String json) {
        this.jsonObject = JsonParser.parseString(json).getAsJsonObject();
    }

    public Document() {
        this.jsonObject = new JsonObject();
    }

    public Document(@NotNull final String key, final Object value) {
        this.jsonObject = new JsonObject();
        this.jsonObject.add(key, Document.GSON.toJsonTree(value));
    }
    public Document(@NotNull final InputStream inputStream) throws IOException {
        this(IOUtils.toString(inputStream, StandardCharsets.UTF_8));
    }

    public Document(@NotNull final JsonElement jsonElement) {
        this.jsonObject = jsonElement.getAsJsonObject();
    }

    public Document clear(){
        entrySet().forEach(stringJsonElementEntry -> remove(stringJsonElementEntry.getKey()));

        return this;
    }

    public <T> T getObject(@NotNull final String key, @NotNull final Class<T> classOfT) {
        if (!this.jsonObject.has(key) || this.jsonObject.get(key).isJsonNull()) {
            return null;
        }

        return Document.GSON.fromJson(this.jsonObject.get(key).getAsString(), classOfT);
    }

    public <T> T getObject(@NotNull final String key, @NotNull final Type type) {
        if (!this.jsonObject.has(key) || this.jsonObject.get(key).isJsonNull()) {
            return null;
        }

        return Document.GSON.fromJson(this.jsonObject.get(key).getAsString(), type);
    }

    public Boolean getBoolean(@NotNull final String key) {
        if (!this.jsonObject.has(key) || this.jsonObject.get(key).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(key).getAsBoolean();
    }

    public Double getDouble(@NotNull final String key) {
        if (!this.jsonObject.has(key) || this.jsonObject.get(key).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(key).getAsDouble();
    }

    public Float getFloat(@NotNull final String key) {
        if (!this.jsonObject.has(key) || this.jsonObject.get(key).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(key).getAsFloat();
    }

    public Integer getInt(@NotNull final String key) {
        if (!this.jsonObject.has(key) || this.jsonObject.get(key).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(key).getAsInt();
    }

    public Long getLong(@NotNull final String key) {
        if (!this.jsonObject.has(key) || this.jsonObject.get(key).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(key).getAsLong();
    }

    public Byte getByte(@NotNull final String key) {
        if (!this.jsonObject.has(key) || this.jsonObject.get(key).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(key).getAsByte();
    }

    public DocumentArray getDocumentArray(@NotNull final String key) {
        if (!this.jsonObject.has(key) || this.jsonObject.get(key).isJsonNull()) {
            return null;
        }
        return new DocumentArray(this.jsonObject.get(key).getAsJsonArray());
    }

    public DocumentArray getDocumentArray(@NotNull final String key, final DocumentArray defaultValue) {
        final DocumentArray documentArray = this.getDocumentArray(key);

        return documentArray == null ? defaultValue : documentArray;
    }

    public JsonArray getJsonArray(@NotNull final String key) {
        if (!this.jsonObject.has(key) || this.jsonObject.get(key).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(key).getAsJsonArray();
    }

    public JsonNull getJsonNull(@NotNull final String key) {
        if (!this.jsonObject.has(key)) {
            return null;
        }
        return this.jsonObject.get(key).getAsJsonNull();
    }

    public JsonPrimitive getJsonPrimitive(@NotNull final String key) {
        if (!this.jsonObject.has(key) || this.jsonObject.get(key).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(key).getAsJsonPrimitive();
    }

    public String getString(@NotNull final String key) {
        if (!this.jsonObject.has(key) || this.jsonObject.get(key).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(key).getAsString();
    }

    public Document getDocument(@NotNull final String key) {
        if (!this.jsonObject.has(key) || this.jsonObject.get(key).isJsonNull()) {
            return null;
        }
        return new Document(this.jsonObject.get(key).getAsJsonObject());
    }

    public Boolean getBoolean(@NotNull final String key, final Boolean defaultValue) {
        final Boolean value = this.getBoolean(key);
        return value == null ? defaultValue : value;
    }

    public Double getDouble(@NotNull final String key, final Double defaultValue) {
        final Double value = this.getDouble(key);
        return value == null ? defaultValue : value;
    }

    public Float getFloat(@NotNull final String key, final Float defaultValue) {
        final Float value = this.getFloat(key);
        return value == null ? defaultValue : value;
    }

    public Integer getInt(@NotNull final String key, final Integer defaultValue) {
        final Integer value = this.getInt(key);
        return value == null ? defaultValue : value;
    }

    public Long getLong(@NotNull final String key, final Long defaultValue) {
        final Long value = this.getLong(key);
        return value == null ? defaultValue : value;
    }

    public Byte getByte(@NotNull final String key, final Byte defaultValue) {
        final Byte value = this.getByte(key);
        return value == null ? defaultValue : value;
    }

    public JsonArray getJsonArray(@NotNull final String key, final JsonArray defaultValue) {
        final JsonArray value = this.getJsonArray(key);
        return value == null ? defaultValue : value;
    }

    public JsonNull getJsonNull(@NotNull final String key, final JsonNull defaultValue) {
        final JsonNull value = this.getJsonNull(key);
        return value == null ? defaultValue : value;
    }

    public JsonPrimitive getJsonPrimitive(@NotNull final String key, final JsonPrimitive defaultValue) {
        final JsonPrimitive value = this.getJsonPrimitive(key);
        return value == null ? defaultValue : value;
    }

    public String getString(@NotNull final String key, final String defaultValue) {
        final String value = this.getString(key);
        return value == null ? defaultValue : value;
    }

    public Document getDocument(@NotNull final String key, final Document defaultValue) {
        final Document value = this.getDocument(key);
        return value == null ? defaultValue : value;
    }

    public Document remove(@NotNull final String key) {
        this.jsonObject.remove(key);
        return this;
    }

    public Document add(@NotNull final String key, final DocumentArray value) {
        return this.add(key, value == null ? null : value.getJsonArray());
    }

    public Document add(@NotNull final String key, final JsonArray value) {
        if (this.has(key)) {
            return this;
        }
        this.jsonObject.add(key, value);
        return this;
    }

    public Document add(@NotNull final String key, final Object value) {
        if (this.has(key)) {
            return this;
        }
        this.jsonObject.add(key, Document.GSON.toJsonTree(value));
        return this;
    }

    public Document add(@NotNull final String key, final String value) {
        if (this.has(key)) {
            return this;
        }
        this.jsonObject.addProperty(key, value);
        return this;
    }

    public Document add(@NotNull final String key, final JsonObject value) {
        if (this.has(key)) {
            return this;
        }

        this.jsonObject.add(key, value);
        return this;
    }

    public Document add(@NotNull final String key, final Number value) {
        if (this.has(key)) {
            return this;
        }
        this.jsonObject.addProperty(key, value);
        return this;
    }

    public Document add(@NotNull final String key, final Document value) {
        if (this.has(key)) {
            return this;
        }
        this.jsonObject.add(key, value.getJsonObject());
        return this;
    }

    public Document add(@NotNull final String key, final Boolean value) {
        if (this.has(key)) {
            return this;
        }
        this.jsonObject.addProperty(key, value);
        return this;
    }

    public Document add(@NotNull final String key, final JsonElement value) {
        if (this.has(key)) {
            return this;
        }
        this.jsonObject.add(key, value);
        return this;
    }

    public Document set(@NotNull final String key, final DocumentArray value) {
        return this.set(key, value == null ? null : value.getJsonArray());
    }

    public Document set(@NotNull final String key, final JsonElement value) {
        this.jsonObject.add(key, value);
        return this;
    }

    public Document set(@NotNull final String key, final Object value) {
        this.jsonObject.addProperty(key, Document.GSON.toJson(value));
        return this;
    }

    public Document set(@NotNull final String key, final JsonArray value) {
        this.jsonObject.add(key, value);
        return this;
    }

    public Document set(@NotNull final String key, final String value) {
        this.jsonObject.addProperty(key, value);
        return this;
    }

    public Document set(@NotNull final String key, final JsonObject value) {
        this.jsonObject.add(key, value);
        return this;
    }

    public Document set(@NotNull final String key, final Number value) {
        this.jsonObject.addProperty(key, value);
        return this;
    }

    public Document set(@NotNull final String key, final Document value) {
        this.jsonObject.add(key, value.getJsonObject());
        return this;
    }

    public Document set(@NotNull final String key, final Boolean value) {
        this.jsonObject.addProperty(key, value);
        return this;
    }

    @Override
    public String toString() {
        return this.jsonObject.toString();
    }

    public String toFormattedString() {
        return Document.GSON.toJson(this.jsonObject);
    }

    public Set<Map.Entry<String, JsonElement>> entrySet() {
        return this.jsonObject.entrySet();
    }

    public Set<String> keySet() {
        return this.jsonObject.keySet();
    }

    public boolean has(@NotNull final String key) {
        return this.jsonObject.has(key);
    }

    public boolean contains(@NotNull final String key) {
        return this.has(key);
    }

    public JsonObject getJsonObject() {
        return this.jsonObject;
    }

    protected void setJsonObject(final JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
