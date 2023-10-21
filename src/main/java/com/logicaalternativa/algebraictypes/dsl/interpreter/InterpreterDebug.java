package com.logicaalternativa.algebraictypes.dsl.interpreter;

import com.logicaalternativa.algebraictypes.dsl.AlgebraDsl;

public class InterpreterDebug {
    
    private InterpreterDebug(){}
    
    public static String interpreter( AlgebraDsl a ) {
        
        return switch (a) {
            
            case AlgebraDsl.Number(var value) -> 
                "%d".formatted( value ) ;
            
            case AlgebraDsl.Addition( var one, var other )  ->
                "(%s + %s)".formatted( interpreter(one ), interpreter(other) );
            
            case AlgebraDsl.Subtraction( var one, var other ) ->
                "(%s - %s)".formatted( interpreter( one ), interpreter( other ) );
            
            case AlgebraDsl.Multiplication( var one, var other ) -> 
                "%s * %s".formatted( interpreter( one ), interpreter( other ) );
            
            case AlgebraDsl.Negative( var other ) ->
                "(-1) *(%s)".formatted(interpreter( other ));
        };
    }
    
}
