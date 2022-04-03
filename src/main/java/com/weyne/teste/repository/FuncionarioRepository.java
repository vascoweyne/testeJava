package com.weyne.teste.repository;

import com.weyne.teste.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findById(Long id);
    List<Funcionario> findAll();
    List<Funcionario> findAllByEmpresa_Id(long id);
}
