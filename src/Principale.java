/******************************************************************************************************
 * <i>[FR]</i>
 * Classe <b>Principale</b>
 * 		<p>Classe qui gère et contrôle toutes fenêtres de l'application.</p>
  ******************************************************************************************************
 * <i>[EN]</i>
 * Class <b>Principale</b>
 * 		<p>Class that runs and controls all the windows of the application.</p>
  ******************************************************************************************************
 * @author Naomi Hauret
 * @version 1.0
 *******************************************************************************************************/
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javax.swing.JOptionPane;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public class Principale implements Serializable{
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//VARIABLES
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private static String FILE="fichierPasswordTrunk";
	static Interface i;
	static Ajout a;
	static Modification m;
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//MAIN
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * <b>Main</b>
	 * @param args
	 * @throws FileNotFoundException <i>si la tentative d'ouverture du fichier  "fichierPasswordTrunk" a échoué // if the attempt to open the "fichierPasswordTrunk" file has failed</i>
	 * @throws ClassNotFoundException <i>si la tentative de charger la classe LesMotsDePasse a échoué // if the attempt to load the class LesMotsDePasse has failed @see LesMotsDePasse</i>
	 * @throws IOException <i>si des erreurs d'entrée/sortie apparaissent // if input/output errors occur</i>
	 */
	
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException{ 
		
		i= new Interface();
		
		try{
			LesMotsDePasse mdp= i.getLesMotsDePasse(); 
			 mdp=(LesMotsDePasse)restitue(FILE);
			 i.setLesMotsDePasse(mdp);
			 i.remplirTableau();

		 }
		 catch(FileNotFoundException  e){
			JOptionPane.showMessageDialog( null, "Le fichier contenant les mots de passe est absent et va donc être créé.","Fichier absent", JOptionPane.INFORMATION_MESSAGE);

		 }
		
		ouvrirInterface();
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//METHODES
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * <b>ouvrirInterface</b>
	 * 
	 * active/désactive les boutons de la fenêtre Interface et l'ouvre // activates/desactivates the buttons of the Interface window and opens it
	 *
	 * @see Interface
	 */
	public static void ouvrirInterface(){
		i.setActions();
		i.setIconImage(Toolkit.getDefaultToolkit().getImage("icone.png")); 
		i.setVisible(true);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * <b>ouvrirAjout</b>
	 * 
	 * active/désactive les boutons de la fenêtre Ajout et l'ouvre // activates/desactivates the buttons of the Ajout window and opens it
	 *
	 * @see Ajout
	 */
	public static void ouvrirAjout(){
	    a= new Ajout();
		a.activeAjout(false);
		a.setVisible(true);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * <b>ouvrirModification</b>
	 * 
	 * ouvre la fenêtre Modification avec les informations du mot de passe que l'utilisateur souhaite modifier (site/application, identifiant, mot de passe) // opens the Modification window with the information of the password the user wants to edit (website/application, login, password)
	 *
	 * @see Modification
	 * @param element  <i>une chaîne de caractère comportant le site/l'application, l'identifiant et le mot de passe // a string that includes the website/application, the login and the password</i>
	 * @param index <i>l'index du mot de passe à modifier // index of the password to edit </i>
	 */
	
	public static void ouvrirModification(String element, int index){
		m = new Modification();
		String[] cells = new String[3];
		cells= element.split(" ");
		m.setTextFields(cells[0], cells[1], cells[2]);
		m.activeModification(false);
		m.setIndex(index);
		m.setVisible(true);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * <b>demandeAjout</b>
	 * 
	 * demande à la fenêtre Interface d'ajouter un mot de passe // asks the Interface window to add a password
	 * 
	 * @param site <i> nom du site/application // name of the website/application </i>
	 * @param id <i> identifiant associé à ce site/cette application // login associated to this website/application </i>
	 * @param mdp <i>  mot de passe// password </i>
	 */
	public static void demandeAjout(String site, String id, String mdp){
		String[] donnees={site, id, mdp};
		i.ajoutElement(donnees);		
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * <b>demandeModification</b>
	 * 
	 * demande à la fenêtre Interface de modifier les informations du mot de passe à l'index précisé // asks the Interface window to edit the information of the password at the said index
	 * 
	 * @param site <i> nom du site/application // name of the website/application </i>
	 * @param id <i> identifiant associé à ce site/cette application // login associated to this website/application </i>
	 * @param mdp <i>  mot de passe// password </i>
	 * @param index <i>l'index du mot de passe à modifier//index of the password to edit </i>
	 */
	public static void demandeModification(String site, String id, String mdp, int index){
		String[] donnees={site, id, mdp};
		i.modifierElement(donnees,index);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * <b>restitue</b>
	 * 
	 * 
	 * 
	 * @param nomF <i> nom du fichier à lire// </i>
	 * @return <i> l'instance de LesMotsDePasse contenue dans le fichier nomF // intance of LesMotsDePasse contained in the nomF file</i>
	 * @throws FileNotFoundException <i>si la tentative d'ouverture du fichier  "fichierPasswordTrunk" a échoué // if the attempt to open the "fichierPasswordTrunk" file has failed</i>
	 * @throws ClassNotFoundExceptio n<i> si la tentative de charger la classe LesMotsDePasse a échoué // if the attempt to load the class LesMotsDePasse has failed @see LesMotsDePasse</i>
	 * @throws IOException <i> si des erreurs d'entrée/sortie apparaissent // if input/output errors occur</i>
	 */
	public static LesMotsDePasse restitue(String nomF) throws FileNotFoundException, IOException, ClassNotFoundException{
		
		ObjectInputStream entree = new ObjectInputStream(new FileInputStream(nomF));
		LesMotsDePasse lmdp=(LesMotsDePasse)entree.readObject();
		entree.close();
		return lmdp;
		
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
