package com.ciandt.investment.core.usecase;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ciandt.investment.core.domain.Fundo;
import com.ciandt.investment.core.domain.InformeDiario;
import com.ciandt.investment.dataprovider.InformeDiarioGateway;

public class ObterCaptacaoLiquidaUseCaseTest {

    private ObterCaptacaoLiquidaUseCase obterCaptacaoLiquidaUseCase;
    private InformeDiarioBoundary informeDiarioBoundary;

    @Before
    public void setup() {
        informeDiarioBoundary = new InformeDiarioGateway();
        obterCaptacaoLiquidaUseCase = new ObterCaptacaoLiquidaUseCase(informeDiarioBoundary);
    }

    @Test
    public void deveObterOsDadosdeInformesDiario() {

        List<InformeDiario> all = informeDiarioBoundary.getAll();
        
        
        Assert.assertEquals(325850, all.size());
    }

    
    @Test
    public void retornaCaptacaoLiquidaTest() {
    	
    	List<Fundo> retornaCaptacaoLiquida = obterCaptacaoLiquidaUseCase.retornaCaptacaoLiquida();
    	
    	Assert.assertNotNull(retornaCaptacaoLiquida);
    	Assert.assertEquals(10, retornaCaptacaoLiquida.size());
    	
    	
    }

}