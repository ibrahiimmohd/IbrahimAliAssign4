package ibrahim.ali.s301022172;

public class Tests {
    //private fields
    private int _PatientId;
    private int _TestCovid19;
    private String _CreatedAt;
    private int _BloodPressure;
    private int _RespiratoryRate;
    private int _BloodOxygenLevel;
    private int _HeartBeatRate;

    //no argument constructor
    public Tests() {}

    //three argument constructor
    public Tests(int patientId, int testCovid19, String createdAt, int bloodPressure, int respiratoryRate, int bloodOxygenLevel, int heartBeatRate) {
        this._PatientId = patientId;
        this._TestCovid19 = testCovid19;
        this._CreatedAt = createdAt;
        this._BloodPressure = bloodPressure;
        this._RespiratoryRate = respiratoryRate;
        this._BloodOxygenLevel = bloodOxygenLevel;
        this._HeartBeatRate = heartBeatRate;
    }

    //getter and setter methods
    public int getPatientId() {
        return _PatientId;
    }
    public void setPatientId(int patientId) {
        this._PatientId = patientId;
    }

    public int getTestCovid19() {
        return _TestCovid19;
    }
    public void setTestCovid19(int testCovid19) {
        this._TestCovid19 = testCovid19;
    }

    public String getCreatedAt() {
        return _CreatedAt;
    }
    public void setCreatedAt(String createdAt) {
        this._CreatedAt = createdAt;
    }

    public int getBloodPressure() {
        return _BloodPressure;
    }
    public void setBloodPressure(int bloodPressure) {
        this._BloodPressure = bloodPressure;
    }

    public int getRespiratoryRate() {
        return _RespiratoryRate;
    }
    public void setRespiratoryRate(int respiratoryRate) {
        this._RespiratoryRate = respiratoryRate;
    }

    public int getBloodOxygenLevel() {
        return _BloodOxygenLevel;
    }
    public void setBloodOxygenLevel(int bloodOxygenLevel) {
        this._BloodOxygenLevel = bloodOxygenLevel;
    }

    public int getHeartBeatRate() {
        return _HeartBeatRate;
    }
    public void setHeartBeatRate(int heartBeatRate) {
        this._HeartBeatRate = heartBeatRate;
    }

}
