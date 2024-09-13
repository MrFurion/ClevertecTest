package by.clevertec.repository;

import by.clevertec.common.CakeType;
import by.clevertec.entity.CakeEntity;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CakeRepository {
    private final List<CakeEntity> db = List.of(
            new CakeEntity(UUID.randomUUID(), "cake1", CakeType.BIG, OffsetDateTime.now().plusDays(1)),
            new CakeEntity(UUID.randomUUID(), "cake2", CakeType.BIG, OffsetDateTime.now()),
            new CakeEntity(UUID.randomUUID(), "cake3", CakeType.SMALL, OffsetDateTime.now())

    );

    public List<CakeEntity> getCake(){
        return db;
    }

    public Optional<CakeEntity> getCakeById(UUID cakeId){
        return Optional.ofNullable(db.get(0));
    }

    public CakeEntity create(CakeEntity cakeEntity){
        return cakeEntity;
    }

    public CakeEntity update (UUID cakeId, CakeEntity cakeEntity){
        return cakeEntity.setId(cakeId);
    }

    public void delete (UUID cakeId){
        //without body
    }
}
