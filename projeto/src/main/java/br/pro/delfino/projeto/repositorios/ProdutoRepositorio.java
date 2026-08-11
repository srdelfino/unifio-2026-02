package br.pro.delfino.projeto.repositorios;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import br.pro.delfino.projeto.entidades.Produto;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {
    Optional<Produto> findByNomeIgnoreCase(String nome);
}