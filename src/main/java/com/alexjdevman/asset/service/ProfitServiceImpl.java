package com.alexjdevman.asset.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Profit calculation service
 *
 * @author Aleksey Gorbachev
 */
@Service
public class ProfitServiceImpl implements ProfitService {

    /**
     * Spread exchange percent
     */
    @Value("${spread.exchange.percent}")
    private Double spread;

    /**
     * Calculating profit amount
     *
     * @param amount      amount purchased (in USD)
     * @param rate        original rate
     * @param currentRate current rate
     * @return profit amount (in RUB)
     */
    @Override
    public BigDecimal calculateProfit(BigDecimal amount, BigDecimal rate, BigDecimal currentRate) {
        BigDecimal result = amount.multiply(currentRate.add(rate.negate()));
        BigDecimal spreadValue = new BigDecimal(spread/100).negate().setScale(3, BigDecimal.ROUND_DOWN);
        return result.add(result.multiply(spreadValue)).setScale(2, BigDecimal.ROUND_DOWN);
    }
}
