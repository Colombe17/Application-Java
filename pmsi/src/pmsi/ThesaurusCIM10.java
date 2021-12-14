/**
 * 
 */
package pmsi;

import java.util.ArrayList;
import java.util.Objects;

import com.pmsi.Thesaurus;
import com.pmsi.Utils;

/**
 * Entity THS_CIM10
 */
public class ThesaurusCIM10 implements Thesaurus {

	/** Attributes **/
	private String id;
	private String libelleCIM10;
	ArrayList<Diagnostic> listDiagnostics;

	public ThesaurusCIM10 () {
		this.id = null;
		this.libelleCIM10 = null;
		this.listDiagnostics = new ArrayList<>();
	}
	
	/**
	 * 
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * 
	 * @param idCIM10
	 * @return
	 */
	public ThesaurusCIM10 setId(String idCIM10) {
		this.id = idCIM10;
		return this;
	}
	
	/**
	 * @return the libelleCIM10
	 */
	public String getLibelle() {
		return this.libelleCIM10;
	}
	
	/**
	 * 
	 * @param libelle
	 * @return
	 */
	public ThesaurusCIM10 setLibelle(String libelle) {
		this.libelleCIM10 = libelle;
		return this;
	}
	
	/**
	 * 
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
	public ThesaurusCIM10 addDiagnostic(Diagnostic diagnostic) {
		if(!Objects.isNull(diagnostic)) {
			this.listDiagnostics.add(diagnostic);
		}
		return this;
	}
	
	/**
	 * 
	 * @param diagnostic
	 * @return
	 */
	public ThesaurusCIM10 removeDiagnostic(Diagnostic diagnostic) {
		if (this.listDiagnostics.contains(diagnostic)) {
			this.listDiagnostics.remove(diagnostic);
		}
		if (diagnostic.getCodeCIM10() == this.id) {
			diagnostic.setCodeCIM10("");
		}
		return this;
	}

	
	public static void main(String[] args) {
		// First Thesaurus CIM10
		ThesaurusCIM10 THCIM101 = new ThesaurusCIM10();
		THCIM101.setId("I200+0")
		   .setLibelle("ANGOR INSTABLE AVEC LESIONS CORONAIRES")
		;
		
		// Second Thesaurus CIM10
		ThesaurusCIM10 THCIM102 = new ThesaurusCIM10();
		THCIM102.setId("I200+1")
		   .setLibelle("Par thoracotomie")
		;
		
		ArrayList<Thesaurus> listThesaurusCIM10 = new ArrayList<>();
		listThesaurusCIM10.add(THCIM101);
		listThesaurusCIM10.add(THCIM102);
		
		System.out.println("\t\t================================== Listes des Thesaurus CIM10 ===================================\n");
		//Utils.listThesaurusPrinting(listThesaurusCIM10);
		
		
		// First diagnostic
		Diagnostic D1 = new Diagnostic();
		D1.setId(303)
		  .setCodeCIM10("I200+0")
		  .setIdHospitalisation(100021)
		  .setDrang((byte) 6)
		  .setDgType("S")
	    ;
		
		// Second diagnostic
		Diagnostic D2 = new Diagnostic();
		D2.setId(304)
		  .setCodeCIM10("I200+0")
		  .setIdHospitalisation(100021)
		  .setDrang((byte) 1)
		  .setDgType("D");
			
		THCIM101.addDiagnostic(D1);
		THCIM101.addDiagnostic(D2);
	
		// Print Intial List Diasgnostics
		System.out.println("\t\t================================ Listes des Diagnostics Initiaux =============================\n");
		Utils.listDiagnosticsPrinting(THCIM101.getListDiagnostics());

		// Remove Diagnostic D1		
		THCIM101.removeDiagnostic(D1);

		// Print List Diagnostic After remove
		System.out.println("\t\t======================== Listes des diagnostics après suppression de D1 =====================\n");
		Utils.listDiagnosticsPrinting(THCIM101.getListDiagnostics());

		// Print Diagnostic D1 after remove from ListeActes
		System.out.println("\t\t================== D1 après l'avoir supprimé de la liste des diagnostics ====================\n");
		Utils.listDiagnosticsHeader();
		System.out.printf("\t\t\t|%5d |   %5s    |  %10d       |   %7d         |   %9s       |\n",
				D1.getId(),
				D1.getCodeCIM10(),
				D1.getIdHospitalisation(),
				D1.getDrang(),
				D1.getDgType()
			);
		Utils.listDiagnosticsFooter();
	}

}
