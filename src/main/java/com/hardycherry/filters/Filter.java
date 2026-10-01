package com.hardycherry.filters;

import com.hardycherry.model.ResourceDto;

import java.util.List;

public class Filter {

    private boolean valid = false;

    private FilterPredicate filterPredicate;
//  1. The ability to determine whether or not a filter matches a given resource (where a resource is represented using a
//Map<String,String>).
//  2. Support for the following types of filter predicate (it is not necessary to implement all of these but they should try to
//implement at least one of each category):
//      a. boolean literals (constants): “true” and “false”
//      b. logical operators which can be used to combine the results of other filters: AND, OR, and NOT
//      c. comparison operators (care should be taken to deal with missing properties):
//          i. property is present
//          ii. property is equal to some value
//          iii. property is less than some value
//          iv. property is greater than some value
//          v. property matches a regular expression.
//  3. The ability to programmatically construct arbitrarily complex filters.
//  4. A string representation, including the ability to generate and parse filters from the string representation.

//    filter = (admin=true OR (role is present AND role=admin)) NOT (age is present AND (age=3 OR age>3))

    public List<ResourceDto> filterResources(List<ResourceDto> resources) {

    }

    public boolean matches(ResourceDto resourceDto) {

    }

    public boolean isValid() {
        return valid;
    }
}
