package by.clevertec.common.helper;
import java.time.OffsetDateTime;

class DataSupplierTest implements DataSupplier{


    @Override
    public OffsetDateTime getCurrentDataTim() {
        return OffsetDateTime.parse("2022-08-08T23:23:23.123123Z");
    }
}