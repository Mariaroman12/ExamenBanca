public class Operacion {
	
	private long codigo;
	private float cantidad;
	private String flecha;
	
	public Operacion(float cantidad, String flecha) {
		super();
		this.codigo = (long) (Math.random()*1000000);
		this.cantidad = cantidad;
		this.flecha = flecha;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public String getFlecha() {
		return flecha;
	}

	public void setFlecha(String flecha) {
		this.flecha = flecha;
	}

	@Override
	public String toString() {
		return codigo + ";" + cantidad + ";" + flecha;
	}

}