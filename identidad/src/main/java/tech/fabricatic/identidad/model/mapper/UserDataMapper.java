package tech.fabricatic.identidad.model.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;

import tech.fabricatic.identidad.common.JsonMapper;
import tech.fabricatic.identidad.model.details.UserData;

public class UserDataMapper implements JsonMapper<UserData>{

    @Override
    public UserData jsonToPojo(String jsonStr) throws JsonProcessingException {
        UserData data = mapper.readValue(jsonStr, UserData.class);
        return data;
    }
    
}
