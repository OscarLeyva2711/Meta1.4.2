import java.util.Date;

public class EstrategiaAnalisisCalidadAire implements EstrategiaAnalisis {
    @Override
    public Alerta analizar(Sensor sensor) {
        if (!"calidad_aire".equals(sensor.getTipo())) return null;
        double valor = sensor.getValor(); // ppm
        if (valor > 150) {
            return new Alerta(sensor.getId(),
                    "☠️ CALIDAD DEL AIRE CRÍTICA: " + valor + " ppm en " + sensor.getUbicacion(),
                    NivelAlerta.CRITICO, new Date());
        } else if (valor > 100) {
            return new Alerta(sensor.getId(),
                    "⚠ Calidad del aire comprometida: " + valor + " ppm en " + sensor.getUbicacion(),
                    NivelAlerta.ADVERTENCIA, new Date());
        }
        return null;
    }
}
