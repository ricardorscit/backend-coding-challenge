package com.ciandt.investment.core.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ciandt.investment.core.domain.Fundo;
import com.ciandt.investment.core.usecase.ObterCaptacaoLiquidaUseCase;

@RestController("/captacao")
public class CaptacaoController {
	
	private final ObterCaptacaoLiquidaUseCase obterCaptacaoLiquidaUseCase;
	
	public CaptacaoController (ObterCaptacaoLiquidaUseCase obterCaptacaoLiquidaUseCase) {
		this.obterCaptacaoLiquidaUseCase = obterCaptacaoLiquidaUseCase;
	}
	
	
	@GetMapping
	public ResponseEntity<List<Fundo>> getCaptacao(){

		return ResponseEntity.ok(obterCaptacaoLiquidaUseCase.retornaCaptacaoLiquida());
	}

}
