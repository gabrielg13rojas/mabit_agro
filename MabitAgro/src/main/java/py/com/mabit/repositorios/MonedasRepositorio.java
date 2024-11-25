package py.com.mabit.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import py.com.mabit.entidades.Monedas;
import java.util.List;


@Repository
public interface MonedasRepositorio extends JpaRepository<Monedas, Long>{
	List<Monedas> findByDescripcionIgnoreCaseContaining(String descripcion);
}
