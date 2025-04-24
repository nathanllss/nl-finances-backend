package com.nathan.nl_finances.projections;

import com.nathan.nl_finances.domain.enums.TransactionType;
import lombok.Value;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public interface TransactionMinDto {

    String getId();
    String getTitle();
    String getDescription();
    TransactionType getType();
    OffsetDateTime getMoment();
    BigDecimal getTransactionValue();
    boolean isRecurring();

    @Value
    public class TransactionMinDtoImpl implements TransactionMinDto {
        String id;
        String title;
        String description;
        TransactionType type;
        OffsetDateTime moment;
        BigDecimal transactionValue;
        boolean recurring;
    }
}
