package py.com.mabit.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import py.com.mabit.entidades.Proveedores;

public interface ProveedorRepositorio extends JpaRepository<Proveedores, Long> {
	List<Proveedores> findByNombreIgnoreCaseContaining(String nombre);
}
