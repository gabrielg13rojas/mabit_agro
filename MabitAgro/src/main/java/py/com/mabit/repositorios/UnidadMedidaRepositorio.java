package py.com.mabit.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.mabit.entidades.UnidadesMedida;

public interface UnidadMedidaRepositorio extends JpaRepository<UnidadesMedida, Long> {
	List<UnidadesMedida> findByDescripcionIgnoreCaseContaining(String descripcion);
}
