public class Documento {
	
	private String tipo;
	private String numero;
	
	public Documento(String tipo, String numero) {
		super();
		this.tipo = tipo;
		this.numero = numero;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return tipo + ";" + numero;
	}
	
	

}