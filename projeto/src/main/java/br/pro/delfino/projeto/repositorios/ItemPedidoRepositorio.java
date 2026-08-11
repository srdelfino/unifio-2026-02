package br.pro.delfino.projeto.repositorios;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.pro.delfino.projeto.entidades.ItemPedido;

public interface ItemPedidoRepositorio extends JpaRepository<ItemPedido, Long> {

    List<ItemPedido> findByPedidoId(Long pedidoId);

    List<ItemPedido> findByProdutoId(Long produtoId);

    @Query("SELECT COALESCE(SUM(i.quantidade * i.valorUnitario), 0) "
            + "FROM ItemPedido i WHERE i.pedido.id = :pedidoId")
    BigDecimal calcularTotalDoPedido(@Param("pedidoId") Long pedidoId);
}
