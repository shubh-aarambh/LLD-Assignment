public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) {
        this.reg = reg;
    }

    public void startClass() {
        Powerable pj = (Powerable) reg.getFirstOfType("Projector");
        pj.powerOn();
        ((InputSource) pj).connectInput("HDMI-1");

        Dimmable lights = (Dimmable) reg.getFirstOfType("LightsPanel");
        lights.setBrightness(60);

        Climatizable ac = (Climatizable) reg.getFirstOfType("AirConditioner");
        ac.setTemperatureC(24);

        Scannable scan = (Scannable) reg.getFirstOfType("AttendanceScanner");
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        ((Powerable) reg.getFirstOfType("Projector")).powerOff();
        ((Powerable) reg.getFirstOfType("LightsPanel")).powerOff();
        ((Powerable) reg.getFirstOfType("AirConditioner")).powerOff();
    }
}
