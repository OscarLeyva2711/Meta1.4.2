public class SistemaMonitoreoIoT {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== INICIO SISTEMA IoT ===\n");

        GestorSensores gestor = GestorSensores.obtenerInstancia();
        NotificadorAlertas notificador = NotificadorAlertas.obtenerInstancia();

        // Observadores
        notificador.registrarObservador(new NotificadorEmail());
        notificador.registrarObservador(new NotificadorDashboard());

        // Sensores
        Sensor temp = new Sensor("T1", "temperatura", 30, "Sala A");
        Sensor aire = new Sensor("A1", "calidad_aire", 90, "Almacén");
        Sensor humedad = new Sensor("H1", "humedad", 50, "Oficina");

        gestor.registrarSensor(temp);
        gestor.registrarSensor(aire);
        gestor.registrarSensor(humedad);

        // Estrategias
        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisCalidadAire());
        gestor.actualizarValorSensor("A1", 160);

        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisHumedad());
        gestor.actualizarValorSensor("H1", 85);

        System.out.println("\n" + gestor.obtenerEstadisticas());
    }
}