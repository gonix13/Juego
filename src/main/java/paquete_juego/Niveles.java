package paquete_juego;

public class Niveles {
	private final int incremEsp;
	private final int umbralNivel;
	
	public Niveles(final int incremEsp,final int umbralNivel){
		this.incremEsp = incremEsp;
		this.umbralNivel = umbralNivel;
	}
	
	public int actualizarNiveles(final TablaJugadores tablaJugadores,final TablaEquipos tablaEquipos){
		int puntosEquipo;
		String mejorEquipo;
		byte encontrado;
		int maxPuntos;
		int codError = 0;  // valor que devolver (0 == sin incidencias)
		final int numJugadores = tablaJugadores.obtenerNumJugadores();  // obtiene cantidad de jugadores de la tabla
		final int numEquipos = tablaEquipos.obtenerNumEquipos();  // obtiene cantidad de equipos de la tabla

		if (numJugadores * numEquipos == 0) {
			codError = 1;  // 1 == al menos una tabla SIN entradas
		}
		else{
			maxPuntos = getMaxPuntos(tablaEquipos, numEquipos);
			// busca equipos con mayor cantidad de puntos (mejores equipos)
			for (int i=0; i<numEquipos; i++){
				puntosEquipo = tablaEquipos.obtenerPuntos(i);
				if (puntosEquipo == maxPuntos){
					mejorEquipo = tablaEquipos.obtenerNombre(i);
					// busca jugadores de mejor equipo
					encontrado = buscaJugadoresMejorEquipo(tablaJugadores, mejorEquipo, numJugadores);
					if (encontrado == 0){
						codError = 2;  // 2 == al menos un mejor equipo SIN deportistas
					}
				}

			}
		}		
		return codError;
	}

	private byte buscaJugadoresMejorEquipo(final TablaJugadores tablaJugadores, final String mejorEquipo, final int numJugadores) {
		byte encontrado = 0;
		String equipoJugador;
		for (int j = 0; j< numJugadores; j++){
			equipoJugador = tablaJugadores.obtenerEquipo(j);
			if (mejorEquipo.equals(equipoJugador)){
				encontrado = 1;
				actualizarNivel(tablaJugadores, j);
			}
		}
		return encontrado;
	}

	private void actualizarNivel(final TablaJugadores tablaJugadores, final int indice) {
		int nivelJugador;
		boolean esPreJugador;
		nivelJugador = tablaJugadores.obtenerNivel(indice);
		esPreJugador = tablaJugadores.obtenerEsPre(indice);
		if (esPreJugador && nivelJugador < umbralNivel ){
			tablaJugadores.actualizarNivel(indice, incremEsp);
		}
		else {
			tablaJugadores.actualizarNivel(indice, 1);
		}
	}

	private int getMaxPuntos(final TablaEquipos tablaEquipos, final int numEquipos) {
		int puntosEquipo;
		int maxPuntos = 0;
		// busca mayor cantidad de puntos entre equipos
		for (int i = 0; i< numEquipos; i++){
			puntosEquipo = tablaEquipos.obtenerPuntos(i);  // puntos del equipo #i en la tabla
			if (puntosEquipo > maxPuntos){
				maxPuntos = puntosEquipo;
			}
		}
		return maxPuntos;
	}
}
