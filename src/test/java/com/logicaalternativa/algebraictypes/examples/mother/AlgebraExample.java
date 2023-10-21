package com.logicaalternativa.algebraictypes.examples.mother;

import com.logicaalternativa.algebraictypes.dsl.AlgebraDsl;

public class AlgebraExample {

    public static final AlgebraDsl EXAMPLE_PROGRAM = new AlgebraDsl.Negative(
            new AlgebraDsl.Multiplication(
                new AlgebraDsl.Addition(
                    new AlgebraDsl.Number(5),
                    new AlgebraDsl.Number(10)
                ),
                new AlgebraDsl.Subtraction( 
                    new AlgebraDsl.Number(8),
                    new AlgebraDsl.Number(5)
                )
            )
    );

    private AlgebraExample() {
    }

}
