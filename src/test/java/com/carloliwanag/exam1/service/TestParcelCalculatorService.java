package com.carloliwanag.exam1.service;


import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestParcelCalculatorService {

	@InjectMocks
	ParcelCalculatorServiceImpl service;
	
	@Test
	public void computeVolume() {
		
		Integer height = 2;
		Integer lenght = 3;
		Integer width = 1;
		
		Integer volume = service.computeVolume(height, lenght, width);
		
		assertEquals(volume.intValue(), 6);
	}
	
}
