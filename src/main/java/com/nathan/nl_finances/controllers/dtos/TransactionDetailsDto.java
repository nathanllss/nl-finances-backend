package com.nathan.nl_finances.controllers.dtos;

import com.nathan.nl_finances.domain.enums.TransactionType;
import lombok.Value;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Value
public class TransactionDetailsDto {

    UUID id;
    UUID ownerId;
    String title;
    String description;
    CategoryDto category;
    TransactionType type;
    OffsetDateTime moment;
    BigDecimal value;
    boolean recurring;
}