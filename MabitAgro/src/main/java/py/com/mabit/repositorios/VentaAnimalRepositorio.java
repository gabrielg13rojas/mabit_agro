package py.com.mabit.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import py.com.mabit.entidades.VentaAnimal;

@Repository
public interface VentaAnimalRepositorio extends JpaRepository<VentaAnimal, Long>{
	
}
