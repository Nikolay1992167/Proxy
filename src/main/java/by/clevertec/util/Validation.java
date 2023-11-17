package by.clevertec.util;

import by.clevertec.dto.CarDto;
import by.clevertec.exception.ValidationException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static by.clevertec.constants.Constants.DESCRIPTION_REGEX;
import static by.clevertec.constants.Constants.NAME_REGEX;

public class Validation {

    /**
     * Проверяет объект для сохранения и формирует список ошибок для выбрасывания исключения.
     * @param carDto - проверяемый объект.
     */
    public void validate(CarDto carDto) {
        List<String> validationErrors = new ArrayList<>();
        validateName(carDto, validationErrors);
        validateDescription(carDto, validationErrors);
        validatePrice(carDto, validationErrors);
        throwValidationException(validationErrors);
    }

    private void validateName(CarDto carDto, List<String> errors) {
        if (carDto.name() != null) {
            if (!carDto.name().matches(NAME_REGEX)) {
                errors.add("Неверное имя car!");
            } else if (carDto.name().trim().isEmpty()) {
                errors.add("Не введено имя!");
            }
        }
    }

    private void validateDescription(CarDto carDto, List<String> errors) {
        if (carDto.description().matches(DESCRIPTION_REGEX)){
            errors.add("Неверное описание car!");
        }
    }

    private void validatePrice(CarDto carDto, List<String> errors) {
        if (carDto.price() == null) {
            errors.add("Цена не может быть null");
        } else {
            if (carDto.price().compareTo(BigDecimal.ZERO) <= 0) {
                errors.add("Корректно укажите цену, она не может быть меньше либо равна 0");
            }
        }
    }

    private void  throwValidationException(List<String> errors){
        if (!errors.isEmpty()){
            throw new ValidationException(errors);
        }
    }
}
