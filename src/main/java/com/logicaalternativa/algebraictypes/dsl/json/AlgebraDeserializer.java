package com.logicaalternativa.algebraictypes.dsl.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.logicaalternativa.algebraictypes.dsl.AlgebraDsl;

public class AlgebraDeserializer implements JsonDeserializer<AlgebraDsl> {
    @Override
    public AlgebraDsl deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

        final var jsonObject = json.getAsJsonObject();

        final var class_ = jsonObject.get("class").getAsString();

        return switch (class_) {

            case "Number" ->
                new AlgebraDsl.Number(
                        jsonObject.get("value").getAsInt());

            case "Multiplication" ->
                new AlgebraDsl.Multiplication(
                        context.deserialize(jsonObject.get("one"), AlgebraDsl.class),
                        context.deserialize(jsonObject.get("other"), AlgebraDsl.class));

            case "Addition" ->
                new AlgebraDsl.Addition(
                        context.deserialize(jsonObject.get("one"), AlgebraDsl.class),
                        context.deserialize(jsonObject.get("other"), AlgebraDsl.class));

            case "Subtraction" ->
                new AlgebraDsl.Subtraction(
                        context.deserialize(jsonObject.get("one"), AlgebraDsl.class),
                        context.deserialize(jsonObject.get("other"), AlgebraDsl.class));

            case "Negative" ->
                new AlgebraDsl.Negative(
                        context.deserialize(jsonObject.get("alg"), AlgebraDsl.class));

            default ->
                throw new IllegalArgumentException();

        };

    }
}
