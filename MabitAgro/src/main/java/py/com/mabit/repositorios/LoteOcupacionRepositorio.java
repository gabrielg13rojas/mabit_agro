package py.com.mabit.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import py.com.mabit.entidades.LotesOcupacion;

@Repository
public interface LoteOcupacionRepositorio extends JpaRepository<LotesOcupacion, Long> {

}
