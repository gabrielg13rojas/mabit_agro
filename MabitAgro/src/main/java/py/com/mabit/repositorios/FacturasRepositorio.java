package py.com.mabit.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import py.com.mabit.entidades.Facturas;
import java.time.LocalDate;


@Repository
public interface FacturasRepositorio extends JpaRepository<Facturas, Long> {
	List<Facturas> findByCompradorDocumentoIgnoreCaseContainingOrCompradorNombreRazonSocialIgnoreCaseContaining(String documento,
			String nombreRazonSocial);
	List<Facturas> findByFechaBetween(LocalDate desde,LocalDate hasta);
}
