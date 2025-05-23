package br.senac.sp.guiarestaurante.controller;

import br.senac.sp.guiarestaurante.model.TipoRestaurante;
import br.senac.sp.guiarestaurante.repository.TipoRestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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

    public TipoRestaurante getRestaurante(){
        TipoRestaurante tipoRestaurante = new TipoRestaurante();
        tipoRestaurante.setId(1L);
        tipoRestaurante.setNome("Sim");
        tipoRestaurante.setDescricao("Simsismisms");
        return tipoRestaurante;
    }

}
