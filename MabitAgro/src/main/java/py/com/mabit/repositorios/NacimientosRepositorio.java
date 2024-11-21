package py.com.mabit.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.mabit.entidades.Nacimientos;


public interface NacimientosRepositorio extends JpaRepository<Nacimientos, Long> {
List<Nacimientos> findByObservacionesIgnoreCaseContaining(String observaciones);
}
