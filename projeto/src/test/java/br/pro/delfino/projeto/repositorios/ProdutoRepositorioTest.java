package br.pro.delfino.projeto.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import br.pro.delfino.projeto.entidades.Categoria;
import br.pro.delfino.projeto.entidades.Produto;

@SpringBootTest
@Transactional
class ProdutoRepositorioTest {

    @Autowired
    private ProdutoRepositorio repositorio;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Test
    void deveEncontrarProdutoPorNomeExatoIgnorandoMaiusculasMinusculas() {
        Optional<Produto> produto = repositorio.findByNomeIgnoreCase("NOTEBOOK DELL");

        assertTrue(produto.isPresent());
        assertEquals("Notebook Dell", produto.get().getNome());
    }

    @Test
    void deveBuscarProdutosPeloPedaçoDoNome() {
        List<Produto> produtos = repositorio.findByNomeContainingIgnoreCase("mec");

        assertEquals(1, produtos.size());
        assertEquals("Teclado Mecânico", produtos.get(0).getNome());
    }

    @Test
    void deveBuscarProdutosDaCategoria() {
        List<Produto> produtos = repositorio.findByCategoriaId(2L);

        assertEquals(2, produtos.size());
    }

    @Test
    void deveBuscarProdutosNaFaixaDePreco() {
        List<Produto> produtos = repositorio.findByPrecoBetween(
                new BigDecimal("100.00"), new BigDecimal("400.00"));

        assertEquals(3, produtos.size());
    }

    @Test
    void deveBuscarProdutosComEstoqueBaixo() {
        List<Produto> produtos = repositorio.findByEstoqueLessThan(30);

        assertEquals(2, produtos.size());
    }

    @Test
    void deveSalvarProdutoNovo() {
        Categoria categoria = categoriaRepositorio.findById(1L).orElseThrow();

        Produto produto = new Produto();
        produto.setNome("Webcam Full HD");
        produto.setDescricao("Webcam 1080p");
        produto.setPreco(new BigDecimal("200.00"));
        produto.setEstoque(15);
        produto.setCategoria(categoria);

        Produto salvo = repositorio.save(produto);

        assertNotNull(salvo.getId());
        assertTrue(repositorio.findById(salvo.getId()).isPresent());
    }

    @Test
    void deveAtualizarPrecoDoProduto() {
        Produto produto = new Produto();
        produto.setNome("Fone de Ouvido");
        produto.setPreco(new BigDecimal("80.00"));
        produto.setEstoque(10);
        Produto salvo = repositorio.save(produto);

        salvo.setPreco(new BigDecimal("90.00"));
        repositorio.save(salvo);

        Optional<Produto> encontrado = repositorio.findById(salvo.getId());
        assertTrue(encontrado.isPresent());
        assertEquals(new BigDecimal("90.00"), encontrado.get().getPreco());
    }

    @Test
    void deveRemoverProduto() {
        Produto produto = new Produto();
        produto.setNome("Produto Temporário");
        Produto salvo = repositorio.save(produto);

        repositorio.deleteById(salvo.getId());

        assertTrue(repositorio.findById(salvo.getId()).isEmpty());
    }
}
