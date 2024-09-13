package by.clevertec.repository;

import by.clevertec.entity.CakeEntity;
import by.clevertec.service.util.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

class CakeRepositoryTest {
    @InjectMocks
    private CakeRepository cakeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCake() {
        //given
        List<CakeEntity> cakeList = cakeRepository.getCake();

        //when then
        assertNotNull(cakeList);

    }

    @Test
    void getCakeById() {
        //given
        UUID cakeId = cakeRepository.getCake().get(0).getId();
        Optional<CakeEntity> cakeEntity = cakeRepository.getCakeById(cakeId);

        //when then
        assertEquals(cakeId, cakeEntity.get().getId());
    }

    @Test
    void create() {
        //given
        CakeEntity cakeEntity = TestData.generateCakeEntity();
        CakeEntity updateCake = cakeRepository.create(cakeEntity);

        //when then
        assertEquals(cakeEntity, updateCake);
    }

    @Test
    void update() {
        //given

        UUID cakeId = UUID.randomUUID();
        CakeEntity cakeEntity = TestData.generateCakeEntity();

        //when
        CakeEntity updateCake = cakeRepository.update(cakeId, cakeEntity);

        //then
        assertEquals(cakeEntity, updateCake);
    }

    @Test
    void delete() {
        //given

        UUID cakeId = cakeRepository.getCake().get(0).getId();

        //when then

        assertDoesNotThrow(() -> cakeRepository.delete(cakeId));
    }
}
