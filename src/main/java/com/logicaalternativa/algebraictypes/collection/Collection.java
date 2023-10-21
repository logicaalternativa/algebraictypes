package com.logicaalternativa.algebraictypes.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public sealed interface Collection<A> {

    record Cons<A>(A head, Collection<A> tail) implements Collection<A>{}
    record Nil<A>() implements Collection<A> {}

    static <A> Collection<A> empty() { 
        return new Nil<>();
    }; 

    default Collection<A> addFirst( final A a ) {
       return new Cons<>(a, this);
    }

    default Collection<A> addLast( final A a ) {
       return switch ( this ) {
            case Nil<A> l -> new Cons<>(a, l);
            case Cons<A>( var head, var tail ) -> new Cons<>( head, tail.addLast( a ) );
       };
    }

    default Optional<A> get( int index ) {
       return switch ( this ) {
            case Collection<A> l when index < 0 -> Optional.empty();
            case Nil<A> l -> Optional.empty();
            case Cons<A>(var head, var tail) when index == 0 -> Optional.of( head );
            case Cons<A>(var head, var tail) -> tail.get( index - 1 );
       };
    }

    default int length() {
        return switch ( this ) {
            case Nil<A> l -> 0;
            case Cons<A>( var h, var tail ) -> 1 + tail.length();
       };
    }

     default Optional<A> getFirst() {
         return get( 0 );
     }

     default Optional<A> getLast() {
         return get( length() -1 );
     }

    default Collection<A> filter( final Predicate<A> f ) {
       return switch ( this ) {
            case Nil<A> l -> l;
            case Cons<A>( var head, var tail ) when f.test( head ) -> new Cons<>( head, tail.filter(f) );
            case Cons<A>( var h, var tail ) -> tail.filter(f);
       };
    }

    default <B> Collection<B> map(final  Function<? super A, ? extends B>  f) {
       return switch ( this ) {
            case Nil<A> l -> Collection.<B>empty();
            case Cons<A>( var head, var tail ) -> new Cons<B>( f.apply(head), tail.<B>map(f) );
       };
    }

    default <B> B foldLeft(B identity, BiFunction<B,A,B> acc) {
       return switch ( this ) {
            case Nil<A> l -> identity;
            case Cons<A>( var head, var tail ) -> tail.foldLeft( acc.apply(identity, head ), acc );
       };
    }

    default A reduce(A identity, BinaryOperator<A> bf) {
       return foldLeft( identity, bf );
    }
    
    default Collection<A> reversed() {
       return this.foldLeft( empty(), (acc, reg) -> acc.addFirst( reg ) )            ;
    }

    default List<A> toList() {
        return this.foldLeft( 
                    new ArrayList<A>(), 
                    (acc, reg ) -> { 
                        acc.add(reg); 
                        return acc;
                    } 
                );
    }
}
