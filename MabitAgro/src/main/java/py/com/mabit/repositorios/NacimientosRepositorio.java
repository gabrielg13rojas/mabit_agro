package py.com.mabit.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.mabit.entidades.Nacimientos;

public interface NacimientosRepositorio extends JpaRepository<Nacimientos, Long> {
	Optional<Nacimientos> findByCriaId(Long criaId);
}
