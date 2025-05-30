package br.senac.sp.guiarestaurante.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.senac.sp.guiarestaurante.model.TipoRestaurante;

public interface TipoRestauranteRepository extends CrudRepository<TipoRestaurante, Long> {
	@Query("select t from TipoRestaurante t where t.palavrasChave like %:p% order by t.nome")
	List<TipoRestaurante> buscarPorChave(@Param("p") String palavras);
}
