package py.com.mabit.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class StockInsumos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private BigDecimal precioUnitario;
	
	@Column(nullable = false)
	private BigDecimal stock;
	
	@Column(nullable = false)
	private String lote;
	
	@Column(nullable = false)
	private LocalDate fecha;
	
	@ManyToOne
	@JoinColumn
	private Insumos insumo;
	
	
}
