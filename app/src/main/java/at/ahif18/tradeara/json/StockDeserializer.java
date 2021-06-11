package at.ahif18.tradeara.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

import java.io.IOException;

import at.ahif18.tradeara.data.Stock;

public class StockDeserializer extends KeyDeserializer {
    @Override
    public Stock deserializeKey(String key, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        return new Stock(key);
    }
}
