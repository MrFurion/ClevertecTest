package by.clevertec.service.util;

import by.clevertec.common.CakeType;
import by.clevertec.entity.CakeEntity;

import java.time.OffsetDateTime;
import java.util.UUID;

public class TestData {

    public static CakeEntity generateCakeEntity(){
        return new CakeEntity()
                .setId(UUID.randomUUID())
                .setCakeType(CakeType.BIG)
                .setTitle("cake")
                .setExpiredPeriod(OffsetDateTime.now());
    }
}
