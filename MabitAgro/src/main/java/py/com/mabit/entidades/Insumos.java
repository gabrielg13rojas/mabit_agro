package py.com.mabit.entidades;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Insumos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String codigo;
	
	@Column(nullable = false)
	private String imagen;
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getDetalles() {
		return detalles;
	}


	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}


	public Double getStockMinimo() {
		return stockMinimo;
	}


	public void setStockMinimo(Double stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	@Column(nullable = false)
	private String descripcion;
	
	@Column(nullable = false)
	private String detalles;
	
	@Column(nullable = false)
	private Double stockMinimo;
	
	@ManyToOne
	@JoinColumn
	private UnidadesMedida unidad_medida;
	
	
	public UnidadesMedida getUnidad_medida() {
		return unidad_medida;
	}


	public void setUnidad_medida(UnidadesMedida unidad_medida) {
		this.unidad_medida = unidad_medida;
	}


	public Insumos() {
		
	}

}
