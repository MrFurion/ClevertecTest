package by.clevertec.service;

import by.clevertec.common.helper.DataSupplier;
import by.clevertec.domain.Cake;
import by.clevertec.entity.CakeEntity;
import by.clevertec.exception.CakeNotFoundException;
import by.clevertec.mapper.CakeMapper;
import by.clevertec.repository.CakeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CakeService {
    private final CakeRepository cakeRepository;
    private final CakeMapper cakeMapper;
    private final DataSupplier dataSupplier;


    public List<Cake> getCake() {
        List<CakeEntity> cakes = cakeRepository.getCake();

        return cakeMapper.toDomains(cakes);
    }

    public Cake getCakeById(UUID cakeId) {
        CakeEntity cakeEntity = cakeRepository.getCakeById(cakeId)
                .orElseThrow(() -> CakeNotFoundException.getException(cakeId));
        return cakeMapper.toDomains(cakeEntity);
    }

    public Cake create(Cake cake) {
        CakeEntity cakeEntity = cakeMapper.toEntity(cake);
        CakeEntity createEntity = cakeRepository.create(cakeEntity);
        return cakeMapper.toDomains(createEntity);
    }

    public Cake update(UUID cakeId, Cake cake) {
        CakeEntity updateEntity = cakeMapper.toEntity(cake);
        CakeEntity cakeEntity = cakeRepository.update(cakeId, updateEntity);
        return cakeMapper.toDomains(cakeEntity);
    }

    public void delete(UUID cakeId) {
        cakeRepository.delete(cakeId);
    }
}
