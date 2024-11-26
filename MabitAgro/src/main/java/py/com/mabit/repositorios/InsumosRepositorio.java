package py.com.mabit.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import py.com.mabit.entidades.Insumos;

@Repository
public interface InsumosRepositorio extends JpaRepository<Insumos, Long> {
    // Buscar empresas 
	List<Insumos> findByDescripcionIgnoreCaseContaining(String descripcion);
}
