    import java.util.*;
    import java.util.concurrent.ConcurrentHashMap;

    public class GestorSensores {
        private static GestorSensores instancia;
        private Map<String, Sensor> sensores;

        private GestorSensores() {
            this.sensores = new ConcurrentHashMap<>();
            System.out.println("🔧 Gestor de Sensores inicializado");
        }

        public static GestorSensores obtenerInstancia() {
            if (instancia == null) {
                synchronized (GestorSensores.class) {
                    if (instancia == null) {
                        instancia = new GestorSensores();
                    }
                }
            }
            return instancia;
        }

        public void registrarSensor(Sensor sensor) {
            sensores.put(sensor.getId(), sensor);
            System.out.println("✅ Sensor registrado: " + sensor.getId() + " en " + sensor.getUbicacion());
        }

        public void actualizarValorSensor(String idSensor, double nuevoValor) {
            Sensor sensor = sensores.get(idSensor);
            if (sensor != null) {
                sensor.setValor(nuevoValor);
                sensor.setUltimaActualizacion(new Date());
                System.out.println("📊 Sensor " + idSensor + " actualizado: " + nuevoValor);

                // Notificar a los observadores
                NotificadorAlertas.obtenerInstancia().verificarYNotificar(sensor);
            } else {
                System.out.println("❌ Sensor no encontrado: " + idSensor);
            }
        }

        public Sensor obtenerSensor(String idSensor) {
            return sensores.get(idSensor);
        }

        public void eliminarSensor(String idSensor) {
            sensores.remove(idSensor);
            System.out.println("🗑️ Sensor eliminado: " + idSensor);
        }

        public List<Sensor> obtenerTodosSensores() {
            return new ArrayList<>(sensores.values());
        }

        public int obtenerCantidadSensores() {
            return sensores.size();
        }

        public List<Sensor> obtenerSensoresPorTipo(String tipo) {
            List<Sensor> resultado = new ArrayList<>();
            for (Sensor s : sensores.values()) {
                if (s.getTipo().equalsIgnoreCase(tipo)) {
                    resultado.add(s);
                }
            }
            return resultado;
        }

        public String obtenerEstadisticas() {
            StringBuilder sb = new StringBuilder();
            sb.append("📊 ESTADÍSTICAS DE SENSORES:\n");
            sb.append("Total registrados: ").append(sensores.size()).append("\n");

            Map<String, Long> porTipo = new HashMap<>();
            for (Sensor s : sensores.values()) {
                porTipo.put(s.getTipo(), porTipo.getOrDefault(s.getTipo(), 0L) + 1);
            }

            for (Map.Entry<String, Long> e : porTipo.entrySet()) {
                sb.append(" - ").append(e.getKey()).append(": ").append(e.getValue()).append("\n");
            }
            return sb.toString();
        }
    }
