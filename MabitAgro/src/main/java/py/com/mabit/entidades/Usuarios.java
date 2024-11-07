package py.com.mabit.entidades;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Usuarios {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String foto;

	@Column(nullable = false, unique = true)
	private String correo;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String contrasenha;

	@Column(nullable = false)
	private Boolean verificado;

	@Column(nullable = false)
	private Boolean bloqueado;

	@Column(nullable = false)
	private String codigoRecuperacion;

	@ManyToOne
	@JoinColumn
	private Privilegios privilegio;
	
	public Usuarios() {
		id = 0l;
		foto="";
		correo ="";
		nombre="";
		contrasenha="";
		codigoRecuperacion="";
		verificado=false;
		bloqueado=false;
		privilegio = new Privilegios();
		privilegio.setId(1l); 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasenha() {
		return contrasenha;
	}

	public void setContrasenha(String contrasenha) {
		this.contrasenha = new BCryptPasswordEncoder(12).encode(contrasenha);
	}

	public Boolean getVerificado() {
		return verificado;
	}

	public void setVerificado(Boolean verificado) {
		this.verificado = verificado;
	}

	public Boolean getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(Boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public Privilegios getPrivilegio() {
		return privilegio;
	}

	public void setPrivilegio(Privilegios privilegio) {
		this.privilegio = privilegio;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getCodigoRecuperacion() {
		return codigoRecuperacion;
	}

	public void setCodigoRecuperacion(String codigoRecuperacion) {
		this.codigoRecuperacion = codigoRecuperacion;
	}

}
