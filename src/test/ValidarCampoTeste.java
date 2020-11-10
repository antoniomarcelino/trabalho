package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import util.ValidarCampo;

class ValidarCampoTeste {
	
	

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void testChecarCampoVazio() {
		Assert.assertEquals(false, ValidarCampo.checarCampoVazio("Teste de Campo"));
	}

	@Test
	void testChecarAlfa() {
		Assert.assertEquals(true, ValidarCampo.checarAlfa("Alfa"));
	}

	@Test
	void testChecarFormatoData() {
		Assert.assertEquals(true, ValidarCampo.checarFormatoData("01/02/2000"));
	}

	@Test
	void testChecarNumerico() {
		Assert.assertEquals(true, ValidarCampo.checarNumerico("123"));
	}

	@Test
	void testChecarTamanhoTelefone() {
		Assert.assertEquals(true, ValidarCampo.checarTamanhoTelefone("62981812233"));;
	}

	@Test
	void testChecarTamanho() {
		Assert.assertEquals(true, ValidarCampo.checarTamanho("AEIOU", 5));
	}

}
