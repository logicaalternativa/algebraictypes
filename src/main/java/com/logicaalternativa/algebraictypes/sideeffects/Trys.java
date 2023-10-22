package com.logicaalternativa.algebraictypes.sideeffects;

import java.util.function.Function;
import java.util.function.Supplier;

public class Trys {
    
    private Trys() {}
    
    public static <T> Try<T> of( final Supplier<T> s ) {
        
        try {
            final var value = s.get();
            return new Try.Success<>( value ) ;
        } catch ( Throwable e) {
            return new Try.Failure<>( e );
        }
    }
    
    public static <T> Try<T> failed( final Throwable error ) {
        return new Try.Failure<>( error ) ;
    }  
    
    public static <T,U> Try<U> flatMap( 
            Try<T> from, 
            Function<? super T, ? extends Try<U>>  f ) {
        
        return switch( from ) {
            case Try.Success( var value ) -> executeF( f, value );
            case Try.Failure( var e ) -> failed( e ) ;
        };
    }
    
    public static <T> Try<T> recoverWith( 
            Try<T> from, 
            Function<? super Throwable, ? extends Try<T>>  f ) {
        
        return switch( from ) {
            case Try.Success<T> s -> s;
            case Try.Failure( var e ) -> executeF( f, e ) ;
        };
    }
    
    public static <T,U> Try<U> map( 
            Try<T> from, 
            Function<? super T, ? extends U>  f ) {
        
        return flatMap(from, s -> of( () -> f.apply(s) ) );
    }
    
    public static <T> Try<T> recover( 
            Try<T> from, 
            Function<? super Throwable, ? extends T>  f ) {
        return recoverWith( from, e -> of( () -> f.apply(e) ) );
    }
    
    private static <T,U> Try<U> executeF( 
            final Function<? super T, ? extends Try<U>>  f, 
            final T value) {
        try  {
            return f.apply( value);
        } catch (Throwable e) {
            return failed( e );            
        }
    }
    
    public static <T> TryWrap<T>  wrap(Try<T> tryValue) {
        return new TryWrap<>( tryValue );
    }  
    
    public static class TryWrap<T> {
        
        private final Try<T> tryValue;
        
        private TryWrap( final Try<T> tryValue ) {
            this.tryValue = tryValue;
        }
        
        public Try<T> get() {
            return tryValue;
        }
        
        public <U> TryWrap<U> flatMap( 
            Function<? super T, ? extends Try<U>>  f )  {
            return new TryWrap<>( Trys.flatMap( tryValue, f ) );
        }
        
        public <U> TryWrap<U> map( 
            Function<? super T, ? extends U>  f )  {
            return new TryWrap<>( Trys.map( tryValue, f ) );
        }
        
        public TryWrap<T> recoverWith( 
            Function<? super Throwable, ? extends Try<T>>  f ) {
            return new TryWrap<>( Trys.recoverWith( tryValue, f ) );
        }
    
        public TryWrap<T> recover( 
                Function<? super Throwable, ? extends T>  f ) {
            return new TryWrap<>( Trys.recover( tryValue, f ) );
        }
        
    }

}