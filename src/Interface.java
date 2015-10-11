/******************************************************************************************************
 * <i>[FR]</i>
 * Classe <b>Interface</b>
 * 		<p>Fenêtre principale de l'application.</p>
  ******************************************************************************************************
 * <i>[EN]</i>
 * Class <b>Interface</b>
 * 		<p>Main window of the application.</p>
  ******************************************************************************************************
 * @author Naomi Hauret
 * @version 1.0
 *******************************************************************************************************/
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public class Interface extends JFrame{
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//VARIABLES
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private static String FILE="fichierPasswordTrunk";
	private JTable tableau;
	private DefaultTableModel tDefaultTableModel;
	private ListSelectionModel selectionModel;
	private LesMotsDePasse lesMdp;
    private Object[][] data;
    private final String  title[] = {"Site web/Application","Identifiant/E-mail", "Mot de passe"};
	private JPanel panelBoutons;
	private JPanel panelFonctions;
	private JButton button_copierMDP;
	private JButton button_copierID;
	private JButton button_ajouter;
	private JButton button_modifier;
	private JButton button_supprimer;
	private JButton button_sauvegarder;
	private JButton button_quitter;
	private JPanel panelFiltrer;
	private JLabel  label_filtrer;
	private JTextField textField;
	private JPopupMenu popupmenu;
	private JMenuItem itemAjouter;
	private JMenuItem itemModifier;
	private JMenuItem itemSupprimer;
	private JMenuItem itemCopierMdp;
	private JMenuItem itemCopierId;
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//CONSTRUCTEUR
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Interface(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("icone.png")); 
		this.setTitle("Password Trunk");
		this.setSize(700,700);
		this.setMinimumSize(new Dimension(500,300));
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.lesMdp= new LesMotsDePasse();
		this.construireFenetre();
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//METHODES
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void construireFenetre(){
		//POPUPMENU
		this.popupmenu= new JPopupMenu();
		this.itemAjouter= new JMenuItem("Ajouter un mot de passe...");
		this.itemModifier= new JMenuItem("Modifier le mot de passe...");
		this.itemSupprimer= new JMenuItem("Supprimer le mot de passe");
		this.itemCopierMdp= new JMenuItem("Copier le mot de passe");
		this.itemCopierId= new JMenuItem("Copier l'identifiant");
		
		this.popupmenu.add(this.itemAjouter);
		this.popupmenu.addSeparator();
		this.popupmenu.add(this.itemModifier);
		this.popupmenu.addSeparator();
		this.popupmenu.add(this.itemSupprimer);
		this.popupmenu.addSeparator();
		this.popupmenu.add(this.itemCopierMdp);
		this.popupmenu.addSeparator();
		this.popupmenu.add(this.itemCopierId);

		//PANELBOUTONS
		this.panelBoutons= new JPanel();
		this.panelBoutons.setMinimumSize(new Dimension(360,300));
		this.panelBoutons.setLayout(new BoxLayout(this.panelBoutons,BoxLayout.PAGE_AXIS));
		this.panelFonctions= new JPanel();
		this.panelFonctions.setLayout(new BoxLayout(this.panelFonctions,BoxLayout.PAGE_AXIS));
		
		this.panelFiltrer= new JPanel();
		this.panelFiltrer.setLayout(new BoxLayout(this.panelFiltrer, BoxLayout.LINE_AXIS));
		this.label_filtrer =new JLabel("Filtrer par site: ");
		this.textField= new JTextField();
		this.textField.setMaximumSize(new Dimension(400,20));
		
		this.panelFiltrer.add(Box.createRigidArea(new Dimension(50,0)));
		this.panelFiltrer.add(this.label_filtrer);
		this.panelFiltrer.add(Box.createRigidArea(new Dimension(10,0)));
		this.panelFiltrer.add(this.textField);
		this.panelFiltrer.add(Box.createRigidArea(new Dimension(10,0)));

		this.button_copierID= new JButton("Copier l'identifiant");
		this.button_copierMDP= new JButton("Copier le mot de passe");
		this.button_ajouter= new JButton("Ajouter un mot de passe...");
		this.button_modifier= new JButton("Modifier le mot de passe...");
		this.button_supprimer= new JButton("Supprimer le mot de passe");
		this.button_sauvegarder= new JButton("Sauvegarder");
		this.button_quitter= new JButton("Quitter");
		
		
		//--tailles
		this.button_copierID.setMaximumSize(new Dimension(200,35));
		this.button_copierMDP.setMaximumSize(new Dimension(200,35));
		this.button_ajouter.setMaximumSize(new Dimension(200,35));
		this.button_modifier.setMaximumSize(new Dimension(200,35));
		this.button_supprimer.setMaximumSize(new Dimension(200,35));
		this.button_sauvegarder.setMaximumSize(new Dimension(200,35));
		this.button_quitter.setMaximumSize(new Dimension(200,35));
		
		//panelBoutons
		this.panelBoutons.add(Box.createRigidArea(new Dimension(0,30)));
		this.panelBoutons.add(this.button_ajouter);
		this.panelBoutons.add(Box.createRigidArea(new Dimension(0,20)));
		this.panelBoutons.add(this.button_modifier);
		this.panelBoutons.add(Box.createRigidArea(new Dimension(0,20)));
		this.panelBoutons.add(this.button_supprimer);
		this.panelBoutons.add(Box.createRigidArea(new Dimension(0,30)));
		this.panelBoutons.add(this.button_copierMDP);
		this.panelBoutons.add(Box.createRigidArea(new Dimension(0,20)));
		this.panelBoutons.add(this.button_copierID);
		this.panelBoutons.add(Box.createRigidArea(new Dimension(0,30)));
		this.panelBoutons.add(this.button_sauvegarder);
		this.panelBoutons.add(Box.createRigidArea(new Dimension(0,20)));
		this.panelBoutons.add(this.button_quitter);
		
		//--construction
		this.panelFonctions.add(Box.createVerticalGlue());
		this.panelFonctions.add(this.panelFiltrer);
		this.panelFonctions.add(Box.createRigidArea(new Dimension(0,10)));
		this.panelFonctions.add(this.panelBoutons);
		this.panelFonctions.add(Box.createVerticalGlue());


		//TABLEAU
		this.tDefaultTableModel= new DefaultTableModel(this.data,this.title);
		this.tableau= new JTable(this.tDefaultTableModel);
	    this.tableau.setRowSelectionAllowed(true);
	    this.selectionModel = tableau.getSelectionModel();
	    this.selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//CONSTRUCTION
		this.add(new JScrollPane(this.tableau), BorderLayout.CENTER);
		this.add(this.panelFonctions, BorderLayout.EAST);
		
		//ECOUTEURS
		this.itemAjouter.addActionListener(new Clic());
		this.itemCopierId.addActionListener(new Clic());
		this.itemCopierMdp.addActionListener(new Clic());
		this.itemModifier.addActionListener(new Clic());
		this.itemSupprimer.addActionListener(new Clic());
		this.button_ajouter.addActionListener(new Clic());
		this.button_copierMDP.addActionListener(new Clic());
		this.button_copierID.addActionListener(new Clic());
		this.button_modifier.addActionListener(new Clic());
		this.button_supprimer.addActionListener(new Clic());
		this.button_sauvegarder.addActionListener(new Clic());
		this.button_quitter.addActionListener(new Clic());
		this.textField.addKeyListener(new Clavier());
		this.tDefaultTableModel.addTableModelListener(new Tableau());
		this.tableau.addMouseListener((new Souris()));
		this.addMouseListener((new Souris()));
		
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public class Clavier implements KeyListener{
		//****************************************************
		@Override
		public void keyPressed(KeyEvent e) {
			
		}
		
		//****************************************************
		@Override
		public void keyReleased(KeyEvent e) {
			
		}
		
		//****************************************************
		@Override
		public void keyTyped(KeyEvent e) {
			if(e.getSource()==textField){
				filtrer();
			}
		//****************************************************
		}	
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public class Clic implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==button_quitter){
				dispose();
			}
			//****************************************************************************************************************************************
			if(e.getSource()==button_copierMDP ||e.getSource()==itemCopierMdp ){
				if(selectionModel.isSelectionEmpty()){
					JOptionPane.showMessageDialog( null, "Aucun mot de passe n'a été sélectionné! Veuillez en sélectionner un dans le tableur.","Erreur: copie du mot de passe impossible", JOptionPane.ERROR_MESSAGE);
				}
				else{
					int i=0;
					boolean trouve=false;
					while(trouve==false && i< tDefaultTableModel.getRowCount()){
						if(selectionModel.isSelectedIndex(i)){
							trouve=true;
							Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					        StringSelection data = new StringSelection((String) tDefaultTableModel.getValueAt(i, 1));
					        clipboard.setContents (data, data);
							
						}
						else{
							i++;
						}
					}
					
				}
			}
			
			//****************************************************************************************************************************************
			if(e.getSource()==button_copierID ||e.getSource()==itemCopierId){
				if(selectionModel.isSelectionEmpty()){
					JOptionPane.showMessageDialog( null, "Aucun mot de passe n'a été sélectionné! Veuillez en sélectionner un dans le tableur.","Erreur: copie de l'identifiant impossible", JOptionPane.ERROR_MESSAGE);
				}
				else{
					int i=0;
					boolean trouve=false;
					while(trouve==false && i< tDefaultTableModel.getRowCount()){
						
						if(selectionModel.isSelectedIndex(i)){
							trouve=true;
							Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					        StringSelection data = new StringSelection((String) tDefaultTableModel.getValueAt(i, 1));
					        clipboard.setContents (data, data);
						}
						else{
							i++;
						}
					}
					
				}
			}
			
			//****************************************************************************************************************************************	
			if(e.getSource()==button_sauvegarder){
				
				try {
					sauver(FILE, getLesMotsDePasse());
				} 
				
				catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog( null, "Erreur: le fichier fichierPasswordTrunk n'a pu être localisé ","Erreur", JOptionPane.ERROR_MESSAGE);
				} 
				
				catch (IOException e1) {
					JOptionPane.showMessageDialog( null, "Erreur: le fichier fichierPasswordTrunk n'a pu être localisé ","Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
			//****************************************************************************************************************************************			

			if(e.getSource()==button_ajouter||e.getSource()==itemAjouter){
				Principale.ouvrirAjout();
			}
			
			//****************************************************************************************************************************************
			if(e.getSource()==button_modifier||e.getSource()==itemModifier){
				if(selectionModel.isSelectionEmpty()){
					JOptionPane.showMessageDialog( null, "Aucun mot de passe n'a été sélectionné! Veuillez en sélectionner un dans le tableur.","Erreur: modification impossible", JOptionPane.ERROR_MESSAGE);
				}
				else{
					int i=0;
					boolean trouve=false;
					while(trouve==false && i< tDefaultTableModel.getRowCount()){
						if(selectionModel.isSelectedIndex(i)){
							trouve=true;
							Principale.ouvrirModification(tDefaultTableModel.getValueAt(i, 0)+" "+tDefaultTableModel.getValueAt(i, 1)+" "+tDefaultTableModel.getValueAt(i, 2), i);
						}
						else{
							i++;
						}
					}
					
				}
				
			}
			
			//****************************************************************************************************************************************
			if(e.getSource()==button_supprimer ||e.getSource()== itemSupprimer){
				if(selectionModel.isSelectionEmpty()){
					JOptionPane.showMessageDialog( null, "Aucun mot de passe n'a été sélectionné! Veuillez en sélectionner un dans le tableur.","Erreur: suppression impossible", JOptionPane.ERROR_MESSAGE);
				}
				else{
					int i=0;
					boolean trouve=false;
					while(trouve==false && i< tDefaultTableModel.getRowCount()){
						if(selectionModel.isSelectedIndex(i)){
							trouve=true;
							tDefaultTableModel.removeRow(i);
							lesMdp.retireMotDePasse(i);
						}
						else{
							i++;
						}
					}
					
				}
			}
			//****************************************************************************************************************************************
			if(e.getSource()==button_sauvegarder){
				try {
					sauver("fichierPasswordTrunk", lesMdp);
				} catch (IOException e1) {

					JOptionPane.showMessageDialog( null, "La sauvegarde est impossible. Veuillez relancer PasswordTrunk","Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public class Tableau implements TableModelListener{
		//****************************************************

		@Override
		public void tableChanged(TableModelEvent e) {
			if(tDefaultTableModel.getRowCount()<=0){
				
				setSauvegarder(false);
				setTextFieldFiltrer(false);
			}
			else{
				setTextFieldFiltrer(true);
			}
			setActions();
			
			if(e.getSource()==tDefaultTableModel){
				setSauvegarder(true);
			}
			
		}
		
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public class Souris implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			
			
		}
		//****************************************************
		@Override
		public void mouseEntered(MouseEvent e) {
			
		}
		//****************************************************
		@Override
		public void mouseExited(MouseEvent e) {
			
		}
		//****************************************************
		@Override
		public void mousePressed(MouseEvent e) {
			
		}
		//****************************************************
		@Override
		public void mouseReleased(MouseEvent e) {
			showPopup(e);
		}
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 * <b>setAjouter</b> change l'état du bouton button_ajouter // change the state of the button button_ajouter
	 * 
	 * @param b <i>état du bouton (activé ou désactivé) // state of the button (enabled or disabled)</i>
	 */
	
	public void setAjouter(boolean b){
		this.button_ajouter.setEnabled(b);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 * <b>setSupprimer</b> change l'état du bouton button_supprimer // change the state of the button button_supprimer
	 * 
	 * @param b <i>état du bouton (activé ou désactivé) // state of the button (enabled or disabled)</i>
	 */
	
	public void setSupprimer(boolean b){
		this.button_supprimer.setEnabled(b);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 * <b>setModifier</b> change l'état du bouton button_modifier // change the state of the button button_modifier
	 * 
	 * @param b <i>état du bouton (activé ou désactivé) // state of the button (enabled or disabled)</i>
	 */
	
	public void setModifier(boolean b){
		this.button_modifier.setEnabled(b);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 * <b>setCopierMDP</b> change l'état du bouton button_copierMDP // change the state of the button button_copierMDP
	 * 
	 * @param b <i>état du bouton (activé ou désactivé) // state of the button (enabled or disabled)</i>
	 */
	
	public void setCopierMDP(boolean b){
		this.button_copierMDP.setEnabled(b);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 * <b>setCopierID</b> change l'état du bouton button_copierID // change the state of the button button_copierID
	 * 
	 * @param b <i>état du bouton (activé ou désactivé) // state of the button (enabled or disabled)</i>
	 */
	
	public void setCopierID(boolean b){
		this.button_copierID.setEnabled(b);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 * <b>setQuitter</b> change l'état du bouton button_quitter // change the state of the button button_quitter
	 * 
	 * @param b <i>état du bouton (activé ou désactivé) // state of the button (enabled or disabled)</i>
	 */
	
	public void setQuitter(boolean b){
		this.button_quitter.setEnabled(b);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 * <b>setSauvegarder</b> change l'état du bouton button_sauvegarder // change the state of the button button_sauvegarder
	 * 
	 * @param b <i>état du bouton (activé ou désactivé) // state of the button (enabled or disabled)</i>
	 */
	
	public void setSauvegarder(boolean b){
		this.button_sauvegarder.setEnabled(b);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 * <b>setActions</b> change l'état des boutons et du champ de texte en fonction du nombre de lignes dans le tableau // change the state of the buttons and the textfield depending of the number of rows in the table
	 * 
	 */
	
	public void setActions(){
		this.setAjouter(true);
		this.setSauvegarder(true);
		this.setQuitter(true);
		
		if(this.tDefaultTableModel.getRowCount()<=0){
			this.setSupprimer(false);
			this.setCopierMDP(false);
			this.setCopierID(false);
			this.setModifier(false);
			this.setTextFieldFiltrer(false);
		}
		
		else{
			this.setSupprimer(true);
			this.setCopierMDP(true);
			this.setCopierID(true);
			this.setModifier(true);
			this.setTextFieldFiltrer(true);

		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 * <b>setTextFieldFiltrer</b> change l'état du champ de texte textField // change the state of the textfield textField
	 * 
	 * @param b <i>état du champ de texte (activé ou désactivé) // state of the textfield (enabled or disabled)</i>
	 */
	
	public void setTextFieldFiltrer(boolean b){
		this.textField.setEnabled(b);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 * <b>ajoutElement</b> ajoute une ligne au tableau (site/application, identifiant, mot de passe) //add a row to the table (website/application, login, password)
	 * 
	 * @param t <i> tableau de chaînes de caractère comprenant à l'index 0: le site/application; 1: l'indentifiant 2: le mot de passe // table of string comporting at index 0: the website/application; 1: the loggin; 2: the password </i>
	 */
	
	public void ajoutElement(String[] t){
		this.lesMdp.ajoutMotDePasse(t[0], t[1], t[2]);
		this.tDefaultTableModel.addRow(t);

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 * <b>modifierElement</b> modifie une ligne du tableau // edit a row of the table
	 * 
	 * @param t <i> tableau de chaînes de caractère comprenant à l'index 0: le site/application; 1: l'indentifiant 2: le mot de passe // table of string comporting at index 0: the website/application; 1: the loggin; 2: the password </i>
	 * @param i <i>index du mot de passe à modifier // index of the password to edit</i>
	 *
	 */
	public void modifierElement(String[] t, int i){
			this.tDefaultTableModel.setValueAt(t[0], i, 0);
			this.tDefaultTableModel.setValueAt(t[1], i, 1);
			this.tDefaultTableModel.setValueAt(t[2], i, 2);
			this.lesMdp.modifierMotDePasse(i,t);
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 *
	 * 
	 * @return
	 */
	
	public LesMotsDePasse getLesMotsDePasse(){
		return this.lesMdp;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void setLesMotsDePasse(LesMotsDePasse mdp){
		this.lesMdp= mdp;
	}
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void remplirElementTableau(String[] t){
		this.tDefaultTableModel.addRow(t);
	
	}
		
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void remplirTableau(){
		int i;
		String[] leMdpEntier;
		 
		for(i=0;i<this.lesMdp.size(); i++){
			leMdpEntier=this.lesMdp.getLeMdpAtIndex(i);
			this.remplirElementTableau(leMdpEntier);
		}
		
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	private void showPopup(MouseEvent e) {
		if (e.isPopupTrigger()) {
			this.popupmenu.show(e.getComponent(),
			e.getX(), e.getY());
		}
	}	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void filtrer(){
		String text = this.textField.getText();
		TableRowSorter<TableModel> sorter =  new TableRowSorter<TableModel>(this.tDefaultTableModel);
        this.tableau.setRowSorter(sorter);
        
        if (text.length() == 0) {
          sorter.setRowFilter(null);
        } else {
          try {
            sorter.setRowFilter(
                RowFilter.regexFilter(text));
          } catch (PatternSyntaxException pse) {
            System.err.println("Bad regex pattern");
          }
        }
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void sauver(String nomFic, LesMotsDePasse lmp) throws FileNotFoundException, IOException{
		
		ObjectOutputStream sortie = new ObjectOutputStream(new FileOutputStream(nomFic));
		sortie.writeObject(lmp);
		sortie.close();
	}
}
