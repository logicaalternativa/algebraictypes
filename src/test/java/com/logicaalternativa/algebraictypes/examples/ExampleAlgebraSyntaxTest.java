package com.logicaalternativa.algebraictypes.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import com.logicaalternativa.algebraictypes.examples.mother.AlgebraExample;

import static com.logicaalternativa.algebraictypes.dsl.AlgebraSyntax.*;

public class ExampleAlgebraSyntaxTest {

    final Logger log = LoggerFactory.getLogger(getClass());

    @Test
    @DisplayName("Check fluent interface")
    void test() {

        final var programFluent = 
                (
                    valueOf(5).plus(valueOf(10))
                ).times(
                    valueOf(8).minus(valueOf(5))
                ).negative()
                .value();

        log.info( () -> """
            
            ==========================
            Raw: %s
            --------------------------
            Using syntax: %s
            ==========================
            """.formatted(AlgebraExample.EXAMPLE_PROGRAM, programFluent) 
        ) ;

        assertEquals(AlgebraExample.EXAMPLE_PROGRAM, programFluent);

    }
}
