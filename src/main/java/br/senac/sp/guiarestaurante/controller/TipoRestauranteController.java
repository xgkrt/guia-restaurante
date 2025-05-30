package br.senac.sp.guiarestaurante.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senac.sp.guiarestaurante.model.TipoRestaurante;
import br.senac.sp.guiarestaurante.repository.TipoRestauranteRepository;

@RestController
@RequestMapping("/tiporestaurante")
public class TipoRestauranteController {
	@Autowired
	private TipoRestauranteRepository repository;

	@PostMapping
	public ResponseEntity<Object> criarTipo(@RequestBody TipoRestaurante tipo) {
		try {
			// salvar no banco
			repository.save(tipo);
			// retorna a resposta
			return ResponseEntity.created(URI.create("/tiporestaurante/" + tipo.getId())).body(tipo);
		} catch (Exception e) {
			e.printStackTrace();
			String erro = e.getMessage();
			return new ResponseEntity<Object>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> alterarTipo(@RequestBody TipoRestaurante tipo, @PathVariable("id") Long id) {
		try {
			// salvar no banco
			repository.save(tipo);
			// retorna a resposta
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			String erro = e.getMessage();
			return new ResponseEntity<Object>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<Iterable<TipoRestaurante>> listarTodos() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<TipoRestaurante> buscarPorId(@PathVariable("id") Long id) {
		Optional<TipoRestaurante> tipo = repository.findById(id);
		if (tipo.isPresent()) {
			return ResponseEntity.ok(tipo.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
