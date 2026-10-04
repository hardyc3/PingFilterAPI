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

    public Filter parse(String filter) throws FilterParsingException {
        GsonJsonParser parser = new GsonJsonParser();
        Map<String, Object> jsonData = parser.parseMap(filter);

        Filter.Builder builder = Filter.builder();
        if(jsonData.containsKey("filters") && jsonData.get("filters") instanceof List<?> filterList) {

            for(var filterObj : filterList) {

                if(filterObj instanceof Map filterMap) {
                    var filterType = (String)filterMap.get("type");
                    switch(filterType.toLowerCase()) {
                        case AND:
                        case OR:
                        case NOT:
                            parseFilters(builder, (List)filterMap.get("list"));
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
                return builder.build();
            }
        }

        return null;
    }

    private void parseFilters(Filter.Builder builder, List<Object> filters) {

    }
}
