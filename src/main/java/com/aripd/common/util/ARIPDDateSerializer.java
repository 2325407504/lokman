package com.aripd.common.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class ARIPDDateSerializer extends JsonSerializer<Date> {

    /**
     * TODO Locale bilgisini SessionLocaleResolver dan nasıl alabiliriz
     *
     */
    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) throws IOException, JsonProcessingException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String formattedDate = formatter.format(value);
        gen.writeString(formattedDate);
    }
}
