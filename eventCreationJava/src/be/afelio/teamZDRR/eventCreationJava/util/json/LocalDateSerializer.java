package be.afelio.teamZDRR.eventCreationJava.util.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateSerializer extends StdSerializer<LocalDateTime> {


	private static final long serialVersionUID = 1L;

	public LocalDateSerializer() {
        super(LocalDateTime.class);
    }

    @Override
    public void serialize(LocalDateTime value, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeString(value.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}