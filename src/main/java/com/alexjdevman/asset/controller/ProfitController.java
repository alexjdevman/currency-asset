package com.alexjdevman.asset.controller;

import com.alexjdevman.asset.service.ProfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Profit calculation controller
 *
 * @author Aleksey Gorbachev
 */
@RestController
public class ProfitController {

    /**
     * Profit service
     */
    @Autowired
    private ProfitService profitService;

    /**
     * Calculating profit amount and returning it on UI
     *
     * @param amount      amount purchased (in USD)
     * @param rate        original rate
     * @param currentRate current rate
     * @return profit amount (in RUB)
     */
    @GetMapping(value = "/profit")
    public ResponseEntity<?> calculateProfit(@RequestParam("amount") BigDecimal amount,
                                             @RequestParam("rate") BigDecimal rate,
                                             @RequestParam("currentRate") BigDecimal currentRate) {
        return new ResponseEntity<>(profitService.calculateProfit(amount, rate, currentRate), HttpStatus.OK);
    }
}
