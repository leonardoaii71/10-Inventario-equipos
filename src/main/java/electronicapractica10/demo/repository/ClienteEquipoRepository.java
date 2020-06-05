package electronicapractica10.demo.repository;

import electronicapractica10.demo.model.ClienteEquipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface ClienteEquipoRepository extends JpaRepository<ClienteEquipo, Long> {

    @Query(value = "SELECT ALQ.ALQUILER_ID, CLI.NOMBRE, EQ.NOMBRE_EQUIPO, ALQ.FECHA_INICIO_ALQUILER, ABS(DATEDIFF('DAY',  ALQ.FECHA_INICIO_ALQUILER, ALQ.FECHA_FIN_ALQUILER )) AS DIAS " +
            "FROM ALQUILER ALQ INNER JOIN CLIENTE CLI ON CLI.ID = ALQ.CLIENTE_ID INNER JOIN EQUIPO EQ ON EQ.ID = ALQ.EQUIPO_ID " +
            "WHERE ALQ.ESTADO = 'Pendiente' ORDER BY ALQ.FECHA_FIN_ALQUILER ", nativeQuery = true)
    List<Object[]> equiposAlquiladosNoDevueltos();

    @Query(value = "SELECT  * FROM ALQUILER ALQ " +
            "INNER JOIN CLIENTE CLI ON CLI.id = ALQ.CLIENTE_ID INNER JOIN EQUIPO EQ ON EQ.ID = ALQ.EQUIPO_ID WHERE CLI.ID = :id", nativeQuery = true)
    List<ClienteEquipo> historialCliente(@Param("id") long id);

    @Query(value = "SELECT S.NOMBRE_SUB_FAMILIA, AVG(ABS(DATEDIFF('DAY', ALQ.FECHA_INICIO_ALQUILER, ALQ.FECHA_FIN_ALQUILER))) AS PROMDIAS " +
            "FROM ALQUILER ALQ INNER JOIN EQUIPO EQ ON EQ.ID = ALQ.EQUIPO_ID INNER JOIN SUB_FAMILIA S ON S.ID = EQ.SUBFAMILIA_ID " +
            "WHERE S.CATEGORIA_ID = :id GROUP BY S.NOMBRE_SUB_FAMILIA", nativeQuery = true)
    List<Object[]> promediosAlquiler(@Param("id") long id);

    //List<ClienteEquipo> findAllByEstadoOrderByFechaInicioAlquiler(Date)
}
