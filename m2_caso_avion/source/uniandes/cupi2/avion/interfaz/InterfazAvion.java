/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 2.1
 *
 * Proyecto Cupi2
 * Ejercicio: Avión
 * Autor: Katalina Marcos - Febrero 2005
 * Autor: Pablo Barvo - 24-Ago-2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.avion.interfaz;

import java.awt.*;
import java.text.*;

import javax.swing.*;

import uniandes.cupi2.avion.mundo.*;

/**
 * Ventana principal del avión
 */
public class InterfazAvion extends JFrame
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Avión
     */
    private Avion avion;

    //-----------------------------------------------------------------
    // Atributos de interfaz
    //-----------------------------------------------------------------

    /**
     * Panel que contiene el avión
     */
    private PanelAvion panelAvion;

    /**
     * Panel de botones
     */
    private PanelBotonesAvion panelBotones;

    /**
     * Dialogo de nuevo pasajero
     */
    private DialogoAsignacion dAsignacion;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea la interfaz del avión <br>
     * <b>post: </b> Se crea el avión y se presenta en una gráfica su estado inicial.
     */
    public InterfazAvion( )
    {
        //Crea al avión
        avion = new Avion( );

        //Configura la interfaz
        setLayout( new BorderLayout( ) );

        //Panel de botones
        panelBotones = new PanelBotonesAvion( this );
        add( panelBotones, BorderLayout.NORTH );

        //Panel del avión
        panelAvion = new PanelAvion( avion );
        add( panelAvion, BorderLayout.CENTER );

        String titulo = "El Avión";
        setTitle( titulo );
        pack( );
        setResizable( false );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Procesa el registro un pasajero
     */
    public void registrarPasajero( )
    {
        dAsignacion = new DialogoAsignacion( this, avion );
        dAsignacion.setLocation( calculaPosicionCentral( this, dAsignacion ) );
        dAsignacion.setModal( true );
        dAsignacion.setVisible( true );
        actualizar( );
    }

    /**
     * Procesa la anulación del registro de un pasajero
     */
    public void anularPasajero( )
    {
        //Pregunta el numero de cédula
        int cedula;
        String sCedula = JOptionPane.showInputDialog( this, "Ingrese el número de cédula" );
        try
        {
            cedula = Integer.parseInt( sCedula );
        }
        catch( NumberFormatException e )
        {
            JOptionPane.showMessageDialog( this, "Error en el número de cédula.", "Anulación", JOptionPane.ERROR_MESSAGE );
            return;
        }

        Pasajero pasajero = new Pasajero( cedula, "no importa" );
        if( !avion.desasignarSilla( pasajero ) )
        {
            JOptionPane.showMessageDialog( this, "El pasajero no tenía silla asignada", "Anulación", JOptionPane.ERROR_MESSAGE );
            return;
        }

        actualizar( );
    }

    /**
     * Muestra el porcentaje de ocupación que tiene el avión
     */
    public void mostrarPorcentajeOcupacion( )
    {
        double porcentaje;
        porcentaje = avion.calcularPorcentajeOcupacion( );
        DecimalFormat df = ( DecimalFormat )NumberFormat.getInstance( );
        df.applyPattern( "###.##" );
        JOptionPane.showMessageDialog( this, "El porcentaje de ocupación es " + df.format( porcentaje ) + "%", "Ocupación del avión", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Procesa la búsqueda de un pasajero
     */
    public void buscarPasajero( )
    {
        // Pregunta el numero de cédula
        int cedula;
        String sCedula = JOptionPane.showInputDialog( this, "Ingrese el número de cédula" );
        try
        {
            cedula = Integer.parseInt( sCedula );
        }
        catch( NumberFormatException e )
        {
            JOptionPane.showMessageDialog( this, "Error en el número de cédula.", "Anulación", JOptionPane.ERROR_MESSAGE );
            return;
        }
        Pasajero pasajero = new Pasajero( cedula, "no importa" );

        Silla silla = avion.buscarPasajero( pasajero );

        if( silla != null )
        {
            VentanaDatosPasajero vDatos = new VentanaDatosPasajero( silla );
            vDatos.setLocation( calculaPosicionCentral( this, vDatos ) );
            vDatos.setVisible( true );

        }
        else
        {
            JOptionPane.showMessageDialog( this, "El pasajero no se encuentra registrado", "Búsqueda registro", JOptionPane.INFORMATION_MESSAGE );
            return;
        }
    }

    /**
     * Método 1 de extensión para el ejemplo
     */
    public void reqFuncOpcion1( )
    {
    	int nSillas ;
    	try { 
    		Object[] possibilities1 = { "Dar Resultados Específicos", "Resultados Totales" };
    		String opcion =( String )JOptionPane.showInputDialog( this, "Elija la clase",null, JOptionPane.QUESTION_MESSAGE, null, possibilities1, "Ventana Económica"); 
			if(opcion.equals("Dar Resultados Específicos") ) {
				Object[] accion= {"Sillas Económicas desocupadas en la Ventana ","Sillas Económicas desocupadas en el Pasillo "};
				String sector =( String )JOptionPane.showInputDialog( this, "Elija el Sector Interesado",null, JOptionPane.QUESTION_MESSAGE, null, accion, "Ventana Económica"); 
				if (sector.equals("Sillas Económicas desocupadas en la Ventana ")) {
					avion.contarSillasEconomicasDesocupadasVentana( );
				}else {
					if(sector.equals("Sillas Económicas desocupadas en el Pasillo")) {
						avion.contarSillasEconomicasDesocupadasPasillo();
					}
				}
			}else {
				if (opcion.equals("Resultados Totales") ) {
					String cSillas = JOptionPane.showInputDialog(this , "Ingrese el mínimo de Sillas a Buscar");
					nSillas = Integer.parseInt(cSillas); 
					avion.hayNumSillasEconomicasLibresPasilloVentana(nSillas);
				}else {
					JOptionPane.showMessageDialog( this, "Solicitud Incompleta", "Error", JOptionPane.ERROR_MESSAGE );
				}
			}
    		actualizar( );
    	}catch( NumberFormatException e ){
    		JOptionPane.showMessageDialog(  this, "Solicitud Incompleta", "Error", JOptionPane.ERROR_MESSAGE );
    	}
    }

    /**
     * Método 2 de extensión para el ejemplo
     */
    public void reqFuncOpcion2( )
    {
        try {
        	Object[] possibilities1 = { "Ventana Económica", "Ventana Ejecutiva" };
    		String opcion =( String )JOptionPane.showInputDialog( this, "Elija el Sector Interesado",null, JOptionPane.QUESTION_MESSAGE, null, possibilities1, "Ventana Económica"); 
        }catch(NumberFormatException e) {
        	
        }
    }

    /**
     * Repinta la gráfica del avión
     */
    public void actualizar( )
    {
        remove( panelAvion );

        //Panel del avión
        panelAvion = new PanelAvion( avion );
        add( panelAvion, BorderLayout.CENTER );
        validate( );
    }

    /**
     * Calcula el punto central entre dos componentes
     * @param componentePadre - Componente padre sobre el que se calcula el centro - componentePadre != null
     * @param componenteHijo - Componente hijo que se centra sobre el padre - componenteHijo != null
     * @return Punto central para ubicar al componente hijo
     */
    private Point calculaPosicionCentral( Component componentePadre, Component componenteHijo )
    {

        //Centra la ventana y asegura que no sea mayor que la resolución actual
        Dimension tamanhoPantalla = Toolkit.getDefaultToolkit( ).getScreenSize( );
        int maxY = tamanhoPantalla.height;
        int minY = 0;

        //Tamaño de la resolución de la pantalla
        Dimension tamanhoPadre = componentePadre.getSize( );
        Point locacionPadre = componentePadre.getLocation( );
        Dimension tamanhoHijo = componenteHijo.getSize( );
        int x = ( tamanhoPadre.width - tamanhoHijo.width ) / 2 + locacionPadre.x;
        int y = ( tamanhoPadre.height - tamanhoHijo.height ) / 2 + locacionPadre.y;

        // Ajuste para abajo
        if( y + tamanhoHijo.height > maxY )
        {
            y = maxY - tamanhoHijo.height;
        }

        // Ajuste para arriba
        if( y < minY )
        {
            y = 0;
        }
        return new Point( x, y );
    }

    /**
     * Método principal de ejecución
     * @param args - argumentos de ejecución - no requiere.
     */
    public static void main( String[] args )
    {
        InterfazAvion interfaz = new InterfazAvion( );
        interfaz.setVisible( true );
    }
}