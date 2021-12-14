/**
 * 
 */
package com.pmsi;

import java.util.ArrayList;

import pmsi.Acte;
import pmsi.Diagnostic;
import pmsi.Hospitalisation;
import pmsi.Patient;
import pmsi.ThesaurusCCAM;
import pmsi.ThesaurusCIM10;

/**
 *
 */
public final class Utils {
	
	/** Acte Printing **/
	public static void listActesHeader () {
		System.out.println("\t\t\t---------------------------------------------------------------------------------------");
		System.out.println("\t\t\t|   ID    | IDCCAM  | IDHospitalisation |      Date de l'acte       |  AnesthesiÃ© ?   |");
		System.out.println("\t\t\t---------------------------------------------------------------------------------------");
	}
	
	public static void listActesFooter () {
		System.out.println("\t\t\t---------------------------------------------------------------------------------------\n\n");
	}
	
	public static void listActesPrinting(ArrayList<Acte> listActes) {
		listActesHeader();
		for (Acte acte : listActes) {
			System.out.printf("\t\t\t| %7d | %5s |  %10d       |  %s | %s |\n",
					acte.getId(),
					acte.getIdCCAM(),
					acte.getIdHospitalisation(),
					acte.getDate(),
					acte.getAnesth()
				);
		}
		listActesFooter();
	}
	
	
	
	/** Diagnostic Printing **/
	public static void listDiagnosticsHeader () {
		System.out.println("\t\t\t---------------------------------------------------------------------------------");
		System.out.println("\t\t\t|  ID  | Code CIM10 | IDHospitalisation | Rang Diagnostique | Type Diagnostique |");
		System.out.println("\t\t\t---------------------------------------------------------------------------------");
	}
	
	public static void listDiagnosticsFooter () {
		System.out.println("\t\t\t---------------------------------------------------------------------------------\n\n");
	}
	
	public static void listDiagnosticsPrinting(ArrayList<Diagnostic> listDiagnostics) {
		listDiagnosticsHeader();
		for (Diagnostic diagnostic : listDiagnostics) {
			System.out.printf("\t\t\t|%5d |   %5s    |  %10d       |   %7d         |   %9s       |\n",
					diagnostic.getId(),
					diagnostic.getCodeCIM10(),
					diagnostic.getIdHospitalisation(),
					diagnostic.getDrang(),
					diagnostic.getDgType()
				);
		}
		listDiagnosticsFooter();
	}
	
	
	
	/** Thesaurus CCAM Printing **/
	public static void listThesaurusCCAMHeader () {
		System.out.println("\t\t------------------------------------------------------------------------------------------------");
		System.out.println("\t\t|    ID    |                                      Label CCAM                                   |");
		System.out.println("\t\t------------------------------------------------------------------------------------------------");
	}
	
		
	public static void listThesaurusCCAMFooter () {
		System.out.println("\t\t------------------------------------------------------------------------------------------------\n\n");
	}
	
	public static void listThesaurusCCAMPrinting (ArrayList<ThesaurusCCAM> libelleCCAM) {
		listThesaurusCCAMHeader();
		for (ThesaurusCCAM thesaurusCCAM : libelleCCAM) {
			System.out.printf("\t\t\t|%5s   |  %s                               \n",
			thesaurusCCAM.getId(),
			thesaurusCCAM.getLibelle());
		};
		listThesaurusCCAMFooter();
	}

	/** Thesaurus CIM10**/
	
	public static void listThesaurusCIM10Header () {
		System.out.println("\t\t------------------------------------------------------------------------------------------------");
		System.out.println("\t\t|    ID    |                                      Label CIM10                                  |");
		System.out.println("\t\t------------------------------------------------------------------------------------------------");
	}
	
	public static void listThesaurusCIM10Footer () {
		System.out.println("\t\t------------------------------------------------------------------------------------------------\n\n");
	}
	
	public static void listThesaurusCIM10Printing (ArrayList<ThesaurusCIM10> libelleCIM10) {
		listThesaurusCCAMHeader();
		for (ThesaurusCIM10 thesaurusCIM10 : libelleCIM10) {
			System.out.printf("\t\t\t|%5s   |  %s                               \n",
			thesaurusCIM10.getId(),
			thesaurusCIM10.getLibelle());
		};
		listThesaurusCIM10Footer();
	}
	
	
	/** Hospitalisation Printing **/
	public static void listHospitalisationsHeader () {
		System.out.println("\t\t\t----------------------------------------------------------------------------------");
		System.out.println("\t\t\t|   ID    | ID Patient  |      Date de l'acte       |      Date de l'acte        |");
		System.out.println("\t\t\t----------------------------------------------------------------------------------");
	}
	
	public static void listHospitalisationsFooter () {
		System.out.println("\t\t\t------------------------------------------------------------------------------\n\n");
	}
	
	public static void listHospitalisationsPrinting(ArrayList<Hospitalisation> listHospitalisations) {
		listHospitalisationsHeader();
		for (Hospitalisation hospitalisation : listHospitalisations) {
			System.out.printf("\t\t\t| %5d  |   %5d    |  %s   |  %23s   |\n",
					hospitalisation.getId(),
					hospitalisation.getIdPatient(),
					hospitalisation.getDateEntree(),
					hospitalisation.getDateSortie()
				);
			
		}
		listHospitalisationsFooter();
	}
	
	
	
	/** Patient Printing: Scénario 1 **/
	public static void listPatientsHeader () {
		System.out.println("\t\t\t\t------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t|   ID   |    Sexe  |    Nom   |    Prénom    |      Date de naissance     |");
		System.out.println("\t\t\t\t------------------------------------------------------------------------------");
	}
	
	public static void listPatientsFooter () {
		System.out.println("\t\t\t\t------------------------------------------------------------------------------\n\n");
	}
	
	public static void listPatientsPrinting(ArrayList<Patient> listPatients) {
		listPatientsHeader();
		for (Patient patient : listPatients) {
			System.out.printf("\t\t\t\t| %5d | %5s | %7s  | %10s   | %23s    |\n", 
					patient.getId(),
					patient.getSexe(),
					patient.getNom(),
					patient.getPrenom(),
					patient.getDateNaissance()
				);
		}
		listPatientsFooter();
	}
	
	
	/**Scénario 2 Printing**/
	public static void listHospitalisations2Header () {
		System.out.println("\t\t\t\t----------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t|   ID   |   Nom   |    Prénom  |    Date d'entrée         |      Date de sortie       |");
		System.out.println("\t\t\t\t----------------------------------------------------------------------------------------");
	}
	
	public static void listHospitalisations2Footer () {
		System.out.println("\t\t\t\t----------------------------------------------------------------------------------------\n\n");
	}
	
	public static void listHospitalisations2Printing(ArrayList<Hospitalisation> listHospitalisations, ArrayList<Patient> listPatients) {
		listHospitalisations2Header();
		for (Hospitalisation hospitalisation :listHospitalisations ) {
				System.out.printf("\t\t\t\t| %5d | %7s | %10s | %23s  | %23s   |\n",
						hospitalisation.getId(),
						listPatients.get(listHospitalisations.indexOf(hospitalisation)).getNom(),
						listPatients.get(listHospitalisations.indexOf(hospitalisation)).getPrenom(),
						hospitalisation.getDateEntree(),
						hospitalisation.getDateSortie()
						
						
					);
		
			
		}
		listHospitalisations2Footer();
	}
	
	/**Scénario 3 Printing**/
	public static void listActes2Header () {
		System.out.println("\t\t\t\t---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t|   ID   |   Nom    |    Prénom   |    ID_Acte    |      Date Acte           |    Anesth         |   ID_CCAM   |   Libellé CCAM                                     |");
		System.out.println("\t\t\t\t---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}
	
	public static void listActes2Footer () {
		System.out.println("\t\t\t\t---------------------------------------------------------------------------------------------------------------------------------------------------------------------\n\n");
	}
	
	public static void listActes2Printing(ArrayList<Patient> listPatients, ArrayList<Acte> listActes, ArrayList<ThesaurusCCAM> libelleCCAM) {
		listActes2Header();
		for (Acte acte : listActes) {
			System.out.printf("\t\t\t\t| %5d | %7s  | %10s  | %5d       | %20s  | %5s  | %5s     | %23s  |\n",
					listPatients.get(listActes.indexOf(acte)).getId(),
					listPatients.get(listActes.indexOf(acte)).getNom(),
					listPatients.get(listActes.indexOf(acte)).getPrenom(),
					acte.getId(),
					acte.getDate(),
					acte.getAnesth(),
					acte.getIdCCAM(),
					libelleCCAM.get(listActes.indexOf(acte)).getLibelle()
					
				);
		}
		listActes2Footer();
	}
	
	/**Scénario 4 Printing**/
	public static void listDiagnostics2Header () {
		System.out.println("\t\t\t\t----------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t|   ID   |   Nom    |    Prénom   |    ID_Diagnostic   |      Drang   |    dgType   |   Code CIM10   |   Libellé CIM10                                   |");
		System.out.println("\t\t\t\t----------------------------------------------------------------------------------------------------------------------------------------------------------");
	}
	
	public static void listDiagnostics2Footer () {
		System.out.println("\t\t\t\t-----------------------------------------------------------------------------------------------------------------------------------------------------------\n\n");
	}
	
	public static void listDiagnostics2Printing(ArrayList<Patient> listPatients, ArrayList<Diagnostic> listDiagnostics, ArrayList<ThesaurusCIM10> libelleCIM10) {
		listDiagnostics2Header();
		for (Diagnostic diagnostic : listDiagnostics) {
			System.out.printf(" \t\t\t\t| %5d | %7s  | %10s  |   %5d           | %5d        |  %5s    | %5s          | %20s          | \n",
					listPatients.get(listDiagnostics.indexOf(diagnostic)).getId(),
					listPatients.get(listDiagnostics.indexOf(diagnostic)).getNom(),
					listPatients.get(listDiagnostics.indexOf(diagnostic)).getPrenom(),
					diagnostic.getId(),
					diagnostic.getDrang(),
					diagnostic.getDgType(),
					diagnostic.getCodeCIM10(),
					libelleCIM10.get(listDiagnostics.indexOf(diagnostic)).getLibelle()
					
				);
		}
		listDiagnostics2Footer(); 
	}
	
	
	/**Scénario 8 Printing**/
	public static void listHospitalisations3Header () {
		System.out.println("\t\t\t\t------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t|   ID Hospitalisation   |   Date d'entrée             |    Date de sortie          |    ID_Diagnostic    |   ID CIM10   |   Libellé CIM10                                                           |");
		System.out.println("\t\t\t\t------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}
	
	public static void listHospitalisations3Footer () {
		System.out.println("\t\t\t\t------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n\n");
	}
	
	public static void listHospitalisations3Printing(ArrayList<Hospitalisation> listHospitalisations, ArrayList<Diagnostic> listDiagnostics, ArrayList<ThesaurusCIM10> libelleCIM10) {
		listHospitalisations3Header();
		for (Hospitalisation hospitalisation : listHospitalisations) {
			System.out.printf(" \t\t\t\t| %13d          | %25s   |%25s   | %10d          | %5s        |%60s               | \n",
					hospitalisation.getId(),
					hospitalisation.getDateEntree(),
					hospitalisation.getDateSortie(),
					listDiagnostics.get(listHospitalisations.indexOf(hospitalisation)).getId(),
					libelleCIM10.get(listHospitalisations.indexOf(hospitalisation)).getId(),
					libelleCIM10.get(listHospitalisations.indexOf(hospitalisation)).getLibelle()
					
				);
		}
		listHospitalisations3Footer();
	}
	
	/**Scénario 9 Printing**/
	public static void listPatients2Header () {
		System.out.println("\t\t\t\t-------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t|   ID   |   Nom     |     Prénom           |     Date d'entrée              |      Date de sortie          |");
		System.out.println("\t\t\t\t-------------------------------------------------------------------------------------------------------------");
	}
	
	public static void listPatients2Footer () {
		System.out.println("\t\t\t\t-------------------------------------------------------------------------------------------------------------\n\n");
	}
	
	public static void listPatients2Printing(ArrayList<Patient> listPatients, ArrayList<Hospitalisation> listHospitalisations) {
		listPatients2Header();
		for (Patient patient : listPatients) {
			System.out.printf("\t\t\t\t| %5d | %7s   | %15s      | %25s      | %25s    |\n",
					patient.getId(),
					patient.getNom(),
					patient.getPrenom(),
					listHospitalisations.get(listPatients.indexOf(patient)).getDateEntree(),
					listHospitalisations.get(listPatients.indexOf(patient)).getDateSortie()
					
					
				);
		}
		listPatients2Footer();
	}
	
	
	
	
	
	/** Application Printing **/
	public static void applicationHeader () {
		System.out.println("\t\t============================= Bienvenue dans votre application de gestion PMSI =============================\n");
		System.out.println("\t\t\t Menu");
		System.out.println("\t\t\t 1. Liste des cinq premiers patients \n"
				+ "\t\t\t 2. Liste de tous les hospitalisations d'un patient\n"
				+ "\t\t\t 3. Liste des actes et leurs libellés liées à une hospitalisation \n"
				+ "\t\t\t 4. Liste des diagnostics et leur libellés liées à une hospitalisation \n"
				+ "\t\t\t 5. Nombre d'hospitalisation selon le type de diagnostic \n"
				+ "\t\t\t 6. ID des patients qui avaient moins de 50 ans le jour de leur hospitalisation en fonction du sexe \n"
				+ "\t\t\t 7. Moyenne d'âge à l'entrée à l'hôpital en fonction du sexe \n"
				+ "\t\t\t 8. Listes des hospitalisations et des diagnostics liées à un patient \n"
				+ "\t\t\t 9. Rechercher des patients n'ayant eu aucun acte en fonction du type de diagnostic \n\n");
		System.out.println("\t\t============================================================================================================\n");
	}
}
