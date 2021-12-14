package pmsi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.pmsi.Utils;

public class Application2 {
	
static final int [] CHOIX_POSSIBLES = {1,2,3,4,5,6,7,8,9};
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/<>";
	static final String USER = "";
	static final String PASS = "";
	
	public static boolean contains(final int[] tab, final int key) {
		Arrays.sort(tab);
		return Arrays.binarySearch(tab, key)>=0;
	}
	
	public static void main(String[] args) throws ParseException, SQLException {
		Utils.applicationHeader();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		ArrayList<Patient> listPatients = new ArrayList<>();
		ArrayList<Hospitalisation> listHospitalisations = new ArrayList<>();
		ArrayList<Acte> listActes = new ArrayList<>();
		ArrayList<Diagnostic> listDiagnostics = new ArrayList<>();
		ArrayList<ThesaurusCCAM> libelleCCAM = new ArrayList<>();
		ArrayList<ThesaurusCIM10> libelleCIM10 = new ArrayList<>();
int choixUtilisateur = 0;
		
		do {
			
			System.out.print("Faites un choix : ");
			choixUtilisateur = sc.nextInt();
			
			if(!Application2.contains(CHOIX_POSSIBLES, choixUtilisateur)) {
				System.err.println("\nVeuillez entrée un choix valide \n");
			}
		} while (!Application2.contains(CHOIX_POSSIBLES, choixUtilisateur));
		
		switch (choixUtilisateur) {
		//Scénario 1: Liste des patients 
					case 1: 
						String QUERY = "SELECT * FROM tab_patient limit 5 ;";
						// Open a connection
					      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
					         Statement stmt = conn.createStatement();
					         ResultSet rs = stmt.executeQuery(QUERY);) {

					         // Extract data from result set
					    	  while (rs.next()) {
						        	 Patient patient = new Patient();
						        	 patient.setId(rs.getInt("ID_PATIENT"))
							        	  .setSexe(rs.getByte("SEXE"))
							        	  .setNom(rs.getString("NOM"))
							        	  .setPrenom(rs.getString("PRENOM"))
							        	  .setDateNaissance(rs.getDate("DATE_NAISSANCE"))
							         ;
						        	 listPatients.add(patient);
						         }
								System.out.println("\n\t\t\t\t============================= Listes des patients ============================\n");
								//Affichage des résultats
								Utils.listPatientsPrinting(listPatients);
					      } catch (SQLException e) {
						         e.printStackTrace();
						      }
							break;
							
			// Scénario 2: Liste des hospitalisations liées à un patient
				   case 2:
					   String QUERY2 = "select tp.NOM\n"
								+ ",tp.PRENOM\n"
								+ ",th.ID_HOSPITALISATION\n"
								+ "	  ,th.DATE_ENTREE\n"
								+ "	  ,th.DATE_SORTIE\n"
								+ "from tab_patient tp \n"
								+ "    inner join tab_hospitalisation th \n"
								+ "    on tp.ID_PATIENT = th.ID_PATIENT\n"
								+ "where tp.ID_PATIENT = ?;\n";
						
						// Open a connection
						System.out.print("\t\t Saisir l'ID du patient : ");
						int ID_PATIENT= sc.nextInt();
					      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
					    		  try(PreparedStatement ps = conn.prepareStatement(QUERY2)) {
					    	       	  ps.setInt(1, ID_PATIENT);
					    	       	  ResultSet rs = ps.executeQuery();
					    	       	while (rs.next()) {	
							        	 Hospitalisation hospitalisation = new Hospitalisation();
							        	 hospitalisation.setId(rs.getInt("ID_HOSPITALISATION"))
							        	 				.setDateEntree(rs.getDate("DATE_ENTREE"))
							        	 				.setDateSortie(rs.getDate("DATE_SORTIE"));
							        	 listHospitalisations.add(hospitalisation);
							        	 Patient patient = new Patient();
							        	 patient.setNom(rs.getString("NOM"))
							        	 		.setPrenom(rs.getString("PRENOM"));
							    
							        	 listPatients.add(patient);
							         }
									System.out.println("\n\t\t\t\t============================= Listes des Hospitalisations liées à un patient ============================\n");
									//Affichage des résultats
									Utils.listHospitalisations2Printing(listHospitalisations, listPatients);
									
					    		  } catch (SQLException e) {
								         e.printStackTrace();
								      }
									
					      }
									break;
									
					//Scénario 3: Liste des actes liées à une hospitalisation
					case 3:
						String QUERY3 = "select tp.ID_PATIENT, tp.NOM, tp.PRENOM, ta.ID_AKT, ta.DATE_ATK, ta.ANESTH, ta.ID_CCAM, tc.LIBELLE_CCAM \n"
								+ "from tab_patient tp, tab_hospitalisation th, tab_acte ta, tab_ccam tc \n"
								+ "where tp.ID_PATIENT = th.ID_PATIENT \n"
								+ "	 and th.ID_HOSPITALISATION = ta.ID_HOSPITALISATION \n"
								+ "	 and ta.ID_CCAM = tc.ID_CCAM \n"
								+ "	 and th.ID_HOSPITALISATION = ?;\n";
						
						// Open a connection
						System.out.print("\t\t Saisir l'ID de l'Hospitalisation : ");
						int ID_HOSPITALISATION= sc.nextInt();
					      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
					    		  try(PreparedStatement ps = conn.prepareStatement(QUERY3)) {
					    	       	  ps.setInt(1, ID_HOSPITALISATION);
					    	       	  ResultSet rs = ps.executeQuery();
					    	       	while (rs.next()) {
					 					 
										 Patient patient = new Patient();
										 patient.setId(rs.getInt("ID_PATIENT"))
							        	        .setNom(rs.getString("NOM"))
							        	 		 .setPrenom(rs.getString("PRENOM"));
							        	 listPatients.add(patient);
										 
										 Acte acte = new Acte();
							        	 acte.setId (rs.getInt("ID_AKT"))
							        	 	 .setIdCCAM(rs.getString("ID_CCAM"))
							        	     .setAnesth(rs.getByte("ANESTH"))
							        	     .setDate (rs.getDate("DATE_ATK"))
							        	     .setIdCCAM(rs.getString("ID_CCAM"));
							        	 listActes.add(acte);
							        	 
							        	 ThesaurusCCAM thesaurus_ccam = new ThesaurusCCAM();
							        	 thesaurus_ccam.setLibelle(rs.getString("LIBELLE_CCAM"));
							        	 libelleCCAM.add(thesaurus_ccam);
							        	 
							        	 
									 }
									
									System.out.println("\n\t\t\t\t============================= Listes des Actes liées à une hospitalisation ============================\n");
									//Affichage des résultats
									Utils.listActes2Printing(listPatients, listActes, libelleCCAM);
					    		  } catch (SQLException e) {
								         e.printStackTrace();
								      }
					      }
								break;
								
					//Scénario 4: Liste des diagnostics liées à une hospitalisation 		
					case 4: 
						String QUERY4 = "select tp.ID_PATIENT, tp.NOM, tp.PRENOM, td.ID_DIAGNOSTIC, td.DRANG, td.DGTYPE, td.CODE_CIM10, tc.LIBELLE_CIM110 \n"
								+ "from tab_patient tp, tab_hospitalisation th, tab_diagnostic td, tab_cim10 tc\n"
								+ "where tp.ID_PATIENT = th.ID_PATIENT \n"
								+ "	 and th.ID_HOSPITALISATION = td.ID_HOSPITALISATION \n"
								+ "	 and td.CODE_CIM10 = tc.ID_CIM10 \n"
								+ "	 and th.ID_HOSPITALISATION = ?;\n";
							
						// Open a connection
						System.out.print("\t\t Saisir l'ID de l'Hospitalisation : ");
						int ID_HOSPITALISATION2= sc.nextInt();
					      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
					    		  try(PreparedStatement ps = conn.prepareStatement(QUERY4)) {
					    	       	  ps.setInt(1, ID_HOSPITALISATION2);
					    	       	  ResultSet rs = ps.executeQuery();	
					    	       	while (rs.next()) {
										Patient patient = new Patient();
							             patient.setId(rs.getInt("ID_PATIENT"))
							                     .setNom(rs.getString("NOM"))
							                     .setPrenom(rs.getString("PRENOM"));
							             listPatients.add(patient);
							             
										Diagnostic diagnostic = new Diagnostic();
							        	 diagnostic.setId(rs.getInt("ID_DIAGNOSTIC"))
							        	           .setDrang(rs.getByte("DRANG"))
							        	           .setDgType(rs.getString("DGTYPE"))
							        	           .setCodeCIM10(rs.getString("CODE_CIM10"));
							             listDiagnostics.add(diagnostic);
							        	 
							             ThesaurusCIM10 thesaurus_CIM10 =new ThesaurusCIM10();
							             thesaurus_CIM10.setLibelle(rs.getString("LIBELLE_CIM110"));
							             libelleCIM10.add(thesaurus_CIM10);
							             
							             
							             
					    	       	  }
									
									System.out.println("\n\t\t\t\t============================= Liste des diagnostics liées à une hospitalisation ============================\n");
									//Affichage des résultats
									Utils.listDiagnostics2Printing(listPatients, listDiagnostics, libelleCIM10);
					    		  } catch (SQLException e) {
					 		         e.printStackTrace();
					 		      }
					      }
					 		break;						
					
		//Scénario 5: Nombre d'hospitalisation selon le type de diagnostic
		case 5: 
			String QUERY5 = "select count(th.ID_HOSPITALISATION) as nombreHospitalisation\n"
					+ "from tab_hospitalisation th\n"
					+ "inner join tab_diagnostic td\n"
					+ "on th.ID_HOSPITALISATION = td.ID_HOSPITALISATION \n"
					+ "where DGTYPE = ? ;\n";
			
					  // Open a connection
						System.out.print("\t\t Saisir le type de diagnostic recherché : ");
						String typeDG= sc.next();
					  	System.out.println(typeDG);
					    try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
					  	try(PreparedStatement ps = conn.prepareStatement(QUERY5)) {
					  	ps.setString(1, typeDG);
					  	ResultSet rs = ps.executeQuery();
					  	
					  	int nombreHospitalisation = 0;
		    	       	  while (rs.next()) {
		    	       		  nombreHospitalisation = rs.getInt("nombreHospitalisation");
				       	  } 
		    	       	System.out.printf("\n\t\t Nombre d'hospitalisation : %d \n ", nombreHospitalisation);
		    	       	
		    	       	
					  	} catch (SQLException e) {
					         e.printStackTrace();
					      }
					   }
			        break;
			        
			      //Scénario 6: ID des patients qui avaient moins de 50 ans le jour de leur hospitalisation selon le sexe
				case 6: 
				String QUERY6 = "select tp.ID_PATIENT\n"
						+ "from tab_patient tp \n"
						+ "inner join tab_hospitalisation th \n"
						+ "on tp.ID_PATIENT = th.ID_PATIENT \n"
						+ "where datediff(DATE_ENTREE, DATE_NAISSANCE)/365.25 <50 and sexe = ?;\n";
				
				// Open a connection
				System.out.print("\t\t Saisir le sexe du patient: ");
				String sexePatient= sc.next();
			    try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
			    		  try(PreparedStatement ps = conn.prepareStatement(QUERY6)) {
			    	       	  ps.setString(1, sexePatient);
			    	       	  ResultSet rs = ps.executeQuery();
			    	       	  
			    	       	while (rs.next()) {
			    	       		System.out.println("\n\t\t ID PATIENT : " + rs.getString("ID_PATIENT"));
					       	  } 
			    	       	
			    		  } catch (SQLException e) {
			 		         e.printStackTrace();
			 		      }
			 		      }
			 		break;
			 	
			 	//Scénario 7: Age moyen des patients  en fonction du sexe
			 	case 7:
			 		String QUERY7 = "select AVG(datediff(DATE_ENTREE, DATE_NAISSANCE)/365.25) as age_moyen\n"
							+ "from tab_patient tp inner join tab_hospitalisation th \n"
							+ "on tp.ID_PATIENT =th.ID_PATIENT\n"
							+ "where sexe=?;\n";
					
			 		// Open a connection
			 		System.out.print("\t\t Saisir le sexe du patient : ");
					String patientSexe= sc.next();
			 	    try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
			 	    		  try(PreparedStatement ps = conn.prepareStatement(QUERY7)) {
			 	    	       	  ps.setString(1, patientSexe);
			 	    	       	  ResultSet rs = ps.executeQuery();
			 	    	       	  
			 	    	       	double ageMoyen = 0;
				    	       	  while (rs.next()) {
				    	       		  ageMoyen = rs.getDouble("age_moyen");
						       	  } 
				    	       	System.out.printf("\n\t\t Age_Moyen : %.2f ans\n", ageMoyen);
				    	       	
			 	    		 } catch (SQLException e) {
			 	   	         e.printStackTrace();
			 	   	      }
			 	   	   }
			 	       break;	
			 	    //Scénario 8: Liste des hospitalisations et des diagnostics liées à un patient
			 		 case 8:
			 	     String QUERY8 = "select th.ID_HOSPITALISATION, th.DATE_ENTREE, th.DATE_SORTIE, td.ID_DIAGNOSTIC, tc.ID_CIM10, tc.LIBELLE_CIM110 \n"
								+ "from tab_hospitalisation th, tab_diagnostic td, tab_cim10 tc \n"
								+ "where th.ID_HOSPITALISATION = td.ID_HOSPITALISATION \n"
								+ "and td.CODE_CIM10 = tc.ID_CIM10 \n"
								+ "and th.ID_PATIENT = ?; \n";
						
			 	     // Open a connection
						System.out.print("\t\t Saisir l'ID du patient : ");
						int ID_PATIENT2= sc.nextInt();
			 	   		try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
			 	   		try(PreparedStatement ps = conn.prepareStatement(QUERY8)) {
			 	   		   	       	  ps.setInt(1, ID_PATIENT2);
			 	   		   	       	  ResultSet rs = ps.executeQuery();
			 	   		   
			 	   		   	   while (rs.next()) {
					    		  
				    	    	Hospitalisation hospitalisation = new Hospitalisation();
					        	 hospitalisation.setId(rs.getInt("ID_HOSPITALISATION"))
					        	                .setDateEntree(rs.getDate("DATE_ENTREE"))
					        	                .setDateSortie(rs.getDate("DATE_SORTIE"));
					          	 listHospitalisations.add(hospitalisation);
				    	    	
				    	    	
				    	    	Diagnostic diagnostic = new Diagnostic();
					        	 diagnostic.setId(rs.getInt("ID_DIAGNOSTIC"));
					             listDiagnostics.add(diagnostic);
					        	
					            ThesaurusCIM10 CIM10 = new ThesaurusCIM10();
					            CIM10.setId(rs.getString("ID_CIM10"))
					                 .setLibelle(rs.getString("LIBELLE_CIM110"));
					            libelleCIM10.add(CIM10);
					             	 
			    	       	  }
							 
							
							System.out.println("\n\t\t\t\t============================= Liste des hospitalisations et des diagnostics liées à un patient ============================\n");
							//Affichage des résultats
							Utils.listHospitalisations3Printing(listHospitalisations, listDiagnostics, libelleCIM10);
			 	   	} catch (SQLException e) {
				         e.printStackTrace();
				      }
			 	   		}
				break;
				
				//Scénario 9: Les patients n'ayant eu aucun acte en fonction du type de diagnostic
			 	   	case 9: 
			 	   		String QUERY9 = "select distinct tp.ID_PATIENT, tp.NOM, tp.PRENOM, th.DATE_ENTREE, th.DATE_SORTIE\n"
								+ "from tab_patient tp, tab_hospitalisation th, tab_diagnostic td\n"
								+ "where tp.ID_PATIENT = th.ID_PATIENT\n"
								+ "and th.ID_HOSPITALISATION = td.ID_HOSPITALISATION\n"
								+ "and td.DGTYPE = ?\n"
								+ "and tp.ID_PATIENT not in (\n"
								+ "				select th.ID_PATIENT\n"
								+ "				from tab_hospitalisation th inner join tab_acte ta\n"
								+ "				on th.ID_HOSPITALISATION = ta.ID_HOSPITALISATION);\n";
			 	   		
			 	   		// Open a connection
						System.out.print("\t\t Saisir le type de diagnostic : ");
						String typeDiagnostic= sc.next();
			 	   		   try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
			 	   		         try(PreparedStatement ps = conn.prepareStatement(QUERY9)) {
			 	   		         ps.setString(1, typeDiagnostic);
			 	   		         ResultSet rs = ps.executeQuery();
			 	   		         
			 	   		         
			 	   		     while (rs.next()) {
									Patient patient = new Patient();
									patient.setId(rs.getInt("ID_PATIENT"))
										   .setNom(rs.getString("NOM"))
									       .setPrenom(rs.getString("PRENOM"));
									listPatients.add(patient);
									
									Hospitalisation hospitalisation = new Hospitalisation();
									hospitalisation.setDateEntree(rs.getDate("DATE_ENTREE"))
									               .setDateSortie(rs.getDate("DATE_ENTREE"));
									listHospitalisations.add(hospitalisation);
									    		  }
								 
								
								System.out.println("\n\t\t\t\t============================= Les patients n'ayant eu aucun acte en fonction du type de diagnostic ============================\n");
								//Affichage des résultats
								Utils.listPatients2Printing(listPatients, listHospitalisations);
			 	   		     } catch (SQLException e) {
						         e.printStackTrace();
						      }
		   }
						break;
						
						default:
							break;
								        
		}

}
	
}
