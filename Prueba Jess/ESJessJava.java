/* Ejemplo de programa java que invoca la ejecuci�n del motor de reglas jess con el programa diet.clp*/
// De esta forma se podr�a realizar la entrada y salida de datos en Java y el razonamiento en Jess
import java.util.Iterator;

import jess.Deffacts;
import jess.Fact;
import jess.JessException;
import jess.RU;
import jess.Rete;
import jess.Value;

public class ESJessJava { 
	
	
	public static void main(String[] args) throws JessException {
		
		
			Rete miRete;		
			miRete = new Rete();

			String ficheroReglas = "diet.clp";	
			System.out.println("Cargando las reglas");
			try
			{
				Value v = miRete.batch(ficheroReglas);
				System.out.println("Value " + v);
			} catch (JessException je0) {
				System.out.println("Error de lectura en " + ficheroReglas);
				je0.printStackTrace();
			}
					
				
			// Crea un deffacts y a�ade un hecho siguiendo la template usuario definida en diet.clp
			// En una aplicaci�n real, los datos de usuario se leer�an en java y se
			// a�adir�an as� al programa jess
			
			Deffacts deffacts = new Deffacts("DatosJava", null, miRete);
			Fact f = new Fact("usuario", miRete);
			f.setSlotValue("edad", new Value(30, RU.INTEGER));
			f.setSlotValue("altura", new Value(150, RU.INTEGER));
			f.setSlotValue("peso", new Value(65, RU.INTEGER));
			f.setSlotValue("sexo", new Value("h", RU.SYMBOL));
			f.setSlotValue("actividadFisica", new Value(1.2, RU.FLOAT));
			deffacts.addFact(f);
			miRete.addDeffacts(deffacts);
			
			miRete.reset();
			
			// A continuaci�n se ejecuta el motor jess
			// Si el programa jess no tiene m�dulos basta con hacer run()
			
			miRete.run();

			/* Si el programa jess tiene m�dulos hay que poner el foco sucesivamente en cada uno de ellos, 
			   en el orden adecuado, y a continuaci�n un run() por cada m�dulo
			  
			   miRete.setFocus("m�dulo1");
			   miRete.run();
			   miRete.setFocus("m�dulo2");
			   miRete.run();
			   miRete.setFocus("m�dulo3");
			   miRete.run();
			   miRete.setFocus("m�dulo4");
			   miRete.run();
			 */

			
			// Se listan los hechos que hay en la memoria de trabajo
			
			listaHechos(miRete);
			extraeHechos(miRete);

			// Para parar el motor de reglas
			miRete.halt();
		
	}


	public static void listaHechos(Rete miRete) {
		// Obtiene e imprime la lista de hechos
		Iterator<Fact> iterador; 
		iterador = miRete.listFacts();
		
		System.out.println("Lista de hechos:");
		while (iterador.hasNext()) {
			System.out.println(iterador.next());
		}
	}

	public static void extraeHechos(Rete miRete) {
		// Ejemplo de c�mo podemos seleccionar s�lo los hechos de la template usuario
		// Y de esos hechos quedarnos s�lo con el slot edad e imprimir su valor
		Iterator<Fact> iterador; 
		iterador = miRete.listFacts();
		Fact f;
		Value ed;
		while (iterador.hasNext()) {
			f = iterador.next();
			if (f.getName().equals("MAIN::usuario"))
			{
				try {
					ed = f.getSlotValue("edad");
					System.out.println("edad " + ed);
				} catch (JessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} }                           
		}
	}
	

}
