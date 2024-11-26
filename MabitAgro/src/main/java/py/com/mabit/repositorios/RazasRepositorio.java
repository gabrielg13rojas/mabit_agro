package py.com.mabit.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import py.com.mabit.entidades.Razas;
import java.util.List;


@Repository
public interface RazasRepositorio  extends JpaRepository<Razas, Long>{
	List<Razas> findByDescripcionIgnoreCaseContaining(String descripcion);
}
