package com.logicaalternativa.algebraictypes.dsl;

public class AlgebraSyntax {
    
    public static AlgebraWrapper valueOf( Integer val ) {
            return new AlgebraWrapper( new AlgebraDsl.Number( val ) ); 
    }
    
    public static class AlgebraWrapper {
        private final AlgebraDsl algebra;
        
        private AlgebraWrapper( final AlgebraDsl algebra ){
            this.algebra = algebra;
        }
            
        public AlgebraWrapper plus( final AlgebraWrapper other ) {
            return new AlgebraWrapper( new AlgebraDsl.Addition( algebra, other.value() ) );
        }

        public AlgebraWrapper minus( final AlgebraWrapper other ) {
            return new AlgebraWrapper( new AlgebraDsl.Subtraction( algebra, other.value() ) );
        }

        public AlgebraWrapper times( final AlgebraWrapper other ) {
            return new AlgebraWrapper( new AlgebraDsl.Multiplication( algebra, other.value() ) );
        }

        public AlgebraWrapper negative() {
            return new AlgebraWrapper( new AlgebraDsl.Negative( algebra ) );
        }

        public AlgebraDsl value() {
            return algebra;
        }
        
    }
}
