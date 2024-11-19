package py.com.mabit.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import py.com.mabit.entidades.UnidadesMedida;

@Repository
public interface UnidadesMedidasRepositorio extends JpaRepository<UnidadesMedida, Long>{

	List<UnidadesMedida> findByDescripcionIgnoreCaseContaining(String descripcion);
}
