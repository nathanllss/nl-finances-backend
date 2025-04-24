package com.nathan.nl_finances.model.projections;

import com.nathan.nl_finances.model.enums.TransactionType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public interface TransactionMinDto {

    String getTitle();
    String getDescription();
    TransactionType getType();
    OffsetDateTime getMoment();
    BigDecimal getTransactionValue();
}