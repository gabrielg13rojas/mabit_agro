package py.com.mabit.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import py.com.mabit.entidades.StockInsumos;

@Repository
public interface StockInsumosRepositorio extends JpaRepository<StockInsumos, Long> {
    // Buscar StockInsumos por descripción (insensible a mayúsculas/minúsculas)
	List<StockInsumos> findByInsumo_DescripcionIgnoreCaseContaining(String descripcion);
}
