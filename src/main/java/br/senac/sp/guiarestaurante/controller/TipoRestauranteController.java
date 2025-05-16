package br.senac.sp.guiarestaurante.controller;

import br.senac.sp.guiarestaurante.model.TipoRestaurante;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TipoRestauranteController {

    @GetMapping("/teste")
    public TipoRestaurante getRestaurante(){
        TipoRestaurante tipoRestaurante = new TipoRestaurante();
        tipoRestaurante.setId(1L);
        tipoRestaurante.setNome("Sim");
        tipoRestaurante.setDescricao("Simsismisms");
        return tipoRestaurante;
    }

}
