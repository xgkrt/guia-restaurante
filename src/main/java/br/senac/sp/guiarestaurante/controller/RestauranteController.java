package br.senac.sp.guiarestaurante.controller;

import br.senac.sp.guiarestaurante.model.Restaurante;
import br.senac.sp.guiarestaurante.model.TipoRestaurante;
import br.senac.sp.guiarestaurante.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {

    @Autowired
    private RestauranteRepository  restauranteRepository;

    @PostMapping
    public ResponseEntity<Object> criarRestaurante(@RequestBody Restaurante rest){
        try {

            restauranteRepository.save(rest);
            return ResponseEntity.created(URI.create("/restaurante/" + rest.getId())).body(rest);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> alterarTipo(@RequestBody Restaurante rest, @PathVariable("id") Long id) {
        try {
            // salvar no banco
            restauranteRepository.save(rest);
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
        restauranteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Iterable<Restaurante>> listarTodos() {
        return ResponseEntity.ok(restauranteRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscarPorId(@PathVariable("id") Long id) {
        Optional<Restaurante> tipo = restauranteRepository.findById(id);
        if (tipo.isPresent()) {
            return ResponseEntity.ok(tipo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
