package com.upc.puntualito.services;

import com.upc.puntualito.dto.AsistenciaDTO;
import com.upc.puntualito.dto.AsistenciaDashboardDTO;
import com.upc.puntualito.dto.ReporteMensualDTO;
import com.upc.puntualito.entities.Asistencia;
import com.upc.puntualito.entities.Empleado;
import com.upc.puntualito.entities.HorarioTrabajo;
import com.upc.puntualito.entities.Sede;
import com.upc.puntualito.interfaces.IAsistenciaService;
import com.upc.puntualito.repositories.AsistenciaRepository;
import com.upc.puntualito.repositories.EmpleadoRepository;
import com.upc.puntualito.repositories.HorarioTrabajoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class AsistenciaService implements IAsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private HorarioTrabajoRepository horarioTrabajoRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public AsistenciaDTO registrarEntrada(AsistenciaDTO asistenciaDTO) {

        Long empleadoId = asistenciaDTO.getEmpleadoId();

        validarGeocercaEntrada(empleadoId, asistenciaDTO.getLatitudEntrada(), asistenciaDTO.getLongitudEntrada());

        AsistenciaDTO ultima = findTopByEmpleadoIdOrderByFechaRegistroDescHoraEntradaDesc(empleadoId);

        Asistencia asistencia = modelMapper.map(asistenciaDTO, Asistencia.class);

        asistencia.setHoraSalida(null);
        asistencia.setModificadoEn(null);
        asistencia.setModificadoPor(null);

        asistencia.setCreadoPor("sistema");
        asistencia.setCreadoEn(Instant.now());

        // CALCULAR Y GUARDAR EL ESTADO
        String estado = calcularEstadoAsistencia(empleadoId, asistenciaDTO.getHoraEntrada());
        asistencia.setEstado(estado);

        if (ultima == null) {
            asistenciaRepository.save(asistencia);
            return modelMapper.map(asistencia, AsistenciaDTO.class);
        }

        if (ultima.getHoraEntrada() != null && ultima.getHoraSalida() == null && asistenciaDTO.getHoraEntrada() != null) {
            throw new IllegalStateException("Ya tienes una entrada registrada a las " + ultima.getHoraEntrada());
        }

        if (ultima.getHoraSalida() != null && asistenciaDTO.getHoraSalida() != null) {
            throw new IllegalStateException("Debes marcar entrada primero");
        }

        if (asistenciaDTO.getId() == null) {
            asistenciaRepository.save(asistencia);
            return modelMapper.map(asistencia, AsistenciaDTO.class);
        }
        return null;
    }


    @Override
    public AsistenciaDTO registrarSalida(AsistenciaDTO asistenciaDTO) {

        Long empleadoId = asistenciaDTO.getEmpleadoId();

        AsistenciaDTO ultima = findTopByEmpleadoIdOrderByFechaRegistroDescHoraEntradaDesc(empleadoId);

        if (ultima == null || ultima.getHoraSalida() != null) {
            throw new IllegalStateException("No tienes entrada activa para registrar salida");
        }

        Asistencia asistencia = modelMapper.map(asistenciaDTO, Asistencia.class);
        asistencia.setModificadoEn(Instant.now());
        asistencia.setModificadoPor("Sistema");


        if(asistenciaDTO.getId()==null){
            asistenciaRepository.save(asistencia);
            return  modelMapper.map(asistencia, AsistenciaDTO.class);
        }
        return null;
    }

    @Override
    public List<AsistenciaDTO> listarAsistencias() {
        List<Asistencia> asistencias = asistenciaRepository.findAll();
        return asistencias.stream()
                .map(asistencia -> modelMapper.map(asistencia,AsistenciaDTO.class))
                .toList();
    }

    @Override
    public AsistenciaDTO findTopByEmpleadoIdOrderByFechaRegistroDescHoraEntradaDesc(Long empleadoId) {
        Asistencia ultima = asistenciaRepository
                .findTopByEmpleadoIdOrderByFechaRegistroDescHoraEntradaDesc(empleadoId);

        return (ultima != null) ? modelMapper.map(ultima, AsistenciaDTO.class) : null;
    }

    @Override
    public AsistenciaDashboardDTO obtenerDashboardPorSedeHoy(@Param("sedeId") Long sedeId){
        return asistenciaRepository.obtenerDashboardPorSedeHoy(sedeId);
    }

    @Override
    public List<ReporteMensualDTO> obtenerReporteMensual(Long sedeId, int mes, int anio) {
        return asistenciaRepository.obtenerReporteMensual(sedeId,mes,anio);
    }

    @Override
    public List<AsistenciaDTO> findAsistenciasByEmpleado_Id(Long empleadoId) {
        List<Asistencia> asistencias = asistenciaRepository.findAsistenciasByEmpleado_Id(empleadoId);
        return asistencias.stream()
                .map(asistencia -> modelMapper.map(asistencia,AsistenciaDTO.class))
                .toList();
    }


    private String calcularEstadoAsistencia(Long empleadoId, Instant horaEntrada) {
        if (horaEntrada == null) return "AUSENTE";

        HorarioTrabajo horario = horarioTrabajoRepository.findByEmpleadoId(empleadoId);
        if (horario == null || horario.getHoraEntrada() == null) return "PRESENTE";

        LocalTime horaLimite = horario.getHoraEntrada()
                .plusMinutes(horario.getToleranciaSalida());

        LocalTime horaEntradaLocal = horaEntrada
                .atZone(ZoneId.systemDefault())
                .toLocalTime();

        return horaEntradaLocal.isAfter(horaLimite) ? "TARDIA" : "PRESENTE";
    }

    private void validarGeocercaEntrada(Long empleadoId, BigDecimal latitudEntrada, BigDecimal longitudEntrada) {
        if (latitudEntrada == null || longitudEntrada == null) {
            throw new IllegalArgumentException("Latitud y longitud de entrada son requeridas para validar geocerca");
        }

        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado: " + empleadoId));

        Sede sede = empleado.getSede();
        if (sede == null || sede.getLatitud() == null || sede.getLongitud() == null || sede.getRadioGeocerca() == null) {
            throw new IllegalStateException("Sede del empleado no configurada correctamente");
        }

        double distancia = calcularDistanciaHaversine(
                latitudEntrada.doubleValue(), longitudEntrada.doubleValue(),
                sede.getLatitud().doubleValue(), sede.getLongitud().doubleValue()
        );

        if (distancia > sede.getRadioGeocerca().doubleValue()) {
            throw new IllegalStateException("Debes estar dentro del perímetro de la tienda");
        }

    }

    private double calcularDistanciaHaversine(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6371000;

        double lat1Rad = Math.toRadians(lat1);
        double lat2Rad = Math.toRadians(lat2);
        double deltaLat = Math.toRadians(lat2 - lat1);
        double deltaLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}
