package py.com.mabit.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import py.com.mabit.entidades.Empresas;

@Repository
public interface EmpresasRepositorio extends JpaRepository<Empresas, Long> {
    // Buscar empresa 
    Optional<Empresas> findByEmail(String email);
    
    // Buscar empresas 
    List<Empresas> findByNombreIgnoreCaseContaining(String nombre);
    List<Empresas> findByNombreIgnoreCaseContainingAndEmail(String nombre, String email);

}
