package com.logicaalternativa.algebraictypes.sideeffects;

import java.io.Serializable;

public sealed interface Option<T extends Serializable> extends Serializable {
    
    record Some<T extends Serializable>(T value) implements Option<T> {}

    record None<T extends Serializable>() implements Option<T> {}
}
