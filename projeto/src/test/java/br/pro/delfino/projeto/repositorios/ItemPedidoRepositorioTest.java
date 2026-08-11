package br.pro.delfino.projeto.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import br.pro.delfino.projeto.entidades.ItemPedido;
import br.pro.delfino.projeto.entidades.Pedido;
import br.pro.delfino.projeto.entidades.Produto;
import br.pro.delfino.projeto.enums.StatusPedido;

@SpringBootTest
@Transactional
class ItemPedidoRepositorioTest {

    @Autowired
    private ItemPedidoRepositorio repositorio;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Test
    void deveBuscarItensDoPedido() {
        List<ItemPedido> itens = repositorio.findByPedidoId(1L);

        assertEquals(2, itens.size());
    }

    @Test
    void deveBuscarItensPorProduto() {
        List<ItemPedido> itens = repositorio.findByProdutoId(4L);

        assertEquals(2, itens.size());
    }

    @Test
    void deveCalcularTotalDoPedido() {
        // Pedido 1: 1x Notebook (5200.00) + 1x Mouse (150.00) = 5350.00
        BigDecimal total = repositorio.calcularTotalDoPedido(1L);

        assertEquals(new BigDecimal("5350.00"), total);
    }

    @Test
    void deveCalcularTotalDePedidoSemItensComoZero() {
        // O COALESCE no @Query garante 0 em vez de null para pedido vazio.
        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus(StatusPedido.PENDENTE);
        Pedido salvo = pedidoRepositorio.save(pedido);

        BigDecimal total = repositorio.calcularTotalDoPedido(salvo.getId());

        assertEquals(0, total.compareTo(BigDecimal.ZERO));
    }

    @Test
    void deveSalvarItemPedidoNovo() {
        Pedido pedido = pedidoRepositorio.findById(1L).orElseThrow();
        Produto produto = produtoRepositorio.findById(3L).orElseThrow();

        ItemPedido item = new ItemPedido();
        item.setQuantidade(2);
        item.setValorUnitario(new BigDecimal("350.00"));
        item.setPedido(pedido);
        item.setProduto(produto);

        ItemPedido salvo = repositorio.save(item);

        assertNotNull(salvo.getId());
        List<ItemPedido> itens = repositorio.findByPedidoId(1L);
        assertEquals(3, itens.size());
        assertTrue(itens.stream().anyMatch(i -> i.getId().equals(salvo.getId())));
    }

    @Test
    void deveAtualizarQuantidadeDoItem() {
        ItemPedido item = new ItemPedido();
        item.setQuantidade(1);
        item.setValorUnitario(new BigDecimal("10.00"));
        ItemPedido salvo = repositorio.save(item);

        salvo.setQuantidade(5);
        repositorio.save(salvo);

        ItemPedido encontrado = repositorio.findById(salvo.getId()).orElseThrow();
        assertEquals(5, encontrado.getQuantidade());
    }

    @Test
    void deveRemoverItemDoPedido() {
        ItemPedido item = new ItemPedido();
        item.setQuantidade(1);
        item.setValorUnitario(new BigDecimal("10.00"));
        ItemPedido salvo = repositorio.save(item);

        repositorio.deleteById(salvo.getId());

        assertTrue(repositorio.findById(salvo.getId()).isEmpty());
    }
}
