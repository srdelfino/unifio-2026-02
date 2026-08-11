package br.pro.delfino.projeto.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.pro.delfino.projeto.entidades.Pagamento;
import br.pro.delfino.projeto.enums.StatusPagamento;
import br.pro.delfino.projeto.enums.TipoPagamento;

public interface PagamentoRepositorio extends JpaRepository<Pagamento, Long> {

    Optional<Pagamento> findByPedidoId(Long pedidoId);

    List<Pagamento> findByStatus(StatusPagamento status);

    List<Pagamento> findByTipo(TipoPagamento tipo);
}
