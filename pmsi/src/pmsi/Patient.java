/**
 * 
 */
package pmsi;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import com.pmsi.DateManager;
import com.pmsi.Utils;

/**
 * Entity Patient
 */
public class Patient extends DateManager {

	/** Constantes **/
	private static final String [] SEXE_TRADUCTION_FR = {"Masculin", "Féminin "};

	/** Attributes **/
	private int id;
	private int sexe;
	private Date dateNaissance;
	private String prenom;
	private String nom;
	ArrayList<Hospitalisation> hospitalisations;
	
	

	public Patient() {
		this.id = 0;
		this.sexe = 0;
		this.dateNaissance = null;
		this.prenom = null;
		this.nom = null;
		this.hospitalisations = new ArrayList<>();
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Patient setId(int id) {
		this.id = id;
		return this;
	}

	/**
	 * @return the sexe
	 */
	public String getSexe() {
		switch (this.sexe) {
			case 1: return SEXE_TRADUCTION_FR[0];
			case 2: return SEXE_TRADUCTION_FR[1];
			default: return "";
		}
	}

	/**
	 * 
	 * @param sexe
	 * @return
	 */
	public Patient setSexe(int sexe) {
		this.sexe = sexe;
		return this;
	}

	/**
	 * @return the dateNaissance
	 */
	public String getDateNaissance() {
		return this.getDateFormatInstance().format(this.dateNaissance);
	}

	/**
	 * 
	 * @param dateNaissance
	 * @return
	 */
	public Patient setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
		return this; 
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return this.prenom;
	}

	/**
	 * 
	 * @param prenom
	 * @return
	 */
	public Patient setPrenom(String prenom) {
		this.prenom = prenom;
		return this;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * 
	 * @param nom
	 * @return
	 */
	public Patient setNom(String nom) {
		this.nom = nom;
		return this;
	}


	/**
	 * @return the hospitalisations
	 */
	public ArrayList<Hospitalisation> getHospitalisations() {
		return this.hospitalisations;
	}

	/**
	 * 
	 * @param hospitalisation
	 * @return
	 */
	public Patient addHospitalisation(Hospitalisation hospitalisation) {
		if (!Objects.isNull(hospitalisation)) {
			this.hospitalisations.add(hospitalisation);
		}
		return this;
	}
	
	
	public Patient removeHospitalisation(Hospitalisation hospitalisation) {
		if (this.hospitalisations.contains(hospitalisation)) {
			this.hospitalisations.remove(hospitalisation);
		}
		if (hospitalisation.getIdPatient() == this.id) {
			hospitalisation.setIdPatient(0);
		}
		return this;
	}


	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Patient Jean = new Patient();
		Jean.setId(100130)
		    .setSexe((byte) 1)
		    .setNom("Dupont")
		    .setPrenom("Jean")
		    .setDateNaissance(new Date("14/09/2000"))
		;
		ArrayList<Patient> listPatients = new ArrayList<>();
		
		
		// First Hospitalisation
		Hospitalisation H1 = new Hospitalisation();
		H1.setId(100021)
		  .setIdPatient(100130)
		  .setDateEntree(new Date("12/10/2018"))
		  .setDateSortie(new Date("16/10/2018"))
		;
		
		
		// Second Hospitalisation
		Hospitalisation H2 = new Hospitalisation();
		H2.setId(100022)
		  .setIdPatient(100130)
		  .setDateEntree(new Date("15/10/2019"))
		  .setDateSortie(new Date("19/10/2019"))
		;
		
		Jean.addHospitalisation(H1);
		Jean.addHospitalisation(H2);
		
		System.out.println("\t\t\t============================== Listes des patients ==============================\n");
		listPatients.add(Jean);
		Utils.listPatientsPrinting(listPatients);
		
		
		System.out.println("\t\t\t====================== Liste Hospitalisation de Jean (Initiale) =====================\n");
		Utils.listHospitalisationsPrinting(Jean.getHospitalisations());
		
		Jean.removeHospitalisation(H1);
		
		System.out.println("\t\t\t==================== Liste Hospitalisation de Jean (Apres Suppression) ===============\n");
		Utils.listHospitalisationsPrinting(Jean.getHospitalisations());
		

	}

}
