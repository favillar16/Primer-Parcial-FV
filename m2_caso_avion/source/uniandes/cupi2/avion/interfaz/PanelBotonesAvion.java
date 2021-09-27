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
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Panel de botones de interacción con el programa del avión
 */
public class PanelBotonesAvion extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Opción registrar
     */
    public final static String REGISTRAR = "REGISTRAR_PASAJERO";

    /**
     * Opción anular
     */
    public final static String ANULAR = "ANULAR_PASAJERO";

    /**
     * Opción buscar
     */
    public final static String BUSCAR = "BUSCAR_PASAJERO";

    /**
     * Opción porcentaje de ocupación
     */
    public final static String PORCENTAJE = "PORCENTAJE_OCUPACION";

    /**
     * Opción extensión 1 = Muestra de Datos Obtenidos
     */
    private final static String OPCION_1 = "Muestra de Datos";

    /**
     * Opción extensión 2 = Búsqueda de Lugares
     */
    private final static String OPCION_2 = "Buscar Lugares";

    //-----------------------------------------------------------------
    // Atributos de interfaz
    //-----------------------------------------------------------------

    /**
     * Botón Registro de nuevo pasajero
     */
    private JButton bRegistro;

    /**
     * Botón Anular
     */
    private JButton bAnular;

    /**
     * Botón de búsqueda
     */
    private JButton bBuscarPasajero;

    /**
     * Botón porcentaje de ocupación
     */
    private JButton bPorcOcupacion;

    /**
     * Botón de extensión 1
     */
    private JButton botonMstrDts;

    /**
     * Botón de extensión 2
     */
    private JButton botonBsqLg;

    /**
     * Interfaz principal
     */
    private InterfazAvion ventana;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea el panel de botones <br>
     * <b <post: </b> se crean y alistan los botones de la interfaz.
     * @param laVentana - ventana o frame padre - laVentana != null
     */
    public PanelBotonesAvion( InterfazAvion laVentana )
    {
        //guarda la referencia a la ventana padre
        ventana = laVentana;

        //Configura propiedades visuales
        setLayout( new GridLayout( 2, 3, 8, 2 ) );
        setBorder( new EmptyBorder( 5, 5, 5, 5 ) );

        //Botón registrar
        bRegistro = new JButton( "Registrar Pasajero" );
        bRegistro.setActionCommand( REGISTRAR );
        bRegistro.addActionListener( this );
        bRegistro.setPreferredSize( new Dimension( 40, 10 ) );
        add( bRegistro );

        //Botón anular registro
        bAnular = new JButton( "Eliminar Pasajero" );
        bAnular.setActionCommand( ANULAR );
        bAnular.addActionListener( this );
        add( bAnular );

        //Botón buscar pasajero
        bBuscarPasajero = new JButton( "Buscar Pasajero" );
        bBuscarPasajero.setActionCommand( BUSCAR );
        bBuscarPasajero.addActionListener( this );
        add( bBuscarPasajero );

        //Botón porcentaje de ocupación
        bPorcOcupacion = new JButton( "Porcentaje Ocupación" );
        bPorcOcupacion.setActionCommand( PORCENTAJE );
        bPorcOcupacion.addActionListener( this );
        add( bPorcOcupacion );

        //Botones de opciones adicionales
        botonMstrDts = new JButton( "Muestra de Datos" );
        botonMstrDts.setActionCommand( OPCION_1 );
        botonMstrDts.addActionListener( this );
        add( botonMstrDts );
        botonBsqLg = new JButton( "Búsqueda de Lugares" );
        botonBsqLg.setActionCommand( OPCION_2 );
        botonBsqLg.addActionListener( this );
        add( botonBsqLg );
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Acciones de respuesta a los eventos de la interfaz
     * @param evento - evento generado por un elemento de la interfaz
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( comando.equals( REGISTRAR ) )
        {
            ventana.registrarPasajero( );
        }
        else if( comando.equals( ANULAR ) )
        {
            ventana.anularPasajero( );
        }
        else if( comando.equals( BUSCAR ) )
        {
            ventana.buscarPasajero( );
        }
        else if( comando.equals( PORCENTAJE ) )
        {
            ventana.mostrarPorcentajeOcupacion( );
        }
        else if( comando.equals( OPCION_1 ) )
        {
            ventana.reqFuncOpcion1( );
        }
        else if( comando.equals( OPCION_2 ) )
        {
            ventana.reqFuncOpcion2( );
        }
    }
}