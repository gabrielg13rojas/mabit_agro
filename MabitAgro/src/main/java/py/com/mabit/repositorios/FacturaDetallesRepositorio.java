package py.com.mabit.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import py.com.mabit.entidades.Facturas;
import py.com.mabit.entidades.FacturaDetalle;


@Repository
public interface FacturaDetallesRepositorio extends JpaRepository<FacturaDetalle, Long> {
	List<FacturaDetalle> findByCabecera(Facturas cabecera);
}
