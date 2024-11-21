package py.com.mabit.entidades;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import py.com.mabit.enums.Comprobantes;

@Entity
public class LotesFacturas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private LocalDate vigenciaDesde;
	@Column(nullable = false)
	private LocalDate vigenciaHasta;
	@Column(nullable = false)
	private String timbrado;
	@Column(nullable = false)
	private String establecimiento;
	@Column(nullable = false)
	private String puntoExpedicion;
	@Column(nullable = false)
	private Integer desdeNumero;
	@Column(nullable = false)
	private Integer hastaNumero;
	@Column(nullable = false)
	private Integer longitud;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Comprobantes comprobante;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getVigenciaDesde() {
		return vigenciaDesde;
	}
	public void setVigenciaDesde(LocalDate vigenciaDesde) {
		this.vigenciaDesde = vigenciaDesde;
	}
	public LocalDate getVigenciaHasta() {
		return vigenciaHasta;
	}
	public void setVigenciaHasta(LocalDate vigenciaHasta) {
		this.vigenciaHasta = vigenciaHasta;
	}
	public String getTimbrado() {
		return timbrado;
	}
	public void setTimbrado(String timbrado) {
		this.timbrado = timbrado;
	}
	public String getEstablecimiento() {
		return establecimiento;
	}
	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}
	public String getPuntoExpedicion() {
		return puntoExpedicion;
	}
	public void setPuntoExpedicion(String puntoExpedicion) {
		this.puntoExpedicion = puntoExpedicion;
	}
	public Integer getDesdeNumero() {
		return desdeNumero;
	}
	public void setDesdeNumero(Integer desdeNumero) {
		this.desdeNumero = desdeNumero;
	}
	public Integer getHastaNumero() {
		return hastaNumero;
	}
	public void setHastaNumero(Integer hastaNumero) {
		this.hastaNumero = hastaNumero;
	}
	public Integer getLongitud() {
		return longitud;
	}
	public void setLongitud(Integer longitud) {
		this.longitud = longitud;
	}
	public Comprobantes getComprobante() {
		return comprobante;
	}
	public void setComprobante(Comprobantes comprobante) {
		this.comprobante = comprobante;
	}
	
}
