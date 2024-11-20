package py.com.mabit.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import py.com.mabit.entidades.Animales;

@Repository
public interface AnimalesRepositorio extends JpaRepository<Animales, Long> {
	List<Animales> findByIdentificadorIgnoreCaseContainingOrAliasIgnoreCaseContaining(String identificador,
			String alias);
}