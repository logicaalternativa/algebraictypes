package com.logicaalternativa.algebraictypes.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import com.logicaalternativa.algebraictypes.dsl.interpreter.InterpreterInt;
import com.logicaalternativa.algebraictypes.examples.mother.AlgebraExample;

public class ExampleInterpreterIntTest {

    final Logger log = LoggerFactory.getLogger(getClass());

    @Test
    @DisplayName("Example of int interpreter")
    void test() {

        final var res = InterpreterInt.interpreter( AlgebraExample.EXAMPLE_PROGRAM );

        log.info( () -> """
            
            ==========================
            Program: %s
            --------------------------
            Res: %s
            ==========================
            """.formatted(AlgebraExample.EXAMPLE_PROGRAM, res) 
        ) ;

        assertEquals(-45, res);

    }
    
}
