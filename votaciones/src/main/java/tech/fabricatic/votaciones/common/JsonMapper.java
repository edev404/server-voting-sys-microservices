package tech.fabricatic.votaciones.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface JsonMapper<T> {

    ObjectMapper mapper = new ObjectMapper();

    public default String pojoToJson(T t) throws JsonProcessingException{
        String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(t);
        return jsonStr;
    } 

    public T jsonToPojo(String jsonStr) throws JsonProcessingException;
    /*
     *  We can access to de .class method of a generic type
     * Implementation should look like this:
     *  T t = mapper.readValue(jsonStr, T.class);
     *  return t;
     */
    
    
}
