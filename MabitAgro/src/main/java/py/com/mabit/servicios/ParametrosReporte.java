package py.com.mabit.servicios;

import java.time.LocalDate;

public class ParametrosReporte {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String campo_orden;
    private String des_asc;
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getCampo_orden() {
		return campo_orden;
	}
	public void setCampo_orden(String campo_orden) {
		this.campo_orden = campo_orden;
	}
	public String getDes_asc() {
		return des_asc;
	}
	public void setDes_asc(String des_asc) {
		this.des_asc = des_asc;
	}
    
  
}