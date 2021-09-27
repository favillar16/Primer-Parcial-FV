/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 * Licenciado bajo el esquema Academic Free License version 2.1
 *
 * Proyecto Cupi2
 * Ejercicio: Avi�n
 * Autor: Katalina Marcos - Febrero 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.avion.mundo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Avi�n de pasajeros
 */
public class Avion
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
    /**
     * Numero de sillas ejecutivas
     */
    public final static int SILLAS_EJECUTIVAS = 8;
    /**
     * Numero de sillas econ�micas
     */
    public final static int SILLAS_ECONOMICAS = 42;
    /**
     * Contador de Sillas en Ventana Econ�micas Ocupadas Ocupadas
     */
    int contventeje;
    int contventeco;
    int contpaseco;
    int contcenteco;
    int conttl;

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    /**
     * Sillas de la clase ejecutiva del avi�n
     */
    private Silla[] sillasEjecutivas;
    /**
     * Sillas de la clase econ�mica del avi�n
     */
    private Silla[] sillasEconomicas;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Construye al avi�n
     */
    public Avion( )
    {
        int ubicacion;

        // Crea las sillas ejecutivas
        sillasEjecutivas = new Silla[SILLAS_EJECUTIVAS];

        // crea las sillas econ�micas
        sillasEconomicas = new Silla[SILLAS_ECONOMICAS];

        sillasEjecutivas[ 0 ] = new Silla( 1, Silla.CLASE_EJECUTIVA, Silla.VENTANA );
        sillasEjecutivas[ 1 ] = new Silla( 2, Silla.CLASE_EJECUTIVA, Silla.PASILLO );
        sillasEjecutivas[ 2 ] = new Silla( 3, Silla.CLASE_EJECUTIVA, Silla.PASILLO );
        sillasEjecutivas[ 3 ] = new Silla( 4, Silla.CLASE_EJECUTIVA, Silla.VENTANA );
        sillasEjecutivas[ 4 ] = new Silla( 5, Silla.CLASE_EJECUTIVA, Silla.VENTANA );
        sillasEjecutivas[ 5 ] = new Silla( 6, Silla.CLASE_EJECUTIVA, Silla.PASILLO );
        sillasEjecutivas[ 6 ] = new Silla( 7, Silla.CLASE_EJECUTIVA, Silla.PASILLO );
        sillasEjecutivas[ 7 ] = new Silla( 8, Silla.CLASE_EJECUTIVA, Silla.VENTANA );

        for( int numSilla = 1 + SILLAS_EJECUTIVAS, j = 1; j <= SILLAS_ECONOMICAS; numSilla++, j++ )
        {
            //Sillas ventana
            if( j % 6 == 1 || j % 6 == 0 )
                ubicacion = Silla.VENTANA;
            //Sillas centrales
            else 
            	if( j % 6 == 2 || j % 6 == 5 )
                ubicacion = Silla.CENTRAL;
            //Sillas pasillo
            else
                ubicacion = Silla.PASILLO;

            sillasEconomicas[ j - 1 ] = new Silla( numSilla, Silla.CLASE_ECONOMICA, ubicacion );
        }
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Asigna la silla a un pasajero, seg�n sus preferencias de clase y ubicaci�n <br>
     * <b>post: </b> Si existe una silla con la clase y la ubicaci�n dada, el pasajero queda asignado en la primera de ellas seg�n el orden num�rico.
     * @param clase - clase elegida por el pasajero - clase pertenece {CLASE_EJECUTIVA,CLASE_ECONOMICA}
     * @param ubicacion - ubicaci�n elegida por el pasajero - si clase = CLASE_ECONOMICA entonces ubicaci�n pertenece {VENTANA, CENTRAL, PASILLO}, o si clase = CLASE_EJECUTIVA
     *        entonces ubicaci�n pertenece {VENTANA, PASILLO}
     * @param pasajero - pasajero a asignar - pasajero != null y no tiene silla en el avi�n
     * @return silla asignada - Si no se asigna una silla retorna null
     */
    public Silla asignarSilla( int clase, int ubicacion, Pasajero pasajero )
    {
        //busca una silla libre
        Silla silla = null;
        if( clase == Silla.CLASE_EJECUTIVA )
        {
            silla = buscarSillaEjecutivaLibre( ubicacion );
        }
        else if( clase == Silla.CLASE_ECONOMICA )
        {
            silla = buscarSillaEconomicaLibre( ubicacion );
        }
        if( silla != null )
        {
            silla.asignarAPasajero( pasajero );
        }
        return silla;
    }

    /**
     * Busca la siguiente silla ejecutiva que este libre y tenga la ubicaci�n indicada.
     * @param ubicacion - ubicaci�n en donde buscar la silla - ubicaci�n pertenece {VENTANA, PASILLO}
     * @return La silla libre encontrada. Si no encuentra retorna null.
     */
    public Silla buscarSillaEjecutivaLibre( int ubicacion )
    {
        boolean encontrado = false;
        Silla silla = null;
        for( int i = 0; i < SILLAS_EJECUTIVAS && !encontrado; i++ )
        {
            silla = sillasEjecutivas[ i ];
            if( ! ( silla.sillaAsignada( ) ) && silla.darUbicacion( ) == ubicacion )
            {
                encontrado = true;
            }
        }
        if( encontrado )
            return silla;
        else
            return null;
    }

    /**
     * Busca la siguiente silla econ�mica que este libre y tenga la ubicaci�n indicada.
     * @param ubicacion - ubicaci�n en donde buscar la silla - ubicaci�n pertenece {VENTANA, CENTRAL, PASILLO}
     * @return la silla encontrada libre. Si no encuentra retorna null
     */
    public Silla buscarSillaEconomicaLibre( int ubicacion )
    {
        boolean encontrado = false;
        Silla silla = null;
        for( int i = 0; i < SILLAS_ECONOMICAS && !encontrado; i++ )
        {
            silla = sillasEconomicas[ i ];
            if( ! ( silla.sillaAsignada( ) ) && silla.darUbicacion( ) == ubicacion )
            {
                encontrado = true;
            }
        }
        if( encontrado )
            return silla;
        else
            return null;
    }

    /**
     * Busca un pasajero en el avi�n
     * @param pasajero - pasajero a buscar - pasajero != null
     * @return silla en la que se encontr� el pasajero. Si no lo encuentra retorna null
     */
    public Silla buscarPasajero( Pasajero pasajero )
    {
        //Busca el pasajero en ejecutiva
        Silla silla = buscarPasajeroEjecutivo( pasajero );
        //Si no estaba en ejecutiva
        if( null == silla )
            //Busca en econ�mica
            silla = buscarPasajeroEconomico( pasajero );
        return silla;

    }

    /**
     * Busca un pasajero en las sillas ejecutivas
     * @param pasajero - pasajero a buscar - pasajero != null
     * @return silla en la que se encontr� el pasajero. Si no lo encuentra retorna null
     */
    public Silla buscarPasajeroEjecutivo( Pasajero pasajero )
    {
        boolean encontrado = false;
        Silla silla = null;
        for( int i = 0; i < SILLAS_EJECUTIVAS && !encontrado; i++ )
        {
            silla = sillasEjecutivas[ i ];
            if( silla.sillaAsignada( ) && silla.darPasajero( ).igualA( pasajero ) )
            {
                encontrado = true;
            }
        }
        if( encontrado )
            return silla;
        else
            return null;
    }

    /**
     * Busca un pasajero en las sillas econ�micas
     * @param pasajero - pasajero a buscar - pasajero != null
     * @return silla en la que se encontr� el pasajero. Si no lo encuentra retorna null
     */
    public Silla buscarPasajeroEconomico( Pasajero pasajero )
    {
        boolean encontrado = false;
        Silla silla = null;
        for( int i = 0; i < SILLAS_ECONOMICAS && !encontrado; i++ )
        {
            silla = sillasEconomicas[ i ];
            if( silla.sillaAsignada( ) && silla.darPasajero( ).igualA( pasajero ) )
            {
                encontrado = true;
            }
        }
        if( encontrado )
            return silla;
        else
            return null;
    }

    /**
     * Desasigna la silla de un pasajero <br>
     * <b>post: </b> Si se encuentra una silla con el pasajero, la silla quedara con su pasajero == null
     * @param pasajero - pasajero a retirar - pasajero != null
     * @return true si encontr� el pasajero y desasign� la silla, false en caso contrario
     */
    public boolean desasignarSilla( Pasajero pasajero )
    {
        //Busca el pasajero en el avi�n
        Silla silla = buscarPasajero( pasajero );
        //Si lo encuentra desasigna
        if( silla != null )
        {
            silla.desasignarSilla( );
            return true;
        }
        else
            return false;
    }

    /**
     * Retorna el n�mero de sillas ejecutivas ocupadas
     * @return numero de ejecutivas sillas ocupadas
     */
    public int contarSillasEjecutivasOcupadas( )
    {
        int contador = 0;
        for( int i = 0; i < SILLAS_EJECUTIVAS; i++ )
        {
            if( sillasEjecutivas[ i ].sillaAsignada( ) )
            {
                contador++;
                conttl++;
            }
            if( i % 6 == 1 || i % 6 == 0 ) {
            	contventeje++;
            	conttl++;
            }
            
        }
        return contador;
    }

    /**
     * Retorna el n�mero de sillas econ�micas ocupadas
     * @return numero de sillas econ�micas ocupadas
     */
    public int contarSillasEconomicasOcupadas( )
    {
        int contador = 0;
        for( int i = 0; i < SILLAS_ECONOMICAS; i++ )
        {
            if( sillasEconomicas[ i ].sillaAsignada( ) )
            {
                contador++;
                conttl++;
            }
            if( i % 6 == 1 || i % 6 == 0 ) {
            	contventeco++;
            	conttl++;
            }else {
            	if( i % 6 == 2 || i % 6 == 5 ) {
            		contcenteco++;
            		conttl++;
            }else {
            	contpaseco++;
            	conttl++;
            }
        }
        }
        return contador;
    }

    /**
     * Calcula el porcentaje de ocupaci�n del avi�n
     * @return porcentaje total de ocupaci�n
     */
    public double calcularPorcentajeOcupacion( )
    {
        double porcentaje;
        int totalSillas = SILLAS_ECONOMICAS + SILLAS_EJECUTIVAS;
        int sillasOcupadas = contarSillasEconomicasOcupadas( ) + contarSillasEjecutivasOcupadas( );
        porcentaje = ( double )sillasOcupadas / totalSillas * 100;
        return porcentaje;
    }
    /**
     *  
     * ventana y que se encuentran libres
     * @return - N�mero de sillas econ�micas libres y que est�n en la ventana
     */
    public int contarSillasEconomicasDesocupadasVentana()
    {
    	int ventdesc;
    	ventdesc= 14 - contventeco;
    	return ventdesc;
    }
    /**
     *  
     * pasillo y que se encuentran libres
     * @return - N�mero de sillas econ�micas libres y que est�n en el pasillo
     */
    public int contarSillasEconomicasDesocupadasPasillo()
    {	
    	int pasdesc;
    	pasdesc= 14 - contventeco;
    	return pasdesc;
    }
    /**
     *  
     * 		disponibles en el pasillo o en la ventana.
     * @param - nSillas - Minimo numero de sillas libres que se busca, ubicadas *en pasillo o ventana
     * @return - True si hay por lo menos nSillas libres en pasillo o ventana, *false en caso contrario
     */
    public boolean hayNumSillasEconomicasLibresPasilloVentana(int nSillas)
    {
    	int ocupadas;
    	ocupadas = 28 - (contpaseco+contventeco);
    	if (nSillas>ocupadas) {
    		return true;
    	}else {
    		return false;
    	}          
    }
    /**
     * TODO: Busca la primera silla econ�mica que este libre y tenga la ubicaci�n 
     * *indicada, revisando desde la parte de atr�s del avi�n
     * @param ubicacion - ubicaci�n en donde buscar la silla - ubicacion *pertenece {VENTANA, CENTRAL, PASILLO}
     * @return La silla encontrada libre. Si no encuentra retorna null
     */
    public Silla buscarSillaEconomicaLibreReves(int ubicacion)
    {	
    	boolean encontrado = false;
    	Silla silla = null;
    	for (int i = SILLAS_ECONOMICAS; i>SILLAS_EJECUTIVAS && !encontrado; i++) {
    		silla = sillasEconomicas[i];
    		if (!(silla.sillaAsignada()) && silla.darUbicacion() == ubicacion) {
    			encontrado = true ;
    		}
    	}
    	if (encontrado)
    		return silla;
    	else 
    	return null;
    }
    /**
     * TODO: Retorna una lista con todas las sillas vac�as del avi�n
     * @return - Lista con todas las sillas vac�as del avi�n
     */
    public ArrayList<Silla> darSillasVacias()
    {
    	ArrayList<Silla> sillasVacias = new ArrayList<Silla>();
			
    	for (int i=0;i < SILLAS_EJECUTIVAS;i++) {
    		if(sillasEjecutivas[i].sillaAsignada() == false) {
    			sillasVacias.add(sillasEjecutivas[i]);
    		}
		}
		for (int i=0;i < SILLAS_ECONOMICAS;i++) {
			if(sillasEconomicas[i].sillaAsignada() == false) {
    			sillasVacias.add(sillasEconomicas[i]);
    		}
		}
    	return sillasVacias;
    }
    /**
     *   TODO :Indica la cantidad total de sillas ocupadas en el avi�n.
     * @return - Total de sillas ocupadas en el avi�n
     */
    public int darNumTotalSillasOcupadas()
    {
        return conttl;
    }
    
    /**
     * 
     * @return - True si hay dos vac�as con la misma ubicaci�n, false de lo *contrario
     */
    public boolean hayDosSillasVaciasMismaUbicacion()
    {
    	boolean repeticion=false;
    	for(int i =0;i <SILLAS_ECONOMICAS-1&&!repeticion;i++){
    		for(int  j=i+1;j <SILLAS_ECONOMICAS-1&&!repeticion;j++){
    			if(!(sillasEconomicas[i].sillaAsignada())&&!(sillasEconomicas[j].sillaAsignada())){
    				if(sillasEconomicas[i].darUbicacion() == sillasEconomicas[j].darUbicacion()) {
    					repeticion=true;    					
    				}
    			}
    		}
    	}
    	for(int i =0;i <SILLAS_EJECUTIVAS-1&&!repeticion;i++){
    		for(int  j=i+1;j <SILLAS_EJECUTIVAS-1&&!repeticion;j++){
    			if(!(sillasEjecutivas[i].sillaAsignada())&&!(sillasEjecutivas[j].sillaAsignada())){
    				if(sillasEjecutivas[i].darUbicacion() == sillasEjecutivas[j].darUbicacion()) {
    					repeticion=true;    					
    				}
    			}
    		}
    	}
    	return repeticion;
    }
    /**
     *   
     * @return - Porcentaje de sillas econ�micas pares ocupadas
     */
    public double darPorcentajeSillasEconomicasOcupadasPares()
    {
    	int contador = 0;
    	for( int i = 0; i < SILLAS_ECONOMICAS; i++ )
        {
            if( sillasEconomicas[ i ].sillaAsignada( ) && i %2 ==0 )
            {
                contador++;
            }
        }
        double porcentaje = contador / 42* 100;
        return porcentaje;
    }

    /**
     * Retorna las sillas ejecutivas del avi�n
     * @return sillas ejecutivas
     */
    public Silla[] obtenerSillasEjecutivas( )
    {
        return sillasEjecutivas;
    }

    /**
     * Retorna las sillas econ�micas del avi�n
     * @return sillas econ�micas
     */
    public Silla[] obtenerSillasEconomicas( )
    {
        return sillasEconomicas;
    }

    /**
     * M�todo 1 de extensi�n al ejemplo
     * @return respuesta
     */
    public String metodo1( )
    {
        return "Respuesta1";
    }

    /**
     * M�todo 2 de extensi�n al ejemplo
     * @return respuesta
     */
    public String metodo2( )
    {
        return "respuesta 2";
    }
}