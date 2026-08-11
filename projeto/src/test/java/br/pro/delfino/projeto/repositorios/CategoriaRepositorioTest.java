package br.pro.delfino.projeto.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import br.pro.delfino.projeto.entidades.Categoria;

@SpringBootTest
@Transactional
class CategoriaRepositorioTest {

    @Autowired
    private CategoriaRepositorio repositorio;

    @Test
    void deveEncontrarCategoriaIgnorandoMaiusculasMinusculas() {
        Optional<Categoria> categoria = repositorio.findByNomeIgnoreCase("informática");

        assertTrue(categoria.isPresent());
        assertEquals("Informática", categoria.get().getNome());
    }

    @Test
    void deveSalvarCategoriaNova() {
        Categoria categoria = new Categoria();
        categoria.setNome("Cozinha");
        categoria.setDescricao("Utensílios e eletrodomésticos");

        Categoria salva = repositorio.save(categoria);

        assertNotNull(salva.getId());
        assertTrue(repositorio.findById(salva.getId()).isPresent());
    }

    @Test
    void deveAtualizarNomeDaCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNome("Antiga");
        Categoria salva = repositorio.save(categoria);

        salva.setNome("Nova");
        repositorio.save(salva);

        Optional<Categoria> encontrada = repositorio.findById(salva.getId());
        assertTrue(encontrada.isPresent());
        assertEquals("Nova", encontrada.get().getNome());
    }

    @Test
    void deveRemoverCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNome("Temporária");
        Categoria salva = repositorio.save(categoria);

        repositorio.deleteById(salva.getId());

        assertTrue(repositorio.findById(salva.getId()).isEmpty());
    }
}
