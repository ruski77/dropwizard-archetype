#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

public class DateSerializer extends JsonSerializer<DateTime> {

    private static DateTimeFormatter formatter;

    public DateSerializer() {
        formatter =  DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    }

    public DateSerializer(String format) {
        formatter =  DateTimeFormat.forPattern(format);
    }

    @Override
    public void serialize(DateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(formatter.print(value));
    }
}
