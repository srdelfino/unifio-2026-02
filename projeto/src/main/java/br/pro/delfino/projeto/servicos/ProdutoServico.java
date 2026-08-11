package br.pro.delfino.projeto.servicos;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import br.pro.delfino.projeto.entidades.Produto;
import br.pro.delfino.projeto.repositorios.ProdutoRepositorio;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProdutoServico {

    private final ProdutoRepositorio repositorio;

    public ProdutoServico(ProdutoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Produto> listar() {
        return repositorio.findAll();
    }

    public Produto buscarPorId(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Produto não encontrado."));
    }

    public Produto salvar(Produto produto) {

        validarProduto(produto);

        if (repositorio.findByNomeIgnoreCase(produto.getNome()).isPresent()) {
            throw new RuntimeException("Já existe um produto com esse nome.");
        }

        return repositorio.save(produto);
    }

    public Produto atualizar(Long id, Produto produto) {

        Produto existente = buscarPorId(id);

        validarProduto(produto);

        repositorio.findByNomeIgnoreCase(produto.getNome())
                .ifPresent(p -> {
                    if (!p.getId().equals(id)) {
                        throw new RuntimeException("Já existe um produto com esse nome.");
                    }
                });

        existente.setNome(produto.getNome());
        existente.setDescricao(produto.getDescricao());
        existente.setPreco(produto.getPreco());
        existente.setEstoque(produto.getEstoque());
        existente.setCategoria(produto.getCategoria());

        return repositorio.save(existente);
    }

    public void excluir(Long id) {

        Produto produto = buscarPorId(id);

        // Futuramente:
        // if (!produto.getItensPedido().isEmpty()) {
        //     throw new RuntimeException(
        //         "Não é possível excluir um produto que já foi vendido.");
        // }

        repositorio.delete(produto);
    }

    private void validarProduto(Produto produto) {

        if (produto.getNome() == null || produto.getNome().isBlank()) {
            throw new RuntimeException("Nome é obrigatório.");
        }

        if (produto.getPreco() == null) {
            throw new RuntimeException("Preço é obrigatório.");
        }

        if (produto.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Preço deve ser maior que zero.");
        }

        if (produto.getEstoque() == null) {
            throw new RuntimeException("Estoque é obrigatório.");
        }

        if (produto.getEstoque() < 0) {
            throw new RuntimeException("Estoque não pode ser negativo.");
        }

        if (produto.getCategoria() == null) {
            throw new RuntimeException("Categoria é obrigatória.");
        }
    }
}
