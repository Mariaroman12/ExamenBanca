import java.util.ArrayList;
import java.util.List;

public class Cuenta {
	
	private String ccc;
	private float saldo;
	private String fechaApertura;
	private List<Operacion> misO;
	private Cliente clienteCuenta;
	
	public Cuenta(String ccc, float saldo, String fechaApertura) {
		super();
		this.ccc = ccc;
		this.saldo = saldo;
		this.fechaApertura = fechaApertura;
		misO = new ArrayList<>();
	}

	public String getCcc() {
		return ccc;
	}

	public void setCcc(String ccc) {
		this.ccc = ccc;
	}

	public float getSaldo() {
		return saldo;
	}


	public String getFechaApertura() {
		return fechaApertura;
	}


	public void setFechaApertura(String fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	
	public boolean isActiva() {
		return saldo>0;
	}
	
	public boolean retirarEfectivo(float cantidad, String fecha) {
		if (cantidad < 0 && saldo+cantidad >= 0 || saldo-cantidad <  0) {
			saldo -= cantidad;
			Operacion o = new Operacion(cantidad,fecha);
			addOperacion(o);
			return true;
		}
		return false;
	}

	public boolean ingresarEfectivo(float cantidad, String fecha) {
		if (cantidad > 0) {
			saldo += cantidad;
			Operacion o = new Operacion(cantidad,fecha);
			addOperacion(o);
			return true;
		}
		return false;
	}
	
	public boolean addOperacion(Operacion o) {
		if (o!=null) {
			while (true) {
				if(buscarOperacion(o.getCodigo()) == null) {
					misO.add(o);
					return true;
				} else {
					o.setCodigo((long) (Math.random()*1000000));
				}
			}
		}
		return false;
	}
	
	public Operacion buscarOperacion(long codigo) {
		for (Operacion o:misO) {
			if(codigo == o.getCodigo()) {
				return o;
			}
		}
		return null;
	}

	public Cliente getClienteCuenta() {
		return clienteCuenta;
	}

	public void setClienteCuenta(Cliente clienteCuenta) {
		this.clienteCuenta = clienteCuenta;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		String operaciones = "";
		for (Operacion o:misO) {
			operaciones += "\n" + o.getCodigo() + ";" + o.getCantidad() + ";" + o.getFlecha();
			System.out.println("a");
		}
		return ccc + ";" + saldo + ";" + fechaApertura + "\n" + clienteCuenta + operaciones;
	}
	
	public boolean asignarCliente(Cliente c) {
		if (c!=null) {
			this.setClienteCuenta(c);
			return true;
		}
		return false;
	}

}