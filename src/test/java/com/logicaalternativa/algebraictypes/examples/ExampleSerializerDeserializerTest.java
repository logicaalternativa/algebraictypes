package com.logicaalternativa.algebraictypes.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.logicaalternativa.algebraictypes.dsl.AlgebraDsl;
import com.logicaalternativa.algebraictypes.dsl.interpreter.AlgebraSerializer;
import com.logicaalternativa.algebraictypes.dsl.interpreter.InterpreterInt;
import com.logicaalternativa.algebraictypes.dsl.json.AlgebraDeserializer;
import com.logicaalternativa.algebraictypes.examples.mother.AlgebraExample;

public class ExampleSerializerDeserializerTest {

    private final Gson gson = initGson();

    @Test
    @DisplayName("Example of serializer/deserializer data")
    void test() {

        final Queue<String> messageBroker = new ConcurrentLinkedQueue<>();

        simulatePublisher(messageBroker);

        final var res = simulateSubscriber(messageBroker);

        assertEquals(-45, res);

    }

    private void simulatePublisher(Queue<String> messageBroker) {

        final var myProgramSerialized = AlgebraSerializer.interpreterJson(
                AlgebraExample.EXAMPLE_PROGRAM);

        messageBroker.offer(myProgramSerialized);

    }

    private int simulateSubscriber(Queue<String> messageBroker) {

        final var message = messageBroker.poll();

        final var program = gson.fromJson(
                message,
                AlgebraDsl.class);

        return InterpreterInt.interpreter(program);

    }

    private Gson initGson() {

        final var gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(AlgebraDsl.class, new AlgebraDeserializer());

        return gsonBuilder.create();

    }

}
