package py.com.mabit.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import py.com.mabit.entidades.Alimentacion;
import py.com.mabit.entidades.Sanitizacion;

@Repository
public interface SanitizacionRepositorio extends JpaRepository<Sanitizacion, Long>{

}
