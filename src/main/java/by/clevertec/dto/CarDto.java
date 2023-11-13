package by.clevertec.dto;

import java.math.BigDecimal;

public record CarDto(

        /**
         * {@link ru.clevertec.product.entity.Product}
         */
        String name,


        /**
         * {@link ru.clevertec.product.entity.Product}
         */
        String description,


        /**
         * {@link ru.clevertec.product.entity.Product}
         */
        BigDecimal price) {
}
