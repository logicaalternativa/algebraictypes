package com.logicaalternativa.algebraictypes.dsl;


import java.io.Serializable;

public sealed interface AlgebraDsl extends Serializable {
    
    record Number( int value ) implements AlgebraDsl{} 
    
    record Addition( AlgebraDsl sumand1, AlgebraDsl summand2 ) implements AlgebraDsl{}
    
    record Negative( AlgebraDsl alg ) implements AlgebraDsl{} 
    
    record Subtraction( AlgebraDsl minuend, AlgebraDsl subtrahend ) implements AlgebraDsl{} 
    
    record Multiplication( AlgebraDsl one, AlgebraDsl other ) implements AlgebraDsl{} 

}
