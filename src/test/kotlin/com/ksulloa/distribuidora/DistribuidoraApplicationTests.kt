package com.ksulloa.distribuidora

import com.ksulloa.distribuidora.service.DistribuidoraService
import com.ksulloa.distribuidora.service.DuenoService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DistribuidoraApplicationTests {

	@Autowired
	lateinit var duenoService: DuenoService

	@Test
	fun contextLoads() {
	}

	@Test
	fun verifySizeWordWhenIsIncorrect(){
		val response: Boolean = duenoService.verifyWord("01978456321","09874561287" )
		Assertions.assertEquals(false,response)
	}

	@Test
	fun verifySizeWordWhenIsCorrect(){
		val response: Boolean = duenoService.verifyWord("0197845632","09102564789")
		Assertions.assertEquals(true,response)
	}
}
