package py.com.mabit.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import py.com.mabit.enums.CondicionVenta;

@Entity
public class Facturas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String numero;
	@Column(nullable = false)
	private LocalDate fecha;
	@Column(nullable = false)
	private LocalDate vencimiento;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private CondicionVenta condicion;
	@Column(nullable = false)
	private BigDecimal total;
	@Column(nullable = false)
	private BigDecimal descuentos = new BigDecimal(0);
	@Column(nullable = false)
	private Boolean activo;
	@Column(nullable = false)
	private Boolean pagado;
	@ManyToOne
	@JoinColumn
	private Compradores comprador;
	@ManyToOne
	@JoinColumn
	private Trabajadores trabajador;
	@ManyToOne
	@JoinColumn
	private LotesFacturas lote;
	@Column(nullable = false)
	private Boolean enviado;
	@Column(nullable = false)
	private String cdc;
	@Column(nullable = false)
	private String estado;
	@Column(nullable = false)
	private String enlace_qr;
	@Column(nullable = false)
	private String respuesta_servidor;
	@ManyToOne
	@JoinColumn
	private Empresas empresa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalDate getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(LocalDate vencimiento) {
		this.vencimiento = vencimiento;
	}

	public CondicionVenta getCondicion() {
		return condicion;
	}

	public void setCondicion(CondicionVenta condicion) {
		this.condicion = condicion;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getDescuentos() {
		return descuentos;
	}

	public void setDescuentos(BigDecimal descuentos) {
		this.descuentos = descuentos;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Boolean getPagado() {
		return pagado;
	}

	public void setPagado(Boolean pagado) {
		this.pagado = pagado;
	}

	public Compradores getComprador() {
		return comprador;
	}

	public void setComprador(Compradores comprador) {
		this.comprador = comprador;
	}

	public Trabajadores getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(Trabajadores trabajador) {
		this.trabajador = trabajador;
	}

	public Boolean getEnviado() {
		return enviado;
	}

	public void setEnviado(Boolean enviado) {
		this.enviado = enviado;
	}

	public String getCdc() {
		return cdc;
	}

	public void setCdc(String cdc) {
		this.cdc = cdc;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEnlace_qr() {
		return enlace_qr;
	}

	public void setEnlace_qr(String enlace_qr) {
		this.enlace_qr = enlace_qr;
	}

	public String getRespuesta_servidor() {
		return respuesta_servidor;
	}

	public void setRespuesta_servidor(String respuesta_servidor) {
		this.respuesta_servidor = respuesta_servidor;
	}

	public Empresas getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresas empresa) {
		this.empresa = empresa;
	}

}
