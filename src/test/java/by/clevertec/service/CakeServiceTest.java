package by.clevertec.service;

import by.clevertec.domain.Cake;
import by.clevertec.entity.CakeEntity;
import by.clevertec.exception.CakeNotFoundException;
import by.clevertec.mapper.CakeMapper;
import by.clevertec.repository.CakeRepository;
import by.clevertec.service.util.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CakeServiceTest {

    @Mock
    private CakeRepository cakeRepository;
    @Mock
    private CakeMapper cakeMapper;
    @InjectMocks
    private CakeService cakeService;

    @Test
    void getCake() {
        //given
        List<CakeEntity> cakeEntityList = List.of(TestData.generateCakeEntity());

        List<Cake> cakeList = List.of(new Cake());

        when(cakeRepository.getCake()).thenReturn(cakeEntityList);
        when(cakeMapper.toDomains(cakeEntityList)).thenReturn(cakeList);

        //when
        List<Cake> cakes = cakeService.getCake();

        //then
        assertFalse(cakes.isEmpty());
    }

    @Test
    void getCakeById() {
        //given
        UUID cakeId = UUID.randomUUID();
        CakeEntity cakeEntity = new CakeEntity();
        Cake cake = new Cake();

        when(cakeRepository.getCakeById(cakeId)).thenReturn(Optional.of(cakeEntity));
        when(cakeMapper.toDomains(cakeEntity)).thenReturn(cake);

        //when
        Cake actualCake = cakeService.getCakeById(cakeId);

        //then
        assertEquals(cake.getId(), actualCake.getId());
    }

    @Test
    void getNotCakeById() {
        //given
        UUID cakeId = UUID.randomUUID();

        when(cakeRepository.getCakeById(cakeId)).thenReturn(Optional.empty());

        //when then

        assertThrows(CakeNotFoundException.class, () -> cakeService.getCakeById(cakeId));

        verifyNoInteractions(cakeMapper);
    }

    @Test
    void create() {
        //given

        CakeEntity cakeEntity = TestData.generateCakeEntity();
        Cake cake = cakeMapper.toDomains(cakeEntity);
        when(cakeMapper.toEntity(cake)).thenReturn(cakeEntity);
        when(cakeRepository.create(cakeEntity)).thenReturn(cakeEntity);
        when(cakeMapper.toDomains(cakeEntity)).thenReturn(cake);

        //when

        Cake result = cakeService.create(cake);

        // then

        assertEquals(cake, result);
    }

    @Test
    void update() {
        //given
        UUID cakeId = UUID.randomUUID();
        CakeEntity cakeEntity = TestData.generateCakeEntity();
        CakeEntity updatedCakeEntity = cakeMapper.toEntity(cakeMapper.toDomains(cakeEntity));

        doReturn(updatedCakeEntity).when(cakeRepository).update(cakeId, updatedCakeEntity);

        //when
        CakeEntity result = cakeRepository.update(cakeId, updatedCakeEntity);

        //then
        assertEquals(updatedCakeEntity, result);
    }

    @Test
    void delete() {
        //given
        UUID cakeId = UUID.randomUUID();
        //when
        cakeRepository.delete(cakeId);
        //then
        verify(cakeRepository).delete(cakeId);

    }
}
