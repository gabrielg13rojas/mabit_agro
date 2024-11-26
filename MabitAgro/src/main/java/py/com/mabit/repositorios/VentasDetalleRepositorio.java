package py.com.mabit.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import py.com.mabit.entidades.Ventas;
import py.com.mabit.entidades.VentasDetalle;


@Repository
public interface VentasDetalleRepositorio extends JpaRepository<VentasDetalle, Long> {
	List<VentasDetalle> findByCabecera(Ventas cabecera);
}
