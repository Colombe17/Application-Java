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
 * Entity Hospitalisation
 */
public class Hospitalisation extends DateManager {

	/** Attributes **/
	private int id;
	private int idPatient;
	private Date dateEntree;
	private Date dateSortie;
	ArrayList<Diagnostic> listDiagnostics;
	ArrayList<Acte> listActes;
	
	
	public Hospitalisation() {
		this.id = 0;
		this.idPatient = 0;
		this.dateEntree = null;
		this.dateSortie = null;
		this.listDiagnostics = new ArrayList<>();
		this.listActes = new ArrayList<>();
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
	public Hospitalisation setId(int id) {
		this.id = id;
		return this;
	}


	/**
	 * @return the idPatient
	 */
	public int getIdPatient() {
		return this.idPatient;
	}


	/**
	 * 
	 * @param idPatient
	 * @return
	 */
	public Hospitalisation setIdPatient(int idPatient) {
		this.idPatient = idPatient;
		return this;
	}


	/**
	 * @return the dateEntree
	 */
	public String getDateEntree() {
		return this.getDateFormatInstance().format(this.dateEntree);
	}


	/**
	 * 
	 * @param dateEntree
	 * @return
	 */
	public Hospitalisation setDateEntree(Date dateEntree) {
		this.dateEntree = dateEntree;
		return this;
	}


	/**
	 * @return the dateSortie
	 */
	public String getDateSortie() {
		return this.getDateFormatInstance().format(this.dateSortie);
	}


	/**
	 * 
	 * @param dateSortie
	 * @return
	 */
	public Hospitalisation setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
		return this;
	}


	/**
	 * @return the listDiagnostics
	 */
	public ArrayList<Diagnostic> getListDiagnostics() {
		return this.listDiagnostics;
	}

	/**
	 * 
	 * @param diagnostic
	 * @return
	 */
	public Hospitalisation addDiagnostic(Diagnostic diagnostic) {
		if (!Objects.isNull(diagnostic)) {
			this.listDiagnostics.add(diagnostic);
		}
		return this;
	}
	
	/**
	 * 
	 * @param diagnostic
	 * @return
	 */
	public Hospitalisation removeDiagnostic(Diagnostic diagnostic) {
		if (this.listDiagnostics.contains(diagnostic)) {
			this.listDiagnostics.remove(diagnostic);
		}
		if (diagnostic.getIdHospitalisation() == this.id) {
			diagnostic.setIdHospitalisation(0);
		}
		return this;
	}


	/**
	 * @return the listActes
	 */
	public ArrayList<Acte> getListActes() {
		return this.listActes;
	}

	/**
	 * 
	 * @param acte
	 * @return
	 */
	public Hospitalisation addActe(Acte acte) {
		if (!Objects.isNull(acte)) {
			this.listActes.add(acte);
		}
		return this;
	}
	
	/**
	 * 
	 * @param acte
	 * @return
	 */
	public Hospitalisation removeActe(Acte acte) {
		if (this.listActes.contains(acte)) {
			this.listActes.remove(acte);
		}
		if (acte.getIdHospitalisation() == this.id) {
			acte.setIdHospitalisation(0);
		}
		return this;
	}
	


	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Hospitalisation H1 = new Hospitalisation();
		H1.setId(100021)
		  .setIdPatient(501080)
		  .setDateEntree(new Date("12/10/2018"))
		  .setDateSortie(new Date("16/10/2018"))
		;
		
		Hospitalisation H2 = new Hospitalisation();
		H2.setId(100022)
		  .setIdPatient(501081)
		  .setDateEntree(new Date("15/10/2017"))
		  .setDateSortie(new Date("15/10/2017"))
		;
		
		ArrayList<Hospitalisation> listHospitalisations = new ArrayList<>();
		listHospitalisations.add(H1);
		listHospitalisations.add(H2);
		
		System.out.println("\t\t=================================== Listes des hospitalisations =====================================\n");
		Utils.listHospitalisationsPrinting(listHospitalisations);
		
		// First Acte
		Acte A1 = new Acte();
		A1.setId(8748534)
		  .setIdCCAM("DZQM005")
		  .setIdHospitalisation(100021)
		  .setDate(new Date("13/10/2018"))
		  .setAnesth((byte) 0)
		;
				
		// Seconde Acte
		Acte A2 = new Acte();
		A2.setId(8748535)
		  .setIdCCAM("DZQM006")
		  .setIdHospitalisation(100021)
		  .setDate(new Date("15/10/2019"))
		  .setAnesth((byte) 1)
		;
		
		H1.addActe(A1)
		  .addActe(A2)
		;
		
		// Print list Acte of H1
		System.out.println("\t\t=============================== Listes des actes pour H1 (initiaux) ==================================\n");
		Utils.listActesPrinting(H1.getListActes());
		
		// First diagnostic
		Diagnostic D1 = new Diagnostic();
		D1.setId(303)
		  .setCodeCIM10("E119")
		  .setIdHospitalisation(100021)
		  .setDrang((byte) 6)
		  .setDgType("S")
		;
		
		// Second diagnostic
		Diagnostic D2 = new Diagnostic();
		D2.setId(304)
		  .setCodeCIM10("E120")
		  .setIdHospitalisation(100021)
		  .setDrang((byte) 1)
		  .setDgType("S")
		;
		
		H1.addDiagnostic(D1)
		  .addDiagnostic(D2)
		;
				
		// Print list Diaggnostics H1
		System.out.println("\t\t================================== Listes des Diagnostics pour H1 ====================================\n");
		Utils.listDiagnosticsPrinting(H1.getListDiagnostics());
		
		
		
		H1.removeActe(A2);

		
		System.out.println("\t\t============================ Listes des actes pour H1 (après suppression de A2) =======================\n");		
		Utils.listActesPrinting(H1.getListActes());
		
		
		
		H1.removeDiagnostic(D1);

		
		System.out.println("\t\t======================== Listes des diagnostics pour H1 (après suppression de D1) =====================\n");
		Utils.listDiagnosticsPrinting(H1.getListDiagnostics());

	}

}
