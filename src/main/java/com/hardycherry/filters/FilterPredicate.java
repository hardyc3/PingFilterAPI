package com.hardycherry.filters;

import lombok.Data;

@Data
public class FilterPredicate {
    FilterOperation operation;
    FilterPredicate right;
    FilterPredicate left;
}
