package com.hardycherry.filters;

import com.hardycherry.exception.FilterParsingException;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class FilterParser {

    private final String AND = "and";
    private final String OR = "or";
    private final String NOT = "not";
    private final String EQUALS = "equals";
    private final String TRUE = "true";
    private final String FALSE = "false";
    private final String PRESENT = "present";
    private final String GT = "gt";
    private final String LT = "lt";
    private final String REGEX = "regex";

    public Filter<String, Object> parse(String filter) throws FilterParsingException {
        GsonJsonParser parser = new GsonJsonParser();
        Map<String, Object> jsonData = parser.parseMap(filter);

        Filter.Builder<String, Object> builder = Filter.builder();
        if(jsonData.containsKey("filters") && jsonData.get("filters") instanceof List<?> filterList) {

            parse(builder, filterList);
            return builder.build();
        }

        return null;
    }

    private void parse(Filter.Builder<String, Object> builder, List<?> filterList) throws FilterParsingException {
        for(var filterObj : filterList) {

            if(filterObj instanceof Map<?, ?> filterMap) {
                var filterType = (String)filterMap.get("type");
                switch(filterType.toLowerCase()) {
                    case AND:
                        Filter.Builder<String, Object> andBuilder = Filter.builder();
                        parse(andBuilder, (List<?>)filterMap.get("list"));
                        builder.and(andBuilder.build());
                        break;
                    case OR:
                        Filter.Builder<String, Object> orBuilder = Filter.builder();
                        parse(orBuilder, (List<?>)filterMap.get("list"));
                        builder.or(orBuilder.build());
                        break;
                    case NOT:
                        Filter.Builder<String, Object> notBuilder = Filter.builder();
                        parse(notBuilder, (List<?>)filterMap.get("list"));
                        builder.not(notBuilder.build());
                        break;
                    case EQUALS:
                        builder.equals((String)filterMap.get("key"), filterMap.get("value"));
                        break;
                    case TRUE:
                        builder.isTrue((String)filterMap.get("key"));
                        break;
                    case FALSE:
                        builder.isFalse((String)filterMap.get("key"));
                        break;
                    case PRESENT:
                        builder.isPresent((String)filterMap.get("key"));
                        break;
                    case GT:
                        builder.gt((String)filterMap.get("key"), (String)filterMap.get("value"));
                        break;
                    case LT:
                        builder.lt((String)filterMap.get("key"), (String)filterMap.get("value"));
                        break;
                    case REGEX:
                        builder.regex((String)filterMap.get("key"), (String)filterMap.get("value"));
                        break;
                    default:
                        throw new FilterParsingException("Json contains unsupported types");

                }
            }
        }
    }
}
