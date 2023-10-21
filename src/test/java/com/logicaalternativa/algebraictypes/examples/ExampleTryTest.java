package com.logicaalternativa.algebraictypes.examples;

import static com.logicaalternativa.algebraictypes.dsl.AlgebraSyntax.valueOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import com.logicaalternativa.algebraictypes.dsl.AlgebraDsl;
import com.logicaalternativa.algebraictypes.sideeffects.Try;
import com.logicaalternativa.algebraictypes.sideeffects.Trys;


public class ExampleTryTest {

    final Logger log = LoggerFactory.getLogger(getClass());

    @Test
    @DisplayName("Test try OK")
    void test01() {

        final var tryOk = Trys.of( () -> 10 /5 );

        log.info( () -> """
            
            ==========================
            tryOk: %s
            ==========================
            """.formatted(tryOk) 
        ) ;

        final var check = switch( tryOk ) {
            case Try.Success( var value ) when value == 2 -> true;
            case Try.Success s  -> false;
            case Try.Failure e ->  false;
        };
        assertTrue(check, "Value should be 2");

        final var okMapped = Trys.wrap( tryOk )
            .map( s -> s * 2 )
            .get();

        final var checkMapped = switch( okMapped ) {
            case Try.Success( var value ) when value == 4 -> true;
            case Try.Success s  -> false;
            case Try.Failure e ->  false;
        };

        assertTrue(checkMapped, "Value should be 4");

    }

    @Test
    @DisplayName("Test with error")
    void test02() {

        final var tryFailed = Trys.of( () -> 1 /0 );

        log.info( () -> """
            
            ==========================
            tryFailed: %s
            ==========================
            """.formatted(tryFailed) 
        ) ;

        final var check = switch( tryFailed ) {
            case Try.Success s  -> false;
            case Try.Failure( ArithmeticException e ) -> true;
            case Try.Failure e ->  false;
        };

        assertTrue(check, "Error with ArithmeticException");

        final var failedMapped = Trys.wrap( tryFailed )
            .map( s -> s * 2 )
            .get();

        final var checkMapped = switch( failedMapped ) {
            case Try.Success s  -> false;
            case Try.Failure( ArithmeticException e ) ->  true;
            case Try.Failure e ->  false;
        };

        assertTrue(checkMapped, "Error with ArithmeticException");

    }
    
}
