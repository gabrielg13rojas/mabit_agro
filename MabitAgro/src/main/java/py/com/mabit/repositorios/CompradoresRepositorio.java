package py.com.mabit.repositorios;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import py.com.mabit.entidades.Compradores;

@Repository
public interface CompradoresRepositorio extends JpaRepository<Compradores, Long>{
	
	List<Compradores> findBydocumentoIgnoreCaseContaining(String documento);

}
