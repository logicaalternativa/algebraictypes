package com.logicaalternativa.algebraictypes.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import com.logicaalternativa.algebraictypes.dsl.interpreter.InterpreterDebug;
import com.logicaalternativa.algebraictypes.examples.mother.AlgebraExample;

public class ExampleInterpreterDebugTest {

    final Logger log = LoggerFactory.getLogger(getClass());

    @Test
    @DisplayName("Example of debug interpreter")
    void test() {

        final var res = InterpreterDebug.interpreter( AlgebraExample.EXAMPLE_PROGRAM );

        log.info( () -> """
            
            ==========================
            Program: %s
            --------------------------
            Res: %s
            ==========================
            """.formatted(AlgebraExample.EXAMPLE_PROGRAM, res) 
        ) ;

        assertEquals("(-1) *((5 + 10) * (8 - 5))", res);

    }
    
}
