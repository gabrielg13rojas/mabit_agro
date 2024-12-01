package py.com.mabit.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import py.com.mabit.entidades.Mortandad;

@Repository
public interface MortandadRepositorio extends JpaRepository<Mortandad, Long>{

}
