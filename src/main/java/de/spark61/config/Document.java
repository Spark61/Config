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
        set(key, value);
    }

    public Document(@NotNull final String key, final Document value) {
        this.jsonObject = new JsonObject();
        set(key, value);
    }

    public Document(@NotNull final String key, final DocumentArray value) {
        this.jsonObject = new JsonObject();
        set(key, value);
    }

    public Document(@NotNull final InputStream inputStream) throws IOException {
        this(IOUtils.toString(inputStream, StandardCharsets.UTF_8));
    }

    public Document(@NotNull final JsonElement jsonElement) {
        this.jsonObject = jsonElement.getAsJsonObject();
    }

    public Document clear() {
        entrySet().forEach(stringJsonElementEntry -> remove(stringJsonElementEntry.getKey()));

        return this;
    }

    public <T> T getObject(@NotNull final String key, @NotNull final Class<T> classOfT) {
        Path pair = checkJsonObject(key);
        if (!pair.getJsonObject().has(pair.getPath()) || pair.getJsonObject().get(pair.getPath()).isJsonNull()) {
            return null;
        }

        return Document.GSON.fromJson(pair.getJsonObject().get(pair.getPath()), classOfT);
    }

    public <T> T getObject(@NotNull final String key, @NotNull final Type type) {
        Path pair = checkJsonObject(key);
        if (!pair.getJsonObject().has(pair.getPath()) || pair.getJsonObject().get(pair.getPath()).isJsonNull()) {
            return null;
        }

        return Document.GSON.fromJson(pair.getJsonObject().get(pair.getPath()), type);
    }

    public Boolean getBoolean(@NotNull final String key) {
        Path pair = checkJsonObject(key);
        if (!pair.getJsonObject().has(pair.getPath()) || pair.getJsonObject().get(pair.getPath()).isJsonNull()) {
            return null;
        }
        return pair.getJsonObject().get(pair.getPath()).getAsBoolean();
    }

    public Double getDouble(@NotNull final String key) {
        Path pair = checkJsonObject(key);
        if (!pair.getJsonObject().has(pair.getPath()) || pair.getJsonObject().get(pair.getPath()).isJsonNull()) {
            return null;
        }
        return pair.getJsonObject().get(pair.getPath()).getAsDouble();
    }

    public Float getFloat(@NotNull final String key) {
        Path pair = checkJsonObject(key);
        if (!pair.getJsonObject().has(pair.getPath()) || pair.getJsonObject().get(pair.getPath()).isJsonNull()) {
            return null;
        }
        return pair.getJsonObject().get(pair.getPath()).getAsFloat();
    }

    public Integer getInt(@NotNull final String key) {
        Path pair = checkJsonObject(key);
        if (!pair.getJsonObject().has(pair.getPath()) || pair.getJsonObject().get(pair.getPath()).isJsonNull()) {
            return null;
        }
        return pair.getJsonObject().get(pair.getPath()).getAsInt();
    }

    public Long getLong(@NotNull final String key) {
        Path pair = checkJsonObject(key);
        if (!pair.getJsonObject().has(pair.getPath()) || pair.getJsonObject().get(pair.getPath()).isJsonNull()) {
            return null;
        }
        return pair.getJsonObject().get(pair.getPath()).getAsLong();
    }

    public Byte getByte(@NotNull final String key) {
        Path pair = checkJsonObject(key);
        if (!pair.getJsonObject().has(pair.getPath()) || pair.getJsonObject().get(pair.getPath()).isJsonNull()) {
            return null;
        }
        return pair.getJsonObject().get(pair.getPath()).getAsByte();
    }

    public DocumentArray getDocumentArray(@NotNull final String key) {
        Path pair = checkJsonObject(key);
        if (!pair.getJsonObject().has(pair.getPath()) || pair.getJsonObject().get(pair.getPath()).isJsonNull()) {
            return null;
        }
        return new DocumentArray(pair.getJsonObject().get(pair.getPath()).getAsJsonArray());
    }

    public DocumentArray getDocumentArray(@NotNull final String key, final DocumentArray defaultValue) {
        final DocumentArray documentArray = this.getDocumentArray(key);

        return documentArray == null ? defaultValue : documentArray;
    }

    public JsonArray getJsonArray(@NotNull final String key) {
        Path pair = checkJsonObject(key);
        if (!pair.getJsonObject().has(pair.getPath()) || pair.getJsonObject().get(pair.getPath()).isJsonNull()) {
            return null;
        }
        return pair.getJsonObject().get(pair.getPath()).getAsJsonArray();
    }

    public JsonNull getJsonNull(@NotNull final String key) {
        Path pair = checkJsonObject(key);
        if (!pair.getJsonObject().has(pair.getPath())) {
            return null;
        }
        return pair.getJsonObject().get(pair.getPath()).getAsJsonNull();
    }

    public JsonPrimitive getJsonPrimitive(@NotNull final String key) {
        Path pair = checkJsonObject(key);
        if (!pair.getJsonObject().has(pair.getPath()) || pair.getJsonObject().get(pair.getPath()).isJsonNull()) {
            return null;
        }
        return pair.getJsonObject().get(pair.getPath()).getAsJsonPrimitive();
    }

    public String getString(@NotNull final String key) {
        Path pair = checkJsonObject(key);
        if (!pair.getJsonObject().has(pair.getPath()) || pair.getJsonObject().get(pair.getPath()).isJsonNull()) {
            return null;
        }
        return pair.getJsonObject().get(pair.getPath()).getAsString();
    }

    public Document getDocument(@NotNull final String key) {
        Path pair = checkJsonObject(key);
        if (!pair.getJsonObject().has(pair.getPath()) || pair.getJsonObject().get(pair.getPath()).isJsonNull()) {
            return null;
        }
        return new Document(pair.getJsonObject().get(pair.getPath()).getAsJsonObject());
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
        Path pair = checkJsonObject(key);
        pair.getJsonObject().remove(pair.getPath());
        return this;
    }

    public Document add(@NotNull final String key, final DocumentArray value) {
        return this.add(key, value == null ? null : value.getJsonArray());
    }

    public Document add(@NotNull final String key, final JsonArray value) {
        if (this.has(key)) {
            return this;
        }
        Path pair = checkJsonObject(key);
        pair.getJsonObject().add(pair.getPath(), value);
        return this;
    }

    public Document add(@NotNull final String key, final Object value) {
        if (this.has(key)) {
            return this;
        }
        Path pair = checkJsonObject(key);
        pair.getJsonObject().add(pair.getPath(), Document.GSON.toJsonTree(value));
        return this;
    }

    public Document add(@NotNull final String key, final String value) {
        if (this.has(key)) {
            return this;
        }
        Path pair = checkJsonObject(key);
        pair.getJsonObject().addProperty(pair.getPath(), value);
        return this;
    }

    public Document add(@NotNull final String key, final JsonObject value) {
        if (this.has(key)) {
            return this;
        }

        Path pair = checkJsonObject(key);
        pair.getJsonObject().add(pair.getPath(), value);
        return this;
    }

    public Document add(@NotNull final String key, final Number value) {
        if (this.has(key)) {
            return this;
        }
        Path pair = checkJsonObject(key);
        pair.getJsonObject().addProperty(pair.getPath(), value);
        return this;
    }

    public Document add(@NotNull final String key, final Document value) {
        if (this.has(key)) {
            return this;
        }
        Path pair = checkJsonObject(key);
        pair.getJsonObject().add(pair.getPath(), value.getJsonObject());
        return this;
    }

    public Document add(@NotNull final String key, final Boolean value) {
        if (this.has(key)) {
            return this;
        }
        Path pair = checkJsonObject(key);
        pair.getJsonObject().addProperty(pair.getPath(), value);
        return this;
    }

    public Document add(@NotNull final String key, final JsonElement value) {
        if (this.has(key)) {
            return this;
        }
        Path pair = checkJsonObject(key);
        pair.getJsonObject().add(pair.getPath(), value);
        return this;
    }

    public Document set(@NotNull final String key, final DocumentArray value) {
        return this.set(key, value == null ? null : value.getJsonArray());
    }

    public Document set(@NotNull final String key, final JsonElement value) {
        Path pair = checkJsonObject(key);
        pair.getJsonObject().add(pair.getPath(), value);
        return this;
    }

    public Document set(@NotNull final String key, final Object value) {
        Path pair = checkJsonObject(key);
        pair.getJsonObject().add(pair.getPath(), Document.GSON.toJsonTree(value));
        return this;
    }

    public Document set(@NotNull final String key, final JsonArray value) {
        Path pair = checkJsonObject(key);
        pair.getJsonObject().add(pair.getPath(), value);
        return this;
    }

    public Document set(@NotNull final String key, final String value) {
        Path pair = checkJsonObject(key);
        pair.getJsonObject().addProperty(pair.getPath(), value);
        return this;
    }

    public Document set(@NotNull final String key, final JsonObject value) {
        Path pair = checkJsonObject(key);
        pair.getJsonObject().add(pair.getPath(), value);
        return this;
    }

    public Document set(@NotNull final String key, final Number value) {
        Path pair = checkJsonObject(key);
        pair.getJsonObject().addProperty(pair.getPath(), value);
        return this;
    }

    public Document set(@NotNull final String key, final Document value) {
        Path pair = checkJsonObject(key);
        pair.getJsonObject().add(pair.getPath(), value.getJsonObject());
        return this;
    }

    public Document set(@NotNull final String key, final Boolean value) {
        Path pair = checkJsonObject(key);
        pair.getJsonObject().addProperty(pair.getPath(), value);
        return this;
    }

    private Path checkJsonObject(@NotNull String key) {
        JsonObject output = jsonObject;
        String outputPath = key;

        if (key.contains(".")) {
            key = key.substring(0, key.lastIndexOf("."));

            for (String s : key.split("\\.")) {
                JsonElement jsonElement = output.get(s);

                if (jsonElement == null || !jsonElement.isJsonObject()) {
                    break;
                }

                output = jsonElement.getAsJsonObject();
                outputPath = outputPath.substring(outputPath.indexOf(".") + 1);
            }
        }

        return new Path(output, outputPath);
    }

    @Override
    public String toString() {
        return jsonObject.toString();
    }

    public String toFormattedString() {
        return Document.GSON.toJson(this.jsonObject);
    }

    public Set<Map.Entry<String, JsonElement>> entrySet() {
        return jsonObject.entrySet();
    }

    public Set<String> keySet() {
        return jsonObject.keySet();
    }

    public boolean has(@NotNull final String key) {
        Path pair = checkJsonObject(key);
        return pair.getJsonObject().has(pair.getPath());
    }

    public boolean isEmpty(){
        return this.keySet().isEmpty();
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

    private static class Path {
        private final JsonObject jsonObject;
        private final String path;

        protected Path(JsonObject jsonObject, String path) {
            this.jsonObject = jsonObject;
            this.path = path;
        }

        public JsonObject getJsonObject() {
            return jsonObject;
        }

        public String getPath() {
            return path;
        }
    }
}
