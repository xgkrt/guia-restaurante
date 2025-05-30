package br.senac.sp.guiarestaurante.controller;

import br.senac.sp.guiarestaurante.model.Avaliacao;
import br.senac.sp.guiarestaurante.model.Restaurante;
import br.senac.sp.guiarestaurante.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @PostMapping
    public ResponseEntity<Object> criarAvaliacao(@RequestBody Avaliacao ava){
        try{
            avaliacaoRepository.save(ava);
            return  ResponseEntity.created(URI.create("/avaliacao/" + ava.getId())).build();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> alterarTipo(@RequestBody Avaliacao ava, @PathVariable("id") Long id) {
        try {
            // salvar no banco
            avaliacaoRepository.save(ava);
            // retorna a resposta
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            String erro = e.getMessage();
            return new ResponseEntity<Object>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        avaliacaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Iterable<Avaliacao>> listarTodos() {
        return ResponseEntity.ok(avaliacaoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscarPorId(@PathVariable("id") Long id) {
        Optional<Avaliacao> tipo = avaliacaoRepository.findById(id);
        if (tipo.isPresent()) {
            return ResponseEntity.ok(tipo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
