package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import businessDelegate.BusinessDelegate;
import dtos.*;
import enums.TipoEnvite;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.Color;


public class VentanaPrueba extends JFrame implements  ActionListener{

	private static final long serialVersionUID = 1L;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

	private DefaultTableModel listaMovimientos, listaCartas;
	private JButton btnActBaza, btnActCantos, btnActPuntaje, btnActCartas, btnCantar, btnTirar;
	private JLabel lblPuntos, lblPuntosPareja1, lblPuntosPareja2, lblCantos, lblCartas, lblBaza, lblPuntos1, lblPuntos2;
	
	private BusinessDelegate businessDelegate;
	private JugadorDTO yo;
	private PartidoDTO miPartido;

	private List<PuntajeParejaDTO> puntajes;
	private List<CartaJugadorDTO> cartasJugador;
	private JComboBox<String> comboBox;
	private JTable tableBaza;
	private JTable tableCartas;
	private JLabel label;
	private JLabel label_1;
	private JButton btnActualizarInformacionJuego;
	private JPanel panel;
	private JLabel lblJugadorActual;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	
	
	public VentanaPrueba(PartidoDTO partido, JugadorDTO jugador) throws RemoteException
	{
		setTitle(jugador.toString());
		
		businessDelegate = new BusinessDelegate();
		
		yo = jugador;
		miPartido = partido;
				
		initUI();
		this.setLocation(50 , 100);
		setVisible(true);
		this.pack();
	}

	private void initUI()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(5, 5));
		JPanel pnlNO = new JPanel();
		JPanel pnlSO = new JPanel(new GridLayout(3,2));
		JPanel pnlNE = new JPanel(new GridLayout(4,2));
		JPanel pnlSE = new JPanel(new GridLayout(3,2));
		btnActPuntaje = new JButton("Actualizar Puntaje");
		btnActCantos = new JButton("Actualizar Cantos");
		btnActPuntaje.addActionListener(this);
		btnActCantos.addActionListener(this);
		lblPuntos = new JLabel("Puntaje");
		lblPuntosPareja2 = new JLabel("Pareja 2");
		lblPuntos1 = new JLabel("0");
		lblPuntos2 = new JLabel("0");
		lblCartas = new JLabel("Cartas");
		lblCantos = new JLabel("Cantos");
		pnlNO.setLayout(new GridLayout(4, 1, 0, 0));
						
		lblBaza = new JLabel("Baza");
		pnlNO.add(lblBaza);
		
		tableBaza = new JTable();
		tableBaza.setShowHorizontalLines(false);
		listaMovimientos = new DefaultTableModel(
				new Object[][] {
					{null, null, null}
				},
				new String[] {
					"TipoMov", "Carta / Envite", "Fecha / Hora"
				}
		);

		tableBaza.setModel(listaMovimientos);

		tableBaza.getColumnModel().getColumn(1).setPreferredWidth(90);
		pnlNO.add(tableBaza);

		tableCartas = new JTable();

		listaCartas = new DefaultTableModel(
				new Object[][] {
					{null, null},
					{null, null},
					{null, null},
				},
				new String[] {
					"Carta", "Tirada"
				}
		);
		tableCartas.setModel(listaCartas);

		pnlNE.add(lblPuntos);
		pnlNE.add(new JLabel(""));
		lblPuntosPareja1 = new JLabel("Pareja 1");
		pnlNE.add(lblPuntosPareja1);
		pnlNE.add(lblPuntos1);
		pnlNE.add(lblPuntosPareja2);
		pnlNE.add(lblPuntos2);
		pnlNE.add(btnActPuntaje);
		
		pnlSO.add(lblCartas);
		
		pnlSE.add(lblCantos);
		pnlSE.add(new JLabel(""));
		
		comboBox = new JComboBox<String>();
		pnlSE.add(comboBox);
		
		label = new JLabel("");
		pnlSE.add(label);
		pnlSE.add(btnActCantos);
		
		getContentPane().add(pnlNO, BorderLayout.NORTH);
		
		btnActBaza = new JButton("Actualizar Baza");
		
		btnActBaza.addActionListener(this);
		pnlNO.add(btnActBaza);
		
		panel = new JPanel();
		pnlNO.add(panel);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		btnActualizarInformacionJuego = new JButton("Actualizar Informacion Juego");
		btnActualizarInformacionJuego.addActionListener(this);

		panel.add(btnActualizarInformacionJuego);
		
		lblNewLabel = new JLabel("Jugador Actual :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		lblJugadorActual = new JLabel("");
		lblJugadorActual.setBackground(Color.BLACK);
		panel.add(lblJugadorActual);

		getContentPane().add(pnlNE, BorderLayout.EAST);
		getContentPane().add(pnlSO, BorderLayout.WEST);
		
		label_1 = new JLabel("");
		pnlSO.add(label_1);
		
		pnlSO.add(tableCartas);
		btnTirar = new JButton("Tirar Carta");
		btnTirar.addActionListener(this);
		
		scrollPane = new JScrollPane();
		pnlSO.add(scrollPane);
		btnActCartas = new JButton("Actualizar Cartas");
		btnActCartas.addActionListener(this);
		pnlSO.add(btnActCartas);

		pnlSO.add(btnTirar);
		getContentPane().add(pnlSE, BorderLayout.SOUTH);
		btnCantar = new JButton("Cantar");
		btnCantar.addActionListener(this);

		pnlSE.add(btnCantar);
	}

	private void actualizarInformacionJuego() {
		btnActBaza.doClick();
		btnActCartas.doClick();
		btnActPuntaje.doClick();
		btnActCantos.doClick();
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(btnActPuntaje)) {
			try {
				puntajes = businessDelegate.obtenerPuntajeChico(miPartido, yo);
				lblPuntos1.setText(Integer.toString(puntajes.get(0).getPuntaje()));
				lblPuntos2.setText(Integer.toString(puntajes.get(1).getPuntaje()));
			} catch (RemoteException re) {
				System.out.println(re.getMessage());
			}
		} else

		if (e.getSource().equals(btnActCartas)) {
			cartasJugador = new ArrayList<CartaJugadorDTO>();
			try {
				cartasJugador = businessDelegate.obtenerCartasJugador(miPartido, yo);

				listaCartas.setRowCount(0);
				for (CartaJugadorDTO cartaJugador : cartasJugador) {
					Vector<String> aux = new Vector<String>();
					
					aux.add(cartaJugador.getCarta().toString());
					aux.add(cartaJugador.isTirada() ? "En Mesa" : "Sin Tirar");
					
					listaCartas.addRow(aux);
				}
				
			} catch (RemoteException e1) {
				System.out.println(e1.getMessage());
			}
		} else
		
		if (e.getSource().equals(btnActCantos)) {
			try {
				comboBox.removeAllItems();
				List<TipoEnvite> envitesDisponibles = businessDelegate.obtenerEnvitesDisponibles(miPartido, yo);
				for (TipoEnvite envite: envitesDisponibles) {					
					comboBox.addItem(envite.name());
				}
			} catch (RemoteException e1) {
				System.out.println(e1.getMessage());
			}
		} else

		if (e.getSource().equals(btnTirar)) {
			CartaTiradaDTO cartaTirada = new CartaTiradaDTO();
			for (CartaJugadorDTO cartaJugador: cartasJugador) {
				if (cartaJugador.getCarta().toString().equalsIgnoreCase((String) listaCartas.getValueAt(tableCartas.getSelectedRow(), 0))) {
					cartaTirada.setCartaJugador(cartaJugador);
					cartaTirada.setFechaHora(new Timestamp (Calendar.getInstance().getTimeInMillis()));
					break;
				}
			}
			try {
				businessDelegate.nuevoMovimientoPartido(miPartido, yo, cartaTirada);
				btnActCartas.doClick();
			} catch (RemoteException re) {
				System.out.println(re.getMessage());
			}
		} else

		if (e.getSource().equals(btnActualizarInformacionJuego)) {
			try {
				JugadorDTO jugadorActual = businessDelegate.obtenerJugadorActual(miPartido, yo);

				if (jugadorActual.getApodo().equals(yo.getApodo()))
					lblJugadorActual.setForeground(Color.red);
				else
					lblJugadorActual.setForeground(Color.black);

				lblJugadorActual.setText(jugadorActual.getApodo());

				actualizarInformacionJuego();
			} catch (RemoteException re) {
				System.out.println(re.getMessage());;
			}
		} else
		
		if (e.getSource().equals(btnCantar)) {
			EnviteDTO envite = new EnviteDTO();
			envite.setFechaHora(new Timestamp (Calendar.getInstance().getTimeInMillis()));
			envite.setTipoEnvite(TipoEnvite.obtenerPorTipo((String) comboBox.getSelectedItem()));			
			try {
				businessDelegate.nuevoMovimientoPartido(miPartido, yo, envite);
				actualizarInformacionJuego();
			} catch (RemoteException re) {
				System.out.println(re.getMessage());
			}
		} else

		if (e.getSource().equals(btnActBaza)) {
			try {
				List<MovimientoDTO> movimientos = businessDelegate.obtenerMovimientosUltimaBaza(miPartido, yo);

				// borro la tabla entera porque obtengo TODOS los movimientos
				listaMovimientos.setRowCount(0);
				for (MovimientoDTO movimiento : movimientos) {
					Vector<String> aux = new Vector<String>();

					//	CABECERA : "TipoMov", "Carta / Envite", "Fecha / Hora"

					if (movimiento instanceof EnviteDTO) {
						EnviteDTO envite = (EnviteDTO) movimiento;

						aux.add("Envite");
						aux.add(envite.getJugador().getApodo() + " canto " + envite.getTipoEnvite().name());
						aux.add(sdf.format(envite.getFechaHora()));
					} else {
						CartaTiradaDTO cartaTirada = (CartaTiradaDTO) movimiento;

						aux.add("CartaTirada");
						aux.add(cartaTirada.getCartaJugador().getJugador().getApodo() + " tiro " + cartaTirada.getCartaJugador().getCarta().toString());
						aux.add(sdf.format(cartaTirada.getFechaHora()));
					}

					listaMovimientos.addRow(aux);
				}

			} catch (RemoteException re) {
				System.out.println(re.getMessage());
			}
		}
	}
		
/*
		if(e.getSource().equals(btnSalir))
		{
			System.exit(0);
		}
		if(e.getSource().equals(btnBorrar))
		{
			try {
				String eliminar = (String) (listaDeAlumnos.getValueAt(table.getSelectedRow(),0));
				businessDelegate.eliminarAlumno(Integer.parseInt(eliminar));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					
		}
		if(e.getSource().equals(btnVolver))
		{
			MenuPrincipal menu = new MenuPrincipal();
			businessDelegate.eliminarObserver(this);
			setVisible(false);
		}
	}*/

}
