package com.alexjdevman.asset.service;

import java.math.BigDecimal;

/**
 * Profit calculation service interface
 *
 * @author Aleksey Gorbachev
 */
public interface ProfitService {

    /**
     * Calculating profit amount
     *
     * @param amount      amount purchased (in USD)
     * @param rate        original rate
     * @param currentRate current rate
     * @return profit amount (in RUB)
     */
    BigDecimal calculateProfit(BigDecimal amount, BigDecimal rate, BigDecimal currentRate);
}
