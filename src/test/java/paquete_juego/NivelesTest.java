package paquete_juego;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NivelesTest {
    private static Niveles niveles;
    @BeforeAll
    static void setUp(){
        niveles = new Niveles(26,26);
    }

    static Stream<Arguments> entradas(){
        return Stream.of(
                Arguments.of(new TablaJugadores("src/test/resources/jTest1.txt"),new TablaEquipos("src/test/resources/eTest1.txt"),1),
                Arguments.of(new TablaJugadores("src/test/resources/jTest2.txt"),new TablaEquipos("src/test/resources/eTest1.txt"),1),
                Arguments.of(new TablaJugadores("src/test/resources/jTest2.txt"),new TablaEquipos("src/test/resources/eTest2.txt"),2),
                Arguments.of(new TablaJugadores("src/test/resources/jTest3.txt"),new TablaEquipos("src/test/resources/eTest3.txt"),0));
    }

    @ParameterizedTest
    @MethodSource("entradas")
    void actualizarNiveles(TablaJugadores tJ, TablaEquipos tE, int res) {
        assertEquals(res,niveles.actualizarNiveles(tJ,tE));
    }

}