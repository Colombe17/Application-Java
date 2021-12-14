package pmsi;

import java.util.ArrayList;
import java.util.Date;

import com.pmsi.DateManager;
import com.pmsi.Utils;

/**
 * Entity Acte
 */
public class Acte extends DateManager {
	
	/** Constantes **/
	private static final String [] ANESTHESIE_TRADUCTION_FR = {"Avec Anesthésie", "Sans Anesthésie"};

	/** Attributes **/
	private int id;
	private String idCCAM;
	private Date date;
	private int idHospitalisation;
	private byte anesth;
	

	public Acte () {
		this.id = 0;
		this.idCCAM = null;
		this.date = null;
		this.idHospitalisation = 0;
		this.anesth = 0;
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
	public Acte setId(int id) {
		this.id = id;
		return this;
	}


	/**
	 * @return the idCCAM
	 */
	public String getIdCCAM() {
		return this.idCCAM;
	}


	/**
	 * 
	 * @param idCCAM
	 * @return
	 */
	public Acte setIdCCAM(String idCCAM) {
		this.idCCAM = idCCAM;
		return this;
	}


	/**
	 * @return the date
	 */
	public String getDate() {
		return this.getDateFormatInstance().format(this.date);
	}


	/**
	 * 
	 * @param date
	 * @return
	 */
	public Acte setDate(Date date) {
		this.date = date;
		return this;
	}


	/**
	 * @return the idHospitalisation
	 */
	public int getIdHospitalisation() {
		return this.idHospitalisation;
	}


	/**
	 * 
	 * @param idHospitalisation
	 * @return
	 */
	public Acte setIdHospitalisation(int idHospitalisation) {
		this.idHospitalisation = idHospitalisation;
		return this;
	}


	/**
	 * @return the anesth
	 */
	public String getAnesth() {
		return this.anesth != 0 ? ANESTHESIE_TRADUCTION_FR[0] : ANESTHESIE_TRADUCTION_FR[1];
	}


	/**
	 * 
	 * @param anesth
	 * @return
	 */
	public Acte setAnesth(byte anesth) {
		this.anesth = anesth;
		return this;
	}


	public static void main(String[] args) {
		// First Acte
		Acte A1 = new Acte();
		A1.setId(8748534)
		  .setIdCCAM("DZQM005")
		  .setIdHospitalisation(432474)
		  .setDate(new Date())
		  .setAnesth((byte) 0)
		;
		
		// Second Acte
		Acte A2 = new Acte();
		A2.setId(8748535)
		  .setIdCCAM("DZQM065")
		  .setIdHospitalisation(432485)
		  .setDate(new Date())
		  .setAnesth((byte) 1)
		;
		
		// Third Acte
		Acte A3 = new Acte();
		A3.setId(8748545)
		  .setIdCCAM("DZQM066")
		  .setIdHospitalisation(432486)
		  .setDate(new Date())
		  .setAnesth((byte) 0)
		;
		
		
		ArrayList<Acte> listActes = new ArrayList<>();
		listActes.add(A1);
		listActes.add(A2);
		listActes.add(A3);
		
		// Print list Acte
		System.out.println("\t\t====================================== Listes des actes ===========================================\n");
		Utils.listActesPrinting(listActes);
		

	}

}
