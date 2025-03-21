package py.com.mabit.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.mabit.entidades.Trabajadores;

public interface TrabajadoresRepositorio extends JpaRepository<Trabajadores, Long> {
	List<Trabajadores> findByNombreApellidoIgnoreCaseContainingOrCedula(String nombreApellido, String cedula);
}
