package ibrahim.ali.s301022172;

public class Patients {
    //private fields
    private int _patientId;
    private String _patientName;
    private int _patientGender;
    private String _patientDepartment;

    //no argument constructor
    public Patients() {}

    //three argument constructor
    public Patients(int patientId, String patientName, int patientGender, String patientDepartment) {
        this._patientId = patientId;
        this._patientName = patientName;
        this._patientGender = patientGender;
        this._patientDepartment = patientDepartment;
    }

    //getter and setter methods
    public int getPatientId() {
        return _patientId;
    }

    public void setPatientId(int patientId) {
        this._patientId = patientId;
    }

    public String getPatientName() {
        return _patientName;
    }

    public void setPatientName(String patientName) {
        this._patientName = patientName;
    }

    public int getPatientGender() {
        return _patientGender;
    }

    public void setPatientGender(int patientGender) {
        this._patientGender = patientGender;
    }

    public String getPatientDepartment() {
        return _patientDepartment;
    }

    public void setPatientDepartment(String patientDepartment) {
        this._patientDepartment = patientDepartment;
    }
}
