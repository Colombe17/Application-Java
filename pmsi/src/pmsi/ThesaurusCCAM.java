/**
 * 
 */
package pmsi;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import com.pmsi.Thesaurus;
import com.pmsi.Utils;

/**
 * Entity THESAURUS_CCAM
 */
public class ThesaurusCCAM implements Thesaurus {

	/** Attributes **/
	private String id;
	private String libelleCCAM;
	ArrayList<Acte> listActes;
	
	
	public ThesaurusCCAM() {
		this.id = null;
		this.libelleCCAM = null;
		this.listActes = new ArrayList<>();
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public ThesaurusCCAM setId(String id) {
		this.id = id;
		return this;
	}

	
	/**
	 * @return the libelleCCAM
	 */
	public String getLibelle() {
		return this.libelleCCAM;
	}


	/**
	 * 
	 * @param libelleCCAM
	 * @return
	 */
	public ThesaurusCCAM setLibelle(String libelle) {
		this.libelleCCAM = libelle;
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
	public ThesaurusCCAM addActe(Acte acte) {
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
	public ThesaurusCCAM removeActe(Acte acte) {
		if (this.listActes.contains(acte)) {
			this.listActes.remove(acte);
		}
		if (acte.getIdCCAM() == this.id) {
			acte.setIdCCAM("");
		}
		return this;
	}

	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// First Thesaurus
		ThesaurusCCAM THCCAM1 = new ThesaurusCCAM();
		THCCAM1.setId("FBFA003")
		   .setLibelle("Exérèse du thymus vestigial, par thoracotomie")
		;
		
		// Second Thesaurus
		ThesaurusCCAM THCCAM2 = new ThesaurusCCAM();
		THCCAM2.setId("FBFA004")
		   .setLibelle("Thymus, par thoracotomie par thoracotomie")
		;
		
		ArrayList<Thesaurus> listThesaurusCCAM = new ArrayList<>();
		listThesaurusCCAM.add(THCCAM1);
		listThesaurusCCAM.add(THCCAM2);

		System.out.println("\t\t================================== Listes des Thesaurus CCAM ===================================\n");
	//	Utils.listThesaurusPrinting(listThesaurusCCAM);
		
		
		// First Acte
		Acte A1 = new Acte();
		A1.setId(8748534)
		  .setIdCCAM("FBFA003")
		  .setIdHospitalisation(100021)
		  .setDate(new Date("13/10/2018"))
		  .setAnesth((byte) 1)
		;
		
		// Second Acte
		Acte A2 = new Acte();
		A2.setId(8748535)
		  .setIdCCAM("FBFA003")
		  .setIdHospitalisation(100021)
		  .setDate(new Date("15/10/2019"))
		  .setAnesth((byte) 0)
	    ;
		
		
		THCCAM1.addActe(A1);
		THCCAM1.addActe(A2);
		
		// Print Intial List Acte
		System.out.println("\t\t====================================== Listes des actes Initiaux =====================================\n");
		Utils.listActesPrinting(THCCAM1.getListActes());
		
		// Remove Acte A2		
		THCCAM1.removeActe(A2);

		// Print List Acte After remove
		System.out.println("\t\t============================== Listes des actes après suppression de A2 ============================\n");
		Utils.listActesPrinting(THCCAM1.getListActes());
		
		// Print Acte A2 after remove from ListeActes
		System.out.println("\t\t========================== A2 après l'avoir supprimé de la liste des actes =========================\n");
		Utils.listActesHeader();
		System.out.printf("\t\t\t| %7d | %7s |  %10d       |  %s  | %s |\n",
				A2.getId(),
				A2.getIdCCAM(),
				A2.getIdHospitalisation(),
				A2.getDate(),
				A2.getAnesth()
			);
		Utils.listActesFooter();
	}

}
