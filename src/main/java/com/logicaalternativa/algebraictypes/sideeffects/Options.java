package com.logicaalternativa.algebraictypes.sideeffects;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class Options {
    
    private Options() {}      

    public static <T extends Serializable> Option<T> empty() {
        return new Option.None<>();
    }

    public static <T extends Serializable> Option<T> ofNullable( final T value ) {
        
        if ( value != null ) {
            return new Option.Some<>(value);
        } else {            
            return empty();
        }
        
    }
    
    public static <T extends Serializable, U extends Serializable> Option<U> flatMap( 
            final Option<T> op,
            final Function<? super T, ? extends Option<U>>  f ) {
       
        return switch ( op ) {
            case Option.None<T> l -> empty();
            case Option.Some<T>( var value ) -> f.apply( value ) ;
        };
        
    }
    
    public static <T extends Serializable, U extends Serializable> Option<U> map( 
            final Option<T> op,
            final Function<? super T, ? extends U>  f ) {
        return flatMap(op, s -> ofNullable( f.apply(s) ) ) ;
       
    }
    
     public static <T extends Serializable> Optional<T> toOptional( final Option<T> op ) {
        return switch ( op ) {
            case Option.None<T> l -> Optional.empty();
            case Option.Some<T>( var value ) -> Optional.of( value );
        };
    }
    
    public static <T extends Serializable> T orElseGet(
            final Option<T> op,
            final Supplier<? extends T> supplier) {
       
        return switch ( op ) {
            case Option.Some<T>(var value) -> value;
            case Option.None<T> l ->  supplier.get();
       };
        
    } 
    
    public static <T extends Serializable> OptWrap<T> wrap( Option<T> optValue ) {
        return new OptWrap<>(optValue);
    }
    
    public static class OptWrap<T extends Serializable> {
        
        private final Option<T> optValue;
        
        private OptWrap( Option<T> optValue ) {
            this.optValue = optValue;
        }        
        
        public Option<T> getOption() {
            return optValue;
        }
        
        public T orElseGet( final Supplier<? extends T> s) {
            return Options.orElseGet( this.optValue, s );
        }
        
        public <U extends Serializable> OptWrap<U> flatMap( 
            final Function<? super T, ? extends Option<U>>  f ) {
            return new OptWrap<>( Options.flatMap( this.optValue, f ) );
        }
        
        public <U extends Serializable> OptWrap<U> map( 
                final Function<? super T, ? extends U>  f ) {
           return new OptWrap<>( Options.map( this.optValue, f  ) );
        }
        
        public Optional<T> toOptional() {
           return Options.toOptional( this.optValue );
        }
        
    }
   
}
