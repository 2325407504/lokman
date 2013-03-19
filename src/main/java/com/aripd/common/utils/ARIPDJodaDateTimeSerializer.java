package com.aripd.common.utils;

import java.io.IOException;
import java.util.Locale;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ARIPDJodaDateTimeSerializer extends JsonSerializer<DateTime> {

	// TODO Locale bilgisini SessionLocaleResolver dan nasıl alabiliriz
	private static DateTimeFormatter formatter = DateTimeFormat.forStyle("MS").withLocale(Locale.GERMAN);

	@Override
	public void serialize(DateTime value, JsonGenerator gen,
			SerializerProvider arg2) throws IOException,
			JsonProcessingException {

		gen.writeString(formatter.print(value));

	}

}
