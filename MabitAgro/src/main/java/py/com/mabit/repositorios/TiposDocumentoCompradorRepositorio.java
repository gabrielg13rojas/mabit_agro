package py.com.mabit.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import py.com.mabit.entidades.TiposDocumentoComprador;

public interface TiposDocumentoCompradorRepositorio extends JpaRepository<TiposDocumentoComprador, Long> {
	List<TiposDocumentoComprador> findByDescripcionIgnoreCaseContaining(String descripcion);

}
