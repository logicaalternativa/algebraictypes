package com.logicaalternativa.algebraictypes.examples;

import static com.logicaalternativa.algebraictypes.dsl.AlgebraSyntax.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import com.logicaalternativa.algebraictypes.dsl.AlgebraDsl;
import com.logicaalternativa.algebraictypes.dsl.interpreter.InterpreterDebug;
import com.logicaalternativa.algebraictypes.sideeffects.Option;
import com.logicaalternativa.algebraictypes.sideeffects.Options;
import java.util.Optional;


public class ExampleOptionTest {

    final Logger log = LoggerFactory.getLogger(getClass());

    @Test
    @DisplayName("Test with empty Option")
    void test01() {

        final Option<AlgebraDsl> myEmpty = Options.empty();

        log.info( () -> """
            
            ==========================
            Empty: %s
            ==========================
            """.formatted(myEmpty) 
        ) ;

        final boolean check = switch( myEmpty) {
            case Option.Some s -> false;
            case Option.None n -> true;
        };

        assertTrue( check, "Check Pattern Matching" );

        final var checkMap = Options.wrap( myEmpty )
            .map(InterpreterDebug::interpreter)
            .orElseGet( () -> "EMPTY" );
            
        assertEquals( "EMPTY", checkMap);

    }

    @Test
    @DisplayName("Test with value")
    void test02() {

        final Option<AlgebraDsl> myOptValue = Options.ofNullable(
            valueOf( 1 ).plus(valueOf(5))
            .value()
        );

        log.info( () -> """
            
            ==========================
            myOptValue: %s
            ==========================
            """.formatted(myOptValue) 
        ) ;        

        final boolean check = switch( myOptValue) {
            case Option.Some( 
                    AlgebraDsl.Addition( 
                        AlgebraDsl.Number( var one ),  
                        AlgebraDsl.Number( var two ))
                     ) when one ==1 && two == 5  -> true;
            case Option.Some s -> false;
            case Option.None n -> false;
        };

        assertTrue( check, "Check Pattern Matching" );

        final var checkMap = Options.wrap( myOptValue )
            .map(InterpreterDebug::interpreter)
            .orElseGet( () -> "EMPTY" );
            
        assertEquals( "(1 + 5)", checkMap);

    }
    
}
