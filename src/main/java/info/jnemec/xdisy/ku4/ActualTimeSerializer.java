package info.jnemec.xdisy.ku4;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by jirka on 10.11.14.
 */
public class ActualTimeSerializer extends JsonSerializer<ActualTime> {

    @Override
    public void serialize(ActualTime actualTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", actualTime.getTimeZone().getID());
        jsonGenerator.writeStringField("displayName", actualTime.getTimeZone().getDisplayName());
        jsonGenerator.writeStringField("actualTime", actualTime.getTime().toString());
        jsonGenerator.writeEndObject();

    }
}
