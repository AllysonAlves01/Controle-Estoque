package com.allyson.controleprodutos.controller;

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
}


/*package com.allyson.controleprodutos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.allyson.controleprodutos.model.Produto;

@Controller
public class ProdutoController {

    @GetMapping("/produtos/novo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("produto", new Produto());
        return "cadastro"; // cadastro.html em src/main/resources/templates
    }
}
*/
