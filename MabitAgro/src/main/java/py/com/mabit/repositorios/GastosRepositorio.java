package py.com.mabit.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import py.com.mabit.entidades.Gastos;

@Repository
public interface GastosRepositorio extends JpaRepository<Gastos, Long>{
	
	List<Gastos> findByBeneficiarioIgnoreCaseContaining(String beneficiario);

}