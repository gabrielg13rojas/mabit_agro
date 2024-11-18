package py.com.mabit.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import py.com.mabit.entidades.Alimentos;

@Repository
public interface AlimentosRepositorio extends JpaRepository<Alimentos, Long>{
	
	List<Alimentos> findByDescripcionIgnoreCaseContaining(String descripcion);

}
