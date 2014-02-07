#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

public class DateDeserializer extends JsonDeserializer<DateTime> {

    private static DateTimeFormatter formatter;

    public DateDeserializer() {
        formatter =  DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public DateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return DateTime.parse(jp.getText(), formatter);
    }
}
