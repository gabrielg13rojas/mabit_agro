package py.com.mabit.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import py.com.mabit.entidades.Servicios;

@Repository
public interface ServiciosRepositorio extends JpaRepository<Servicios, Long> {
List<Servicios> findByDescripcionIgnoreCaseContaining(String descripcion);
}
