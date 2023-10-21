package com.logicaalternativa.algebraictypes.sideeffects;

public sealed interface Try<A> {

    record Success<A>(A value) implements Try<A>{}

    record Failure <A>( Throwable error ) implements Try<A> {}

}
