package br.pro.delfino.projeto.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import br.pro.delfino.projeto.entidades.Cliente;
import br.pro.delfino.projeto.entidades.Pagamento;
import br.pro.delfino.projeto.entidades.Pedido;
import br.pro.delfino.projeto.enums.StatusPagamento;
import br.pro.delfino.projeto.enums.StatusPedido;
import br.pro.delfino.projeto.enums.TipoPagamento;

@SpringBootTest
@Transactional
class PagamentoRepositorioTest {

    @Autowired
    private PagamentoRepositorio repositorio;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Test
    void deveBuscarPagamentoDoPedido() {
        Optional<Pagamento> pagamento = repositorio.findByPedidoId(1L);

        assertTrue(pagamento.isPresent());
        assertEquals(TipoPagamento.PIX, pagamento.get().getTipo());
    }

    @Test
    void deveBuscarPagamentosPorStatus() {
        List<Pagamento> pagamentos = repositorio.findByStatus(StatusPagamento.PAGO);

        assertEquals(3, pagamentos.size());
    }

    @Test
    void deveBuscarPagamentosPorTipo() {
        List<Pagamento> pagamentos = repositorio.findByTipo(TipoPagamento.PIX);

        assertEquals(2, pagamentos.size());
    }

    private Pedido salvarPedidoNovo() {
        Cliente cliente = clienteRepositorio.findById(1L).orElseThrow();

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus(StatusPedido.PENDENTE);
        pedido.setCliente(cliente);
        return pedidoRepositorio.save(pedido);
    }

    @Test
    void deveSalvarPagamentoNovo() {
        // Todo pedido do seed já tem pagamento (1:1), então usamos um pedido novo.
        Pedido pedidoSalvo = salvarPedidoNovo();

        Pagamento pagamento = new Pagamento();
        pagamento.setValor(new BigDecimal("100.00"));
        pagamento.setData(LocalDateTime.now());
        pagamento.setStatus(StatusPagamento.PENDENTE);
        pagamento.setTipo(TipoPagamento.BOLETO);
        pagamento.setPedido(pedidoSalvo);

        Pagamento salvo = repositorio.save(pagamento);

        assertNotNull(salvo.getId());
        Optional<Pagamento> encontrado = repositorio.findByPedidoId(pedidoSalvo.getId());
        assertTrue(encontrado.isPresent());
        assertEquals(TipoPagamento.BOLETO, encontrado.get().getTipo());
    }

    @Test
    void deveAtualizarStatusDoPagamento() {
        Pedido pedidoSalvo = salvarPedidoNovo();

        Pagamento pagamento = new Pagamento();
        pagamento.setValor(new BigDecimal("100.00"));
        pagamento.setData(LocalDateTime.now());
        pagamento.setStatus(StatusPagamento.PENDENTE);
        pagamento.setTipo(TipoPagamento.PIX);
        pagamento.setPedido(pedidoSalvo);
        Pagamento salvo = repositorio.save(pagamento);

        salvo.setStatus(StatusPagamento.PAGO);
        repositorio.save(salvo);

        Optional<Pagamento> encontrado = repositorio.findByPedidoId(pedidoSalvo.getId());
        assertTrue(encontrado.isPresent());
        assertEquals(StatusPagamento.PAGO, encontrado.get().getStatus());
    }

    @Test
    void deveRemoverPagamento() {
        Pedido pedidoSalvo = salvarPedidoNovo();

        Pagamento pagamento = new Pagamento();
        pagamento.setValor(new BigDecimal("100.00"));
        pagamento.setData(LocalDateTime.now());
        pagamento.setStatus(StatusPagamento.PENDENTE);
        pagamento.setTipo(TipoPagamento.PIX);
        pagamento.setPedido(pedidoSalvo);
        Pagamento salvo = repositorio.save(pagamento);

        repositorio.deleteById(salvo.getId());

        assertTrue(repositorio.findByPedidoId(pedidoSalvo.getId()).isEmpty());
    }
}
