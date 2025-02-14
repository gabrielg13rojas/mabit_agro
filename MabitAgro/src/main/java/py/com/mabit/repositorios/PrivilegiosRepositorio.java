package py.com.mabit.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import py.com.mabit.entidades.Privilegios;

@Repository
public interface PrivilegiosRepositorio extends JpaRepository<Privilegios, Long> {
	List<Privilegios> findByDescripcionIgnoreCaseContaining(String descripcion);
}
