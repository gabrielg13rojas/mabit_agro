package py.com.mabit.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import py.com.mabit.entidades.MovimientosAnimales;

@Repository
public interface MovimientoAnimalesRepositorio extends JpaRepository<MovimientosAnimales, Long>{

}
