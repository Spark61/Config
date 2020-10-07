/*
 *  Copyright (c) 2020. Zyonic Software - Spark61 | hallo1142 | UltimatumGamer
 *  This File, its contents and by extension the corresponding project is property of Spark61 and Zyonic Software and may not be used without explicit permission to do so.
 *
 *  spark61@sv-studios.net
 *  management@sv-studios.net
 *  info@zyonicsoftware.com
 */

package de.spark61.config;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class DocumentArray implements Iterable<JsonElement> {

    private final JsonArray jsonArray;

    public DocumentArray(@NotNull final JsonArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public DocumentArray(@NotNull final List<Object> list) {
        this.jsonArray = new JsonArray();

        list.forEach(o -> this.add(Document.GSON.toJson(o)));
    }

    public DocumentArray(@NotNull final String jsonArrayString) {
        this.jsonArray = JsonParser.parseString(jsonArrayString).getAsJsonArray();
    }

    public DocumentArray() {
        this.jsonArray = new JsonArray();
    }

    public JsonElement get(final int i) {
        return this.jsonArray.get(i);
    }

    public DocumentArray add(@NotNull final Boolean value) {
        this.jsonArray.add(value);
        return this;
    }

    public DocumentArray add(@NotNull final String value) {
        this.jsonArray.add(value);
        return this;
    }

    public DocumentArray add(@NotNull final Number value) {
        this.jsonArray.add(value);
        return this;
    }

    public DocumentArray add(@NotNull final Document value) {
        this.jsonArray.add(value.getJsonObject());
        return this;
    }

    public DocumentArray add(@NotNull final JsonElement value) {
        this.jsonArray.add(value);
        return this;
    }

    public DocumentArray add(@NotNull final Character value) {
        this.jsonArray.add(value);
        return this;
    }

    public DocumentArray addAll(@NotNull final List<Object> value) {
        value.forEach(o -> this.add(Document.GSON.toJson(o)));
        return this;
    }

    public DocumentArray addAll(@NotNull final JsonArray value) {
        this.jsonArray.addAll(value);
        return this;
    }

    public boolean contains(@NotNull final JsonElement value) {
        return this.jsonArray.contains(value);
    }

    public boolean has(@NotNull final JsonElement value) {
        return this.jsonArray.contains(value);
    }

    public DocumentArray remove(@NotNull final Integer index) {
        this.jsonArray.remove(index);

        return this;
    }

    public DocumentArray remove(@NotNull final JsonElement value) {
        this.jsonArray.remove(value);

        return this;
    }

    public DocumentArray remove(@NotNull final Boolean value) {
        this.jsonArray.remove(Document.GSON.toJsonTree(value));
        return this;
    }

    public DocumentArray remove(@NotNull final String value) {
        this.jsonArray.remove(Document.GSON.toJsonTree(value));
        return this;
    }

    public DocumentArray remove(@NotNull final Number value) {
        this.jsonArray.add(value);
        return this;
    }

    public DocumentArray remove(@NotNull final Character value) {
        this.jsonArray.remove(value);
        return this;
    }

    public int size() {
        return this.jsonArray.size();
    }

    public DocumentArray set(@NotNull final Integer index, @NotNull final JsonElement value) {
        this.jsonArray.set(index, value);

        return this;
    }

    public DocumentArray set(@NotNull final Integer index, @NotNull final Boolean value) {
        this.jsonArray.set(index, Document.GSON.toJsonTree(value));

        return this;
    }

    public DocumentArray set(@NotNull final Integer index, @NotNull final String value) {
        this.jsonArray.set(index, Document.GSON.toJsonTree(value));

        return this;
    }

    public DocumentArray set(@NotNull final Integer index, @NotNull final Number value) {
        this.jsonArray.set(index, Document.GSON.toJsonTree(value));

        return this;
    }

    public DocumentArray set(@NotNull final Integer index, @NotNull final Character value) {
        this.jsonArray.set(index, Document.GSON.toJsonTree(value));

        return this;
    }

    public int indexOf(@NotNull final JsonElement jsonElement) {
        final List<JsonElement> jsonElements = Lists.newArrayList(this.jsonArray.iterator());

        return jsonElements.indexOf(jsonElement);
    }

    public JsonArray getJsonArray() {
        return this.jsonArray;
    }

    public Stream<JsonElement> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    @NotNull
    @Override
    public Iterator<JsonElement> iterator() {
        return this.jsonArray.iterator();
    }

    @Override
    public void forEach(@NotNull final Consumer<? super JsonElement> action) {
        this.jsonArray.forEach(action);
    }

    @Override
    public Spliterator<JsonElement> spliterator() {
        return this.jsonArray.spliterator();
    }

    @Override
    public String toString() {
        return this.jsonArray.toString();
    }

    public String toFormattedString() {
        return Document.GSON.toJson(this.jsonArray);
    }
}
