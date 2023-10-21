package com.logicaalternativa.algebraictypes.dsl.interpreter;

import com.logicaalternativa.algebraictypes.dsl.AlgebraDsl;

public class AlgebraSerializer {
    
    private static String TEMPLATE_NUMBER = """
        { 
        ~  "class": "%s", 
        ~  "value": %d 
        ~}""";
        
    private static String TEMPLATE_OPERATION = """
        {
        ~  "class": "%s", 
        ~  "one": %s, 
        ~  "other": %s 
        ~}""";
    
    private static String TEMPLATE_NEGATIVE = """
        { 
        ~  "class": "%s", 
        ~  "alg": %s 
        ~}""";
        
    private static final String DEFAULT_INDENT= "  ";
    
    private static String addDefautlIdent( String  ident) {
        return ident + DEFAULT_INDENT;
    }
    
    private static String interpreterJson( AlgebraDsl a, String indent ) {
        
        return switch (a) {
            
            case AlgebraDsl.Number(var value) -> 
                TEMPLATE_NUMBER.formatted( 
                    AlgebraDsl.Number.class.getSimpleName(), 
                    value 
                ).replaceAll( "~", indent );
                
            case AlgebraDsl.Addition( var one, var other ) -> 
                TEMPLATE_OPERATION.formatted( 
                    AlgebraDsl.Addition.class.getSimpleName(), 
                    interpreterJson( one, addDefautlIdent( indent ) ), 
                    interpreterJson( other, addDefautlIdent( indent ) )
                ).replaceAll( "~", indent );
                
            case AlgebraDsl.Subtraction( var one, var other )  -> 
                TEMPLATE_OPERATION.formatted(
                    AlgebraDsl.Subtraction.class.getSimpleName(), 
                    interpreterJson( one, addDefautlIdent( indent ) ), 
                    interpreterJson( other, addDefautlIdent( indent ) )
                ).replaceAll( "~", indent );
                
            case AlgebraDsl.Multiplication( var one, var other ) -> 
                TEMPLATE_OPERATION.formatted(
                    AlgebraDsl.Multiplication.class.getSimpleName(), 
                    interpreterJson( one, addDefautlIdent( indent ) ), 
                    interpreterJson( other, addDefautlIdent( indent ) ) 
                ).replaceAll( "~", indent );
                
            case AlgebraDsl.Negative( var other ) ->
                TEMPLATE_NEGATIVE.formatted( 
                    AlgebraDsl.Negative.class.getSimpleName(), 
                    interpreterJson( other, addDefautlIdent( indent ) )
                ).replaceAll( "~", indent );
         };
        
    }   
        
        
    public static String interpreterJson( AlgebraDsl a ) {
         return interpreterJson( a, "" );
    }
}
