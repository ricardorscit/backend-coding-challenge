package com.ciandt.investment.core.usecase;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ciandt.investment.core.domain.Fundo;
import com.ciandt.investment.core.domain.InformeDiario;

@Service
public class ObterCaptacaoLiquidaUseCase {

    private final InformeDiarioBoundary informeDiarioBoundary;

    public ObterCaptacaoLiquidaUseCase(InformeDiarioBoundary informeDiarioBoundary) {
        this.informeDiarioBoundary = informeDiarioBoundary;
    }
    

    public List<Fundo> retornaCaptacaoLiquida(){
    	
    	List<InformeDiario> all = informeDiarioBoundary.getAll();
    	
		Map<String, Double> collect = all.stream()
  			  .collect(Collectors.groupingBy(InformeDiario::getCnpj, Collectors.summingDouble(x -> calculaLiquido(x))));
    	
    	List<Fundo> listaFundo = new ArrayList<>();
    	collect.forEach((k, v) -> {
    		Fundo fundo = new Fundo();
    		fundo.setCnpj(k);
    		fundo.setCaptacaoLiquida(new BigDecimal(v).round(new MathContext(2)).doubleValue());
    		
    		listaFundo.add(fundo);
    		
    	});
    	
    	
		return listaFundo
    			.stream()
    			.sorted(Comparator.comparingDouble(Fundo::getCaptacaoLiquida).reversed())
    			.limit(10)
    			.collect(Collectors.toList());
				    	
    }


	private double calculaLiquido(InformeDiario x) {
		
		return x.getCaptacaoDia().round(new MathContext(2)).subtract(x.getResgateDia().round(new MathContext(2))).doubleValue();
	}

    
}
