package tech.edev404.votaciones.model.mapper.common;

public interface GenericMapper<P, D> {
    P dtoToPojo(D dto);
    D pojoToDto(P pojo);
}
