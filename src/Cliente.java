public class Cliente {
	
	private String nombre;
	private String apellidos;
	private String mail;
	private Documento documento;
	
	public Cliente(String nombre, String apellidos, String mail, Documento documento) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.mail = mail;
		this.documento = documento;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	@Override
	public String toString() {
		return nombre + ";" + apellidos + ";" + mail + "\n" + documento;
	}

	
}