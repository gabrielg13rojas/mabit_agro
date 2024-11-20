package py.com.mabit.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import py.com.mabit.entidades.Lotes;



@Repository
public interface LotesRepositorio extends JpaRepository<Lotes, Long> {
	List<Lotes> findByNombreIgnoreCaseContainingOrCodigoIgnoreCaseContaining(String Nombre,String codigo);
}
