package com.hardycherry.filters;

import java.util.function.Function;

public class FilterOperation {
    public Function<FilterPredicate, Boolean> BOOLEAN_TRUE = p -> Boolean.TRUE.equals(p.getRight());
    public Function<FilterPredicate, Boolean> BOOLEAN_FALSE = p -> Boolean.FALSE.equals(p.getRight());
    public Function<FilterPredicate, Boolean> AND = p -> p.getRight() && p.getLeft();
    public Function<FilterPredicate, Boolean> OR = p -> Boolean.TRUE.equals(p.getRight());
    public Function<FilterPredicate, Boolean> NOT = p -> Boolean.TRUE.equals(p.getRight());
    public Function<FilterPredicate, Boolean> PRESENT = p -> Boolean.TRUE.equals(p.getRight());
    public Function<FilterPredicate, Boolean> EQUALS = p -> Boolean.TRUE.equals(p.getRight());
    public Function<FilterPredicate, Boolean> LESS_THAN = p -> Boolean.TRUE.equals(p.getRight());
    public Function<FilterPredicate, Boolean> GREATER_THAN = p -> Boolean.TRUE.equals(p.getRight());
    public Function<FilterPredicate, Boolean> REGEX = p -> Boolean.TRUE.equals(p.getRight());

    //      a. boolean literals (constants): “true” and “false”
//      b. logical operators which can be used to combine the results of other filters: AND, OR, and NOT
//      c. comparison operators (care should be taken to deal with missing properties):
//          i. property is present
//          ii. property is equal to some value
//          iii. property is less than some value
//          iv. property is greater than some value
//          v. property matches a regular expression.
}
