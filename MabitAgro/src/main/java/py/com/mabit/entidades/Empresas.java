package py.com.mabit.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Empresas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	public String logo;
	
	@Column(nullable = false, unique = true)
	public String nombre;
	
	@Column(nullable = false)
	public String telefono;
	
	@Column(nullable = false)
	public String direccion;
	
	@Column(nullable = false)
	public String email;
	
	@Column(nullable = false)
	public String atrib_email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAtrib_email() {
		return atrib_email;
	}

	public void setAtrib_email(String atrib_email) {
		this.atrib_email = atrib_email;
	}
}
