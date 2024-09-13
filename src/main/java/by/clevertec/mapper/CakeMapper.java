package by.clevertec.mapper;

import by.clevertec.domain.Cake;
import by.clevertec.entity.CakeEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CakeMapper {
    List<Cake> toDomains(List<CakeEntity> cakeEntities);

    Cake toDomains(CakeEntity cakeEntity);
    
    CakeEntity toEntity(Cake cake);
}
