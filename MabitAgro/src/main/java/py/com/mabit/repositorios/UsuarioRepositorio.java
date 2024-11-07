package py.com.mabit.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import py.com.mabit.entidades.Usuarios;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuarios, Long> {
	Optional<Usuarios> findByCorreo(String correo);
	
	List<Usuarios> findByCorreoIgnoreCaseContaining(String correo);
}