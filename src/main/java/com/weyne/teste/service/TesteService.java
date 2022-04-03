package com.weyne.teste.service;


import com.weyne.teste.model.Empresa;
import com.weyne.teste.model.Funcionario;
import com.weyne.teste.model.dto.AdicionarSaldoDTO;
import com.weyne.teste.model.dto.TransferenciaDTO;
import com.weyne.teste.repository.EmpresaRepository;
import com.weyne.teste.repository.FuncionarioRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;


@Service
public class TesteService {

    private static final String NAOENCONTRADO = "Não Encontrado";

    private final EmpresaRepository empresaRepository;

    private final FuncionarioRepository funcionarioRepository;

    public TesteService(EmpresaRepository empresaRepository, FuncionarioRepository funcionarioRepository) {
        this.empresaRepository = empresaRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public Empresa incluirEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public List<Empresa> buscarTodasEmpresas() {
        return empresaRepository.findAll();
    }

    public Optional <Empresa> buscarEmpresaPorId(Long id) {
        return empresaRepository.findById(id);
    }

    public Double getSaldoEmpresa (Long id) {
        Optional<Empresa> empresa = this.buscarEmpresaPorId(id);
        if(empresa.isPresent()){
            return empresa.get().getSaldo();
        }else{
            return null;
        }
    }

    public Funcionario incluirFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> buscarTodosFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Optional <Funcionario> buscarFuncionarioPorId(Long id) {
        return funcionarioRepository.findById(id);
    }

    public Double getSaldoFuncionario (Long id) {
        Optional<Funcionario> funcionario = this.buscarFuncionarioPorId(id);
        if(funcionario.isPresent()){
            return funcionario.get().getSaldo();
        }else{
            return null;
        }
    }


    public String transferirSaldo(TransferenciaDTO dto) {
        Optional<Empresa> empresa = this.buscarEmpresaPorId(dto.getIdEmpresa());
        Optional<Funcionario> funcionario = this.buscarFuncionarioPorId(dto.getIdFuncionario());
        if (empresa.isPresent() && funcionario.isPresent()) {
            if (funcionario.get().getEmpresa().getId() == empresa.get().getId()) {
                if (empresa.get().getSaldo() >= dto.getValor()) {
                    empresa.get().setSaldo(empresa.get().getSaldo() - dto.getValor());
                    empresaRepository.save(empresa.get());
                    funcionario.get().setSaldo(funcionario.get().getSaldo() + dto.getValor());
                    funcionarioRepository.save(funcionario.get());
                    return "Transferencia realizada";
                } else {
                    return "Saldo da empresa é insuficiente";
                }
            } else {
                return "O funcionario não trabalha nessa empresa";
            }
        } else {
            return "Funcionario ou empresa inexistente";
        }
    }


    public String adicionarSaldo(AdicionarSaldoDTO dto){
        Optional<Empresa> empresa = this.buscarEmpresaPorId(dto.getIdEmpresa());
        if (empresa.isPresent()){
            empresa.get().setSaldo(empresa.get().getSaldo() + dto.getValor());
            empresaRepository.save(empresa.get());
            return "Valor adicionado";
        } else {
            return "Empresa não encontrada";
        }
    }

    public List<Funcionario> buscarFuncionariosPorEmpresa(Long idEmpresa){
        return funcionarioRepository.findAllByEmpresa_Id(idEmpresa);

    }

}
