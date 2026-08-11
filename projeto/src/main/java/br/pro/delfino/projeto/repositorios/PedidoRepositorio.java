package br.pro.delfino.projeto.repositorios;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.pro.delfino.projeto.entidades.Pedido;
import br.pro.delfino.projeto.enums.StatusPedido;

public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {

    List<Pedido> findByClienteId(Long clienteId);

    List<Pedido> findByStatus(StatusPedido status);

    List<Pedido> findByDataBetween(LocalDateTime inicio, LocalDateTime fim);

    List<Pedido> findByClienteIdAndStatus(Long clienteId, StatusPedido status);
}
