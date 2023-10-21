package com.logicaalternativa.algebraictypes.dsl.interpreter;

import com.logicaalternativa.algebraictypes.dsl.AlgebraDsl;

public class InterpreterInt {
    
    private InterpreterInt(){}
    
    public static int interpreter( AlgebraDsl a ) {
        
        return switch (a) {
            
            case AlgebraDsl.Number(var value) -> 
                value;
            
            case AlgebraDsl.Addition( var one, var other )  ->
                interpreter( one ) + interpreter(other );
            
            case AlgebraDsl.Subtraction( var one, var other ) ->
                interpreter( one ) - interpreter( other );
            
            case AlgebraDsl.Multiplication( var one, var other ) -> 
                interpreter( one ) * interpreter( other );
            
            case AlgebraDsl.Negative( var other ) ->
                -1 * interpreter( other );
        };
    }
    
}
