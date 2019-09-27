package com.alexjdevman.asset.currencyasset;

import com.alexjdevman.asset.service.ProfitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Application tests
 *
 * @author Aleksey Gorbachev
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyAssetApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	/**
	 * Profit calculation service
	 */
	@Autowired
	private ProfitService profitService;

	/**
	 * Profit calculation test
	 */
	@Test
	public void profitCalculationTest() {
		BigDecimal amount = new BigDecimal(1500);
		BigDecimal originalRate = new BigDecimal(65.00);
		BigDecimal currentRate = new BigDecimal(62.00);

		assertEquals(-4477.50, profitService.calculateProfit(amount, originalRate, currentRate).doubleValue());

		amount = new BigDecimal(2000);
		originalRate = new BigDecimal(60.00);
		currentRate = new BigDecimal(65.00);

		assertEquals(9950.00, profitService.calculateProfit(amount, originalRate, currentRate).doubleValue());
	}

	/**
	 * Profit REST test
	 */
	@Test
	public void profitRestTest() throws Exception {
		final String restURL = "/profit?amount=1500&rate=65.00&currentRate=62.00";
		mockMvc.perform(get(restURL))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("-4477.50")));
	}

}
