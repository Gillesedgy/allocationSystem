package com.cas.clientAllocationSystem.Enum;

import java.math.BigDecimal;

public enum Role {
    FRONT_END_DEVELOPER,
    BACK_END_DEVELOPER,
    MANAGER,
    QA,
    DESIGNER;
    // MORE TO COME

    public BigDecimal getHourlyRate() {
        switch (this) {
            case FRONT_END_DEVELOPER:
                return new BigDecimal("60.00");
            case BACK_END_DEVELOPER:
                return new BigDecimal("70.00");
            case MANAGER:
                return new BigDecimal("90.00");
            case QA:
                return new BigDecimal("45.00");
            case DESIGNER:
                return new BigDecimal("55.00");
            default:
                return new BigDecimal("50.00");
        }
    }
}

