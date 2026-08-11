package br.pro.delfino.projeto.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import br.pro.delfino.projeto.entidades.Cliente;

@SpringBootTest
@Transactional
class ClienteRepositorioTest {

    @Autowired
    private ClienteRepositorio repositorio;

    @Test
    void deveEncontrarClientePorEmailIgnorandoMaiusculasMinusculas() {
        Optional<Cliente> cliente = repositorio.findByEmailIgnoreCase("ANA.SOUZA@EMAIL.COM");

        assertTrue(cliente.isPresent());
        assertEquals("Ana Souza", cliente.get().getNome());
    }

    @Test
    void deveBuscarClientesPeloPedaçoDoNome() {
        List<Cliente> clientes = repositorio.findByNomeContainingIgnoreCase("silva");

        assertEquals(1, clientes.size());
        assertEquals("João da Silva", clientes.get(0).getNome());
    }

    @Test
    void deveSalvarClienteNovo() {
        Cliente cliente = new Cliente();
        cliente.setNome("Fernanda Lima");
        cliente.setEmail("fernanda.lima@email.com");
        cliente.setTelefone("(11) 99999-8888");

        Cliente salvo = repositorio.save(cliente);

        assertNotNull(salvo.getId());
        assertTrue(repositorio.findByEmailIgnoreCase("FERNANDA.LIMA@EMAIL.COM").isPresent());
    }

    @Test
    void deveAtualizarTelefoneDoCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Antigo");
        cliente.setEmail("antigo@email.com");
        Cliente salvo = repositorio.save(cliente);

        salvo.setTelefone("(21) 98888-7777");
        repositorio.save(salvo);

        Optional<Cliente> encontrado = repositorio.findById(salvo.getId());
        assertTrue(encontrado.isPresent());
        assertEquals("(21) 98888-7777", encontrado.get().getTelefone());
    }

    @Test
    void deveRemoverCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Temporário");
        cliente.setEmail("temporario@email.com");
        Cliente salvo = repositorio.save(cliente);

        repositorio.deleteById(salvo.getId());

        assertTrue(repositorio.findById(salvo.getId()).isEmpty());
    }
}
