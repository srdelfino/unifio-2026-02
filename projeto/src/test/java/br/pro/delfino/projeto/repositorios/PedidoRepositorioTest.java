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

import br.pro.delfino.projeto.entidades.Cliente;
import br.pro.delfino.projeto.entidades.Pedido;
import br.pro.delfino.projeto.enums.StatusPedido;

@SpringBootTest
@Transactional
class PedidoRepositorioTest {

    @Autowired
    private PedidoRepositorio repositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Test
    void deveBuscarPedidosDoCliente() {
        List<Pedido> pedidos = repositorio.findByClienteId(1L);

        assertEquals(2, pedidos.size());
    }

    @Test
    void deveBuscarPedidosPorStatus() {
        List<Pedido> pedidos = repositorio.findByStatus(StatusPedido.PAGO);

        assertEquals(1, pedidos.size());
    }

    @Test
    void deveBuscarPedidosNoPeriodo() {
        LocalDateTime inicio = LocalDateTime.of(2026, 8, 5, 0, 0, 0);
        LocalDateTime fim = LocalDateTime.of(2026, 8, 10, 23, 59, 59);

        List<Pedido> pedidos = repositorio.findByDataBetween(inicio, fim);

        assertEquals(3, pedidos.size());
    }

    @Test
    void deveBuscarPedidosDoClientePorStatus() {
        List<Pedido> pedidos = repositorio.findByClienteIdAndStatus(1L, StatusPedido.PAGO);

        assertEquals(1, pedidos.size());
        assertEquals(new BigDecimal("5350.00"), pedidos.get(0).getValorTotal());
    }

    @Test
    void deveSalvarPedidoNovo() {
        Cliente cliente = clienteRepositorio.findById(1L).orElseThrow();

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus(StatusPedido.PENDENTE);
        pedido.setValorTotal(new BigDecimal("150.00"));
        pedido.setCliente(cliente);

        Pedido salvo = repositorio.save(pedido);

        assertNotNull(salvo.getId());
        assertTrue(repositorio.findById(salvo.getId()).isPresent());
        assertEquals(3, repositorio.findByClienteId(1L).size());
    }

    @Test
    void deveAtualizarStatusDoPedido() {
        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus(StatusPedido.PENDENTE);
        Pedido salvo = repositorio.save(pedido);

        salvo.setStatus(StatusPedido.CANCELADO);
        repositorio.save(salvo);

        // Seed tem 1 CANCELADO (pedido 5); com a atualização passa a ter 2.
        List<Pedido> cancelados = repositorio.findByStatus(StatusPedido.CANCELADO);
        assertEquals(2, cancelados.size());
        assertTrue(cancelados.stream().anyMatch(p -> p.getId().equals(salvo.getId())));
    }

    @Test
    void deveRemoverPedido() {
        // Seed não é apagável (pedido 1 tem itens e pagamento vinculados):
        // o delete estouraria violação de FK. Então criamos um pedido "limpo".
        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus(StatusPedido.PENDENTE);
        Pedido salvo = repositorio.save(pedido);

        repositorio.deleteById(salvo.getId());

        assertTrue(repositorio.findById(salvo.getId()).isEmpty());
    }
}
