package py.com.mabit.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import py.com.mabit.entidades.Ventas;
import java.time.LocalDate;


@Repository
public interface VentasRepositorio extends JpaRepository<Ventas, Long> {
	List<Ventas> findByCompradorDocumentoIgnoreCaseContainingOrCompradorNombreRazonSocialIgnoreCaseContaining(String documento,
			String nombreRazonSocial);
	List<Ventas> findByFechaBetween(LocalDate desde,LocalDate hasta);
}
