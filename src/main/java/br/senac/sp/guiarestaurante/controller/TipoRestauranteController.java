package br.senac.sp.guiarestaurante.controller;

import br.senac.sp.guiarestaurante.model.TipoRestaurante;
import br.senac.sp.guiarestaurante.repository.TipoRestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tiporestaurante")
public class TipoRestauranteController {

    @Autowired //injeção de dependencias
    private TipoRestauranteRepository tipoRestauranteRepository;

    @PostMapping
    public ResponseEntity<Object> criarTipo(@RequestBody TipoRestaurante tipo){
        try{
            //salvar no banco
            tipoRestauranteRepository.save(tipo);
            //retornar resposta
            return ResponseEntity.created(URI.create("/tiporestaurante/" + tipo.getId())).body(tipo);

        } catch (Exception e) {
            e.printStackTrace();
            String erro = e.getMessage();
            return new ResponseEntity<Object>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> alterarTipo(@RequestBody TipoRestaurante tipo, @PathVariable("id") Long id){
        try{
            //salvar no banco
            tipoRestauranteRepository.save(tipo);
            //retornar resposta
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            e.printStackTrace();
            String erro = e.getMessage();
            return new ResponseEntity<Object>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        tipoRestauranteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Iterable<TipoRestaurante>> listarTodos(){
        return ResponseEntity.ok(tipoRestauranteRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoRestaurante> buscarId(@PathVariable Long id){
        Optional<TipoRestaurante> tipo = tipoRestauranteRepository.findById(id);
        if (tipo.isPresent()){
            return ResponseEntity.ok(tipo.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
