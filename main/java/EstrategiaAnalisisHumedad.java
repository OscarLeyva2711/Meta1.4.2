import java.util.Date;

public class EstrategiaAnalisisHumedad implements EstrategiaAnalisis {
    @Override
    public Alerta analizar(Sensor sensor) {
        if (!"humedad".equals(sensor.getTipo())) return null;
        double valor = sensor.getValor(); // porcentaje
        if (valor < 20) {
            return new Alerta(sensor.getId(),
                    "💧 HUMEDAD MUY BAJA: " + valor + "% en " + sensor.getUbicacion(),
                    NivelAlerta.ADVERTENCIA, new Date());
        } else if (valor > 80) {
            return new Alerta(sensor.getId(),
                    "💦 HUMEDAD EXCESIVA: " + valor + "% en " + sensor.getUbicacion(),
                    NivelAlerta.CRITICO, new Date());
        }
        return null;
    }
}