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
public class FacturaDetalle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Double cantidad;
	@Column(nullable = false)
	private String detalle;
	@Column(nullable = false)
	private BigDecimal precioUnitario;
	@Column(nullable = false)
	private BigDecimal subtotal;
	@Column(nullable = false)
	private BigDecimal descuento;
	@Column(nullable = false)
	private BigDecimal ganancia;
	@Column(nullable = false)
	private BigDecimal comision;
	@Column(nullable = false)
	private BigDecimal impuestoPorc;
	@Column(nullable = false)
	private BigDecimal impuestoMonto;

	@ManyToOne
	@JoinColumn
	private Facturas cabecera;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public BigDecimal getGanancia() {
		return ganancia;
	}

	public void setGanancia(BigDecimal ganancia) {
		this.ganancia = ganancia;
	}

	public BigDecimal getComision() {
		return comision;
	}

	public void setComision(BigDecimal comision) {
		this.comision = comision;
	}

	public BigDecimal getImpuestoPorc() {
		return impuestoPorc;
	}

	public void setImpuestoPorc(BigDecimal impuestoPorc) {
		this.impuestoPorc = impuestoPorc;
	}

	public BigDecimal getImpuestoMonto() {
		return impuestoMonto;
	}

	public void setImpuestoMonto(BigDecimal impuestoMonto) {
		this.impuestoMonto = impuestoMonto;
	}

	public Facturas getCabecera() {
		return cabecera;
	}

	public void setCabecera(Facturas cabecera) {
		this.cabecera = cabecera;
	}
}
