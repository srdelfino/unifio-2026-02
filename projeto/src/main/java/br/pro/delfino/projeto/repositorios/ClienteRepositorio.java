package br.pro.delfino.projeto.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.pro.delfino.projeto.entidades.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmailIgnoreCase(String email);

    List<Cliente> findByNomeContainingIgnoreCase(String nome);
}
