package py.com.mabit.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import py.com.mabit.entidades.Lotes;
import py.com.mabit.enums.EstadoLote;




@Repository
public interface LotesRepositorio extends JpaRepository<Lotes, Long> {
	List<Lotes> findByNombreIgnoreCaseContainingOrCodigoIgnoreCaseContaining(String Nombre,String codigo);
	List<Lotes> findByEstado(EstadoLote estado);	
}
