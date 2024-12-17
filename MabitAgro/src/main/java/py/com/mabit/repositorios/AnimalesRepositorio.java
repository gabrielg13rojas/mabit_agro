package py.com.mabit.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import py.com.mabit.entidades.Animales;
import py.com.mabit.enums.EstadoAnimal;
import py.com.mabit.enums.SexoAnimal;

@Repository
public interface AnimalesRepositorio extends JpaRepository<Animales, Long> {
	List<Animales> findByIdentificadorIgnoreCaseContainingOrAliasIgnoreCaseContaining(String identificador,
			String alias);

	@Query("SELECT a FROM Animales a WHERE a.sexo = :sexo AND a.estado IN (:estados)")
	List<Animales> findBySexoYEstado(@Param("sexo") SexoAnimal sexo, @Param("estados") List<EstadoAnimal> estados);
	List<Animales> findByEstadoOrEstado(EstadoAnimal vivo, EstadoAnimal muerto);

}