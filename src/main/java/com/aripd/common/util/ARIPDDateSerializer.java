package com.aripd.common.util;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.context.i18n.LocaleContextHolder;

public class ARIPDDateSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) throws IOException, JsonProcessingException {
        //SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        DateTimeFormatter formatter = DateTimeFormat.forStyle("S-").withLocale(LocaleContextHolder.getLocale());
        gen.writeString(formatter.print(value.getTime()));
    }
}
