package com.aston.astonTestTask.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ActionSearchCriteria {
    private Integer billId;
    private Integer customerId;
}
