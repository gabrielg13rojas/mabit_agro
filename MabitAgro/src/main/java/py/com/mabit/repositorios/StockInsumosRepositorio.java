package py.com.mabit.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import py.com.mabit.entidades.StockInsumos;

@Repository
public interface StockInsumosRepositorio extends JpaRepository<StockInsumos, Long> {
	List<StockInsumos> findByInsumoId(Long insumoId);
}
