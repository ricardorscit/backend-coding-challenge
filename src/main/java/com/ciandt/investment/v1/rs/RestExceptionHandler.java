package com.ciandt.investment.v1.rs;

import com.ciandt.investment.core.domain.exception.EInvestimentoErrorCode;
import com.ciandt.investment.core.domain.exception.InvestimentoDomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Erro> erroInternoHandler(Exception ex){
        Erro erro = new Erro(EInvestimentoErrorCode.ERRO_INTERNO.getErroCode());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }

    @ExceptionHandler(InvestimentoDomainException.class)
    public ResponseEntity<Erro> investimentoDomainExceptionHandler(InvestimentoDomainException ex){
        Erro erro = new Erro(ex.getErrorCode().getErroCode());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}