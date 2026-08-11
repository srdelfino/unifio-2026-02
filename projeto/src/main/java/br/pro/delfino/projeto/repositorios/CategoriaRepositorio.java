package br.pro.delfino.projeto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import br.pro.delfino.projeto.entidades.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {
}
