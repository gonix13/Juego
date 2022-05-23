package paquete_juego;

public class Niveles {
	private final int incrementoEspecial;
	private final int umbralNivel;
	
	public Niveles(int iE, int uN){
		incrementoEspecial = iE;
		umbralNivel = uN;
	}
	
	public int actualizarNiveles(TablaJugadores tJ, TablaEquipos tE){
		int puntosEquipo;
		String mejorEquipo;
		byte encontrado;
		int maxPuntos;
		int codError = 0;  // valor que devolver (0 == sin incidencias)
		int numJugadores = tJ.obtenerNumJugadores();  // obtiene cantidad de jugadores de la tabla
		int numEquipos = tE.obtenerNumEquipos();  // obtiene cantidad de equipos de la tabla

		if (numJugadores * numEquipos == 0)
			codError = 1;  // 1 == al menos una tabla SIN entradas
		else{
			maxPuntos = getMaxPuntos(tE, numEquipos);
			// busca equipos con mayor cantidad de puntos (mejores equipos)
			for (int i=0; i<numEquipos; i++){
				puntosEquipo = tE.obtenerPuntos(i);
				if (puntosEquipo == maxPuntos){
					mejorEquipo = tE.obtenerNombre(i);
					// busca jugadores de mejor equipo
					encontrado = buscaJugadoresMejorEquipo(tJ, mejorEquipo, numJugadores);
					if (encontrado == 0)codError = 2;  // 2 == al menos un mejor equipo SIN deportistas
				}

			}
		}		
		return codError;
	}

	private byte buscaJugadoresMejorEquipo(TablaJugadores tJ, String mejorEquipo, int numJugadores) {
		byte encontrado = 0;
		String equipoJugador;
		for (int j = 0; j< numJugadores; j++){
			equipoJugador = tJ.obtenerEquipo(j);
			if (mejorEquipo.equals(equipoJugador)){
				encontrado = 1;
				actualizarNivel(tJ, j);
			}
		}
		return encontrado;
	}

	private void actualizarNivel(TablaJugadores tJ, int j) {
		int nivelJugador;
		boolean esPreJugador;
		nivelJugador = tJ.obtenerNivel(j);
		esPreJugador = tJ.obtenerEsPre(j);
		if (esPreJugador && nivelJugador < umbralNivel )
			tJ.actualizarNivel(j,incrementoEspecial);
		else
			tJ.actualizarNivel(j,1);
	}

	private int getMaxPuntos(TablaEquipos tE, int numEquipos) {
		int puntosEquipo;
		int maxPuntos = 0;
		// busca mayor cantidad de puntos entre equipos
		for (int i = 0; i< numEquipos; i++){
			puntosEquipo = tE.obtenerPuntos(i);  // puntos del equipo #i en la tabla
			if (puntosEquipo > maxPuntos) maxPuntos = puntosEquipo;
		}
		return maxPuntos;
	}
}
