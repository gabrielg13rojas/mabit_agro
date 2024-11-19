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
public class Alimentos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String descripcion;

	@ManyToOne
	@JoinColumn
	private Proveedores proveedor_habitual;

	@Column
	private Double stock;

	@ManyToOne
	@JoinColumn
	private UnidadesMedida unidad_medida;

	@Column
	private Double stockMinimo;

	@Column
	private BigDecimal precioUnitario;

	public Alimentos() {
		id = 0l;
		descripcion = "";
		proveedor_habitual = new Proveedores();
		proveedor_habitual.setId(1l);
		stock = 0d;
		unidad_medida = new UnidadesMedida();
		unidad_medida.setId(1l);
		stockMinimo = 0d;
		precioUnitario = BigDecimal.ZERO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Proveedores getProveedor_habitual() {
		return proveedor_habitual;
	}

	public void setProveedor_habitual(Proveedores proveedor_habitual) {
		this.proveedor_habitual = proveedor_habitual;
	}

	public Double getStock() {
		return stock;
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}

	public UnidadesMedida getUnidad_medida() {
		return unidad_medida;
	}

	public void setUnidad_medida(UnidadesMedida unidad_medida) {
		this.unidad_medida = unidad_medida;
	}

	public Double getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(Double stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

}
