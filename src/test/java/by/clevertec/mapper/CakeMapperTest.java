package by.clevertec.mapper;

import by.clevertec.common.CakeType;
import by.clevertec.domain.Cake;
import by.clevertec.entity.CakeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CakeMapperTest {
    @InjectMocks
    private CakeMapperImpl cakeMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testToDomainsList() {
        //given

        List<CakeEntity> cakeEntities = List.of(
                new CakeEntity(UUID.randomUUID(), "cake1", CakeType.BIG, OffsetDateTime.now().plusDays(1)),
                new CakeEntity(UUID.randomUUID(), "cake2", CakeType.SMALL, OffsetDateTime.now())
        );

        List<Cake> cakes = cakeMapper.toDomains(cakeEntities);

        //when then
        assertEquals(2, cakes.size());
        assertEquals("cake1", cakes.get(0).getTitle());
        assertEquals(CakeType.BIG, cakes.get(0).getCakeType());
    }

    @Test
    void testToDomains() {
        //given
        CakeEntity cakeEntity = new CakeEntity(UUID.randomUUID(), "testCake", CakeType.BIG, OffsetDateTime.now().plusDays(2));

        Cake cake = cakeMapper.toDomains(cakeEntity);

        //when then
        assertEquals(cakeEntity.getId(), cake.getId());
        assertEquals(cakeEntity.getTitle(), cake.getTitle());
        assertEquals(cakeEntity.getCakeType(), cake.getCakeType());
    }

    @Test
    void toEntity() {
        //given

        Cake cake = new Cake(UUID.randomUUID(), "mappedCake", CakeType.SMALL, OffsetDateTime.now().plusDays(5));

        CakeEntity cakeEntity = cakeMapper.toEntity(cake);

        //when then
        assertEquals(cake.getId(), cakeEntity.getId());
        assertEquals(cake.getTitle(), cakeEntity.getTitle());
        assertEquals(cake.getCakeType(), cakeEntity.getCakeType());
    }
}
