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
import py.com.mabit.enums.EstadoGasto;
import py.com.mabit.enums.FormaPago;

@Entity
public class Gastos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private LocalDate vencimiento;
	@Column(nullable = false)
	private String tipoGasto;
	@Column(nullable = false)
	private BigDecimal monto;
	@Column(nullable = false)
	private String beneficiario;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private FormaPago formaPago;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EstadoGasto estado;
	@Column(nullable = false)
	private String observaciones;
	
	public Gastos() {
		id=0l;
		vencimiento=LocalDate.now();
		tipoGasto="";
		monto=BigDecimal.ZERO;
		beneficiario="";
		formaPago=FormaPago.EFECTIVO ;
		estado=EstadoGasto.PAGADO;
		observaciones="";
		
		
			
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(LocalDate vencimiento) {
		this.vencimiento = vencimiento;
	}

	public String getTipoGasto() {
		return tipoGasto;
	}

	public void setTipoGasto(String tipoGasto) {
		this.tipoGasto = tipoGasto;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	public FormaPago getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}

	public EstadoGasto getEstado() {
		return estado;
	}

	public void setEstado(EstadoGasto estado) {
		this.estado = estado;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}
