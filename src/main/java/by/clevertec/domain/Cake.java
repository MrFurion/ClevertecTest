package by.clevertec.domain;

import by.clevertec.common.CakeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cake {

    private UUID id;
    private String title;
    private CakeType cakeType;
    private OffsetDateTime expiredPeriod;

}
