package com.nathan.nl_finances.controllers.dtos;

//@Value
//public class TransactionMinDto {
//
//    String id;
//    String title;
//    String description;
//    TransactionType type;
//    OffsetDateTime moment;
//    BigDecimal transactionValue;
//}

import com.nathan.nl_finances.model.enums.TransactionType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public interface TransactionMinDto {

    String getId();
    String getTitle();
    String getDescription();
    TransactionType getType();
    OffsetDateTime getMoment();
    BigDecimal getTransactionValue();
}