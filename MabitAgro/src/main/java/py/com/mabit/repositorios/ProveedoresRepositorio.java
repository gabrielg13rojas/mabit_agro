package py.com.mabit.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import py.com.mabit.entidades.Proveedores;

@Repository
public interface ProveedoresRepositorio extends JpaRepository<Proveedores, Long>{
	Optional<Proveedores> findByEmail (String email);
	
	List<Proveedores> findByEmailIgnoreCaseContaining (String email);

}
