/*
 *  Copyright (c) 2020. Zyonic Software - Spark61 | hallo1142 | UltimatumGamer
 *  This File, its contents and by extension the corresponding project is property of Spark61 and Zyonic Software and may not be used without explicit permission to do so.
 *
 *  spark61@sv-studios.net
 *  management@sv-studios.net
 *  info@zyonicsoftware.com
 */

package de.spark61.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Config extends Document {

    private final String path;
    private final boolean readOnly;

    public Config(@NotNull final String path) {
        this(path, false);
    }

    public Config(@NotNull final String path, @NotNull final Boolean readOnly) {
        this.path = path;
        this.readOnly = readOnly;

        this.reload();
    }

    public Config(@NotNull final InputStream inputStream) throws IOException {
        super(IOUtils.toString(inputStream, StandardCharsets.UTF_8));
        this.readOnly = true;
        this.path = null;
    }

    private Config(@NotNull final Document document) {
        super(document.toString());
        this.readOnly = true;
        this.path = null;
    }

    public boolean isReadOnly() {
        return this.readOnly;
    }

    public void save() {
        if (this.isReadOnly()) {
            try {
                throw new IOException("Can't write in file");
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        
        try {
            final Writer writer = new FileWriter(this.path);

            Document.GSON.toJson(this.getDocument().getJsonObject(), writer);

            writer.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        try {
            final JsonElement jsonElement = JsonParser.parseReader(new FileReader(this.path));

            super.setJsonObject(jsonElement.getAsJsonObject());
        } catch (final IOException e) {
            final File file = new File(this.path);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            super.setJsonObject(new JsonObject());
            this.save();
        }
    }

    @Override
    public Config set(@NotNull final String key, final Number value) {
        super.set(key, value);
        return this;
    }

    @Override
    public Config set(@NotNull final String key, final Object value) {
        super.set(key, value);
        return this;
    }

    @Override
    public Config set(@NotNull final String key, final String value) {
        super.set(key, value);
        return this;
    }

    @Override
    public Config set(@NotNull final String key, final Boolean value) {
        super.set(key, value);
        return this;
    }

    @Override
    public Config set(@NotNull final String key, final Document value) {
        super.set(key, value);
        return this;
    }

    @Override
    public Config set(@NotNull final String key, final JsonArray value) {
        super.set(key, value);
        return this;
    }

    @Override
    public Config set(@NotNull final String key, final JsonObject value) {
        super.set(key, value);
        return this;
    }

    @Override
    public Config set(@NotNull final String key, final JsonElement value) {
        super.set(key, value);
        return this;
    }

    @Override
    public Document set(@NotNull final String key, final DocumentArray value) {
        super.set(key, value);
        return this;
    }

    @Override
    public Config add(@NotNull final String key, final Number value) {
        super.add(key, value);
        return this;
    }

    @Override
    public Config add(@NotNull final String key, final Object value) {
        super.add(key, value);
        return this;
    }

    @Override
    public Config add(@NotNull final String key, final String value) {
        super.add(key, value);
        return this;
    }

    @Override
    public Config add(@NotNull final String key, final Boolean value) {
        super.add(key, value);
        return this;
    }

    @Override
    public Config add(@NotNull final String key, final Document value) {
        super.add(key, value);
        return this;
    }

    @Override
    public Config add(@NotNull final String key, final JsonElement value) {
        super.add(key, value);
        return this;
    }

    @Override
    public Config add(@NotNull final String key, final JsonArray value) {
        super.add(key, value);
        return this;
    }

    @Override
    public Config add(@NotNull final String key, final JsonObject value) {
        super.add(key, value);
        return this;
    }

    @Override
    public Document add(@NotNull final String key, final DocumentArray value) {
        super.add(key, value);
        return this;
    }

    @Override
    public String toString() {
        return "Config{" +
                "document=" + this.getJsonObject() +
                ", path='" + this.path + '\'' +
                '}';
    }

    public String getPath() {
        return this.path;
    }

    public Document getDocument() {
        return this;
    }
}