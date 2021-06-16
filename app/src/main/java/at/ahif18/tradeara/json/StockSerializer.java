package at.ahif18.tradeara.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.io.StringWriter;

import at.ahif18.tradeara.data.Stock;

public class StockSerializer extends JsonSerializer<Stock> {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void serialize(Stock value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {

        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, value);
        String content = writer.toString().replace("\"", "");
        System.out.println(content);
        gen.writeFieldName(content);
    }
}
