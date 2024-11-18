package jdbcExercises;

public class Doctor {
	int doctor_codi;
	int doctor_hospital_codi;
	String doctor_nom;
	String doctor_especialitat;
	
	
	public Doctor(int doctor_codi, int doctor_hospital_codi, String doctor_nom, String doctor_especialitat) {
		super();
		this.doctor_codi = doctor_codi;
		this.doctor_hospital_codi = doctor_hospital_codi;
		this.doctor_nom = doctor_nom;
		this.doctor_especialitat = doctor_especialitat;
	}
	
	
	public int getDoctor_codi() {
		return doctor_codi;
	}
	public void setDoctor_codi(int doctor_codi) {
		this.doctor_codi = doctor_codi;
	}

	public int getDoctor_hospital_codi() {
		return doctor_hospital_codi;
	}
	public void setDoctor_hospital_codi(int doctor_hospital_codi) {
		this.doctor_hospital_codi = doctor_hospital_codi;
	}
	public String getDoctor_nom() {
		return doctor_nom;
	}
	public void setDoctor_nom(String doctor_nom) {
		this.doctor_nom = doctor_nom;
	}
	public String getDoctor_especialitat() {
		return doctor_especialitat;
	}
	public void setDoctor_especialitat(String doctor_especialitat) {
		this.doctor_especialitat = doctor_especialitat;
	}	
	
}
