package com.weyne.teste.controller;

import com.weyne.teste.model.Empresa;
import com.weyne.teste.model.Funcionario;
import com.weyne.teste.model.constantes.API;
import com.weyne.teste.model.dto.AdicionarSaldoDTO;
import com.weyne.teste.model.dto.TransferenciaDTO;
import com.weyne.teste.service.TesteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin
@Slf4j
@RestController
@Api(value = "TESTE")
@RequestMapping(value = API.VERSAO, produces = MediaType.APPLICATION_JSON_VALUE)
public class TesteController {

    @Autowired
    private TesteService testeService;

    @PostMapping("/empresa")
    @ApiOperation(value = "Inclui uma empresa")
    public ResponseEntity incluirEmpresa(@RequestBody @Valid Empresa empresa, HttpServletRequest request) {

        Empresa empresaIncluida = testeService.incluirEmpresa(empresa);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(empresaIncluida.getId()).toUri();
        return ResponseEntity.created(uri).body(empresaIncluida);
    }

    @GetMapping("/empresa")
    @ApiOperation(value = "Buscar empresas")
    public ResponseEntity<List<Empresa>> buscarTodasEmpresas(Pageable pageable, HttpServletRequest request) {
        List<Empresa> empresas = testeService.buscarTodasEmpresas();
        return ResponseEntity.ok(empresas);
    }

    @GetMapping("/empresa/saldo/{id}")
    @ApiOperation(value = "Buscar empresas")
    public ResponseEntity<Double> getSaldoEmpresa(@PathVariable Long id, HttpServletRequest request) {
        Double saldo = testeService.getSaldoEmpresa(id);
        return ResponseEntity.ok(saldo);
    }

    @PostMapping("/funcionario")
    @ApiOperation(value = "Inclui um funcionario")
    public ResponseEntity <Funcionario> incluirFuncionario(@RequestBody @Valid Funcionario funcionario, HttpServletRequest request) {

        Funcionario funcionarioIncluido = testeService.incluirFuncionario(funcionario);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(funcionarioIncluido.getId()).toUri();
        return ResponseEntity.created(uri).body(funcionarioIncluido);
    }

    @GetMapping("/funcionario")
    @ApiOperation(value = "Buscar funcionarios")
    public ResponseEntity<List<Funcionario>> buscarTodosFuncionarios(Pageable pageable, HttpServletRequest request) {
        List<Funcionario> funcionarios = testeService.buscarTodosFuncionarios();
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/funcionario/saldo/{id}")
    @ApiOperation(value = "Buscar funcionarios")
    public ResponseEntity<Double> getSaldoFuncionario(@PathVariable Long id, HttpServletRequest request) {
        Double saldo = testeService.getSaldoFuncionario(id);
        return ResponseEntity.ok(saldo);
    }

    @PostMapping("/transferencia")
    @ApiOperation(value = "Realiza uma transferencia")
    public ResponseEntity<String> transferencia(@RequestBody @Valid TransferenciaDTO dto, HttpServletRequest request) {

        String response = testeService.transferirSaldo(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/adicionar-saldo")
    @ApiOperation(value = "adiciona um valor")
    public ResponseEntity<String> adicionarSaldo(@RequestBody @Valid AdicionarSaldoDTO dto, HttpServletRequest request) {
        String response = testeService.adicionarSaldo(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/funcionarios-empresa/{id}")
    @ApiOperation(value = "Buscar funcionarios por empresa")
    public ResponseEntity<List<Funcionario>> buscarFuncionariosPorEmpresa(@PathVariable Long id, HttpServletRequest request) {
        List<Funcionario> funcionarios = testeService.buscarFuncionariosPorEmpresa(id);
        return ResponseEntity.ok(funcionarios);
    }
}
