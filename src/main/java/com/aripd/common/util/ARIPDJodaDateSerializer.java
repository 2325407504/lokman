package com.aripd.common.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.context.i18n.LocaleContextHolder;

public class ARIPDJodaDateSerializer extends JsonSerializer<DateTime> {

    @Override
    public void serialize(DateTime value, JsonGenerator gen, SerializerProvider arg2) throws IOException, JsonProcessingException {
        DateTimeFormatter formatter = DateTimeFormat.forStyle("S-").withLocale(LocaleContextHolder.getLocale());
        gen.writeString(formatter.print(value));
    }
}
