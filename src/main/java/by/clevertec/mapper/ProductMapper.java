package by.clevertec.mapper;

import by.clevertec.dto.CarDto;
import by.clevertec.dto.InfoCarDto;
import by.clevertec.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    /**
     * Маппит DTO в продукт без UUID
     *
     * @param carDto - DTO для маппинга
     * @return новый продукт
     */
    Car toCar(CarDto carDto);

    /**
     * Маппит текущий продукт в DTO без даты
     *
     * @param car - существующий продукт
     * @return DTO с идентификатором
     */
    InfoCarDto toInfoProductDto(Car car);

    /**
     * Сливает существующий продукт с информацией из DTO
     * не меняет дату создания и идентификатор
     *
     * @param car    существующий продукт
     * @param carDto информация для обновления
     * @return обновлённый продукт
     */
    Car merge(@MappingTarget Car car, CarDto carDto);
}
