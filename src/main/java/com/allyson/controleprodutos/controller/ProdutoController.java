package com.allyson.controleprodutos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.allyson.controleprodutos.model.Produto;
import com.allyson.controleprodutos.repository.ProdutoRepository;

import java.util.List;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    // Página de cadastro
    @GetMapping("/produtos/cadastro")
    public String cadastro() {
        return "cadastro";
    }

    // Salvar produto
    @PostMapping("/produtos")
    public String salvar(@RequestParam String nome, @RequestParam int quantidade) {
        Produto p = new Produto();
        p.setNome(nome);
        p.setQuantidade(quantidade);
        repository.save(p);
        return "redirect:/produtos/cadastro?ok=1"; // volta para a tela de cadastro
    }


    // Listar todos os produtos
    @GetMapping("/produtos")
    public String listar(Model model) {
        List<Produto> produtos = repository.findAll();
        model.addAttribute("produtos", produtos);
        return "produtos"; // ← novo template para listagem
    }

    // Buscar produtos por nome
    @GetMapping("/produtos/buscar")
    public String buscar(@RequestParam("nome") String nome, Model model) {
        List<Produto> produtos = repository.findByNomeContainingIgnoreCase(nome);
        model.addAttribute("produtos", produtos);
        model.addAttribute("nomeBusca", nome);
        return "produtos";
    }
    
    @GetMapping("/produtos/editar/{id}")
    public String editarProduto(@PathVariable Long id, Model model) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto inválido: " + id));
       model.addAttribute("produto", produto);
        return "editar";
    }
    @PostMapping("/produtos/atualizar")
    public String atualizarProduto(@ModelAttribute Produto produto) {
        repository.save(produto);
        return "redirect:/produtos";
    }
    
    @GetMapping("/produtos/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/produtos";
    }


    }












/*package com.allyson.controleprodutos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.allyson.controleprodutos.model.Produto;
import com.allyson.controleprodutos.repository.ProdutoRepository;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping("/produtos")
    public String listar(Model model) {
        model.addAttribute("produtos", repository.findAll());
        return "cadastro";
    }

    @PostMapping("/produtos")
    public String salvar(@RequestParam String nome, @RequestParam int quantidade) {
        Produto p = new Produto();
        p.setNome(nome);
        p.setQuantidade(quantidade);
        repository.save(p);
        return "redirect:/produtos?ok=1";
    }
}*/



