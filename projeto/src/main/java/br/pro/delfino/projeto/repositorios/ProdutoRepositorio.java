package br.pro.delfino.projeto.repositorios;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.pro.delfino.projeto.entidades.Produto;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {

    Optional<Produto> findByNomeIgnoreCase(String nome);

    List<Produto> findByNomeContainingIgnoreCase(String nome);

    List<Produto> findByCategoriaId(Long categoriaId);

    List<Produto> findByPrecoBetween(BigDecimal precoMinimo, BigDecimal precoMaximo);

    List<Produto> findByEstoqueLessThan(Integer estoqueMaximo);
}
