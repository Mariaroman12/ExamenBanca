import java.util.InputMismatchException;
import java.util.Scanner;
import com.google.gson.Gson;

public class Main {
	
	private static Cuenta miC;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
        String respuesta;
        boolean bContinuarMenu = true;
        miC = new Cuenta("0000",0,"NADA");

        do {
        	
            System.out.println("Menú de Opciones");
            System.out.println("------------------------");
            System.out.println("A: Proporcionar datos de la Cuenta");
            System.out.println("B: Cargar un Cliente desde un fichero JSON");
            System.out.println("C: Realizar un ingreso efectivo");
            System.out.println("D: Realizar una retirada efectivo");
            System.out.println("E: Grabar cuenta a JSON");
            System.out.println("F: Cargar cuenta desde JSON");
            System.out.println("G: Exportar datos por pantalla y fichero");
            System.out.println("Cualquier otra cosa: Salir");

            respuesta = sc.nextLine();

            if (respuesta.compareToIgnoreCase("A") == 0) {
            		
            	System.out.println("Introduce el codigo de la Cuenta");
                String codigo = sc.nextLine();
                float saldo = leerFloat("Introduce el saldo de la Cuenta",sc);
                while (saldo<0) {
                	saldo = leerFloat("Introduce correctamente el saldo de la Cuenta",sc);
                }
                System.out.println("Introduce la fecha de la Cuenta");
                String fecha = sc.nextLine();
                miC = new Cuenta(codigo,saldo,fecha);
                System.out.println("¡Cuenta actualizada con éxito!");
            	
            } else if(respuesta.compareToIgnoreCase("B") == 0) {
                
            	Cliente c = loadCliente(sc);
            	System.out.println(c);
            	if (miC.asignarCliente(c)) {
            		System.out.println("¡Cliente actualizado con éxito!");
            	} else {
            		System.out.println("¡El cliente no ha podido actualizarse!");
            	}
            	
            		
            } else if (respuesta.compareToIgnoreCase("C") == 0) {
                	
            	float cantidad = leerFloat("Introduce la cantidad que deseas ingresar",sc);
            	System.out.println("Introduce la fecha");
                String fecha = sc.nextLine();
                if (miC.ingresarEfectivo(cantidad, fecha)) {
                	System.out.println("¡Ingreso efectuado con éxito!");
                } else {
                	System.out.println("¡El ingreso no ha podido realizarse!");
                }
                
            } else if (respuesta.compareToIgnoreCase("D") == 0) {
                	
            	float cantidad = leerFloat("Introduce la cantidad que deseas retirar",sc);
            	System.out.println("Introduce la fecha");
                String fecha = sc.nextLine();
                if (miC.retirarEfectivo(cantidad, fecha)) {
                	System.out.println("¡Retiro efectuado con éxito!");
                } else {
                	System.out.println("¡El retiro no ha podido realizarse!");
                }
                	
            } else if (respuesta.compareToIgnoreCase("E") == 0) {
                	
            	if (saveCuenta(sc)) {
                	System.out.println("Operación completada.");
                } else {
                	System.out.println("Operación cancelada.");
                }
                		
            } else if (respuesta.compareToIgnoreCase("F") == 0) {
                	
                if (loadCuenta(sc)) {
                	System.out.println("Operación completada.");
                } else {
                	System.out.println("Operación cancelada.");
                }
                	
            } else if (respuesta.compareToIgnoreCase("G") == 0) {
            	
            	System.out.println(miC);
            	System.out.println("Pulse ENTER para continuar");
            	respuesta = sc.nextLine();
            	mostrarCuenta(sc);
            	System.out.println("Operación completada.");
                		
            } else {
                	
                bContinuarMenu = false;
                	
            }
            
            if (bContinuarMenu) {
            	
            	System.out.println("Pulse ENTER para continuar");
        		respuesta = sc.nextLine();
            	
            }
            	
        } while (bContinuarMenu);
        
        sc.close();

	}
	
	public static float leerFloat(String s, Scanner sc) {
		boolean repetir;
		float n = 0;
		do {
			repetir = false;
			try {
				System.out.println(s);
				n = sc.nextFloat();
			} catch (InputMismatchException e) {
				System.out.println("Error al guardar el número");
				repetir = true;
			} finally {
				sc.nextLine();
			}
		} while (repetir);
		return n;
	}
	
	private static Cliente loadCliente(Scanner sc) {
		
		System.out.println("Introduzca el fichero para cargar un cliente");
		System.out.println("Introduce un ENTER para cancelar");
		String filename = sc.nextLine();
		if (!filename.isBlank()) {
			Gson gson = new Gson();
			String json = ControladorFichero.readText(filename+".json");
			return gson.fromJson(json,Cliente.class);
		} else {
			System.out.println("Operación cancelada.");
		}
		return null;
		
	}
	
	private static boolean saveCuenta(Scanner sc) {
		
		System.out.println("Introduzca el fichero para grabar la copia de seguridad");
		System.out.println("Introduce un ENTER para cancelar");
		String filename = sc.nextLine();
		if (!filename.isBlank()) {
			Gson gson = new Gson();
			String dataJson = gson.toJson(miC);
			ControladorFichero.writeText(filename+".json", dataJson, true);
			return true;
		}
		return false;
	}
	
	private static boolean loadCuenta(Scanner sc) {
		
		System.out.println("Introduzca el fichero para cargar la copia de seguridad");
		System.out.println("Introduce un ENTER para cancelar");
		String filename = sc.nextLine();
		if (!filename.isBlank()) {
			Gson gson = new Gson();
			//Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = ControladorFichero.readText(filename+".json");
			miC = gson.fromJson(json,Cuenta.class);
			return true;
		}
		return false;
		
	}
	
	private static void mostrarCuenta(Scanner sc) {
		
		System.out.println("Introduzca el fichero para mostrar la cuenta");
		String filename = sc.nextLine();
		
		String dataJson = miC.toString();
		ControladorFichero.writeText(filename+".txt", dataJson, true);
		
	}
	
	/*private static void guardarClienteComoJson(Cliente cliente, Scanner sc) {
        Gson gson = new Gson();
        String json = gson.toJson(cliente);

        System.out.println("Introduzca el nombre del archivo para guardar el cliente:");
        String filename = sc.nextLine();
        ControladorFichero.writeText(filename + ".json", json, false);
        System.out.println("Cliente guardado como JSON en " + filename + ".json");
    }
    
    *private static void mostrarClienteDesdeJson(Scanner sc) {
        System.out.println("Introduzca el fichero para cargar un cliente");
        System.out.println("Introduce un ENTER para cancelar");
        String filename = sc.nextLine();
        if (!filename.isBlank()) {
            Gson gson = new Gson();
            String json = ControladorFichero.readText(filename + ".json");
            Cliente cliente = gson.fromJson(json, Cliente.class);
            System.out.println(cliente);
        } else {
            System.out.println("Operación cancelada.");
        }
    }
    *
    */

}