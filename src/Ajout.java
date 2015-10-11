/******************************************************************************************************
 * <i>[FR]</i>
 * Classe <b>Ajout</b>
 * 		<p>Boîte de dialogue permettant à l'utilisateur d'ajouter un mot de passe.</p>
  ******************************************************************************************************
 * <i>[EN]</i>
 * Class <b>Ajout</b>
 * 		<p>Dialog window that allows the user to add a password.</p>
  ******************************************************************************************************
 * @author Naomi Hauret
 * @version 1.0
 *******************************************************************************************************/
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public class Ajout extends JDialog{
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//VARIABLES
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private JPanel contentPane;
	private JPanel panelMdp;
	private JPanel panelId;
	private JPanel panelSite;
	private JLabel label_mdp;
	private JTextField textField_mdp;
	private JLabel label_id;
	private JTextField textField_id;
	private JLabel label_site;
	private JTextField textField_site;
	private JPanel panelBoutons;
	private JButton button_ajouter;
	private JButton button_annuler;
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//CONSTRUCTEUR
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Ajout(){
		this.setLocationRelativeTo(null);
		this.setTitle("Ajouter un mot de passe...");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(new Dimension(500,250));
		this.setResizable(false);
		this.setModal(true);
		this.construireFenetre();
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//METHODES
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void construireFenetre(){
		
		//panelSite
		this.panelSite= new JPanel();
		this.panelSite.setLayout(new BoxLayout(this.panelSite, BoxLayout.LINE_AXIS));
		this.label_site= new JLabel("Site ou application:");
		this.textField_site= new JTextField();
		this.textField_site.setMaximumSize(new Dimension(330,20));
		this.panelSite.add(Box.createRigidArea(new Dimension(5,0)));
		this.panelSite.add(this.label_site);
		this.panelSite.add(Box.createRigidArea(new Dimension(25,0)));
		this.panelSite.add(this.textField_site);

		
		//panelId
		this.panelId= new JPanel();
		this.panelId.setLayout(new BoxLayout(this.panelId, BoxLayout.LINE_AXIS));
		this.label_id= new JLabel("Identifiant ou e-mail:");
		this.textField_id= new JTextField();
		this.textField_id.setMaximumSize(new Dimension(330,20));
		this.panelId.add(Box.createRigidArea(new Dimension(5,0)));
		this.panelId.add(this.label_id);
		this.panelId.add(Box.createRigidArea(new Dimension(18,0)));
		this.panelId.add(this.textField_id);

		//panelMdp
		this.panelMdp= new JPanel();
		this.panelMdp.setLayout(new BoxLayout(this.panelMdp, BoxLayout.LINE_AXIS));
		this.label_mdp= new JLabel("Mot de passe:");
		this.textField_mdp= new JTextField();
		this.textField_mdp.setMaximumSize(new Dimension(330,20));
		this.panelMdp.add(Box.createRigidArea(new Dimension(5,0)));
		this.panelMdp.add(this.label_mdp);
		this.panelMdp.add(Box.createRigidArea(new Dimension(54,0)));
		this.panelMdp.add(this.textField_mdp);

		
		//panelBoutons
		this.panelBoutons= new JPanel();
		this.panelBoutons.setLayout(new BoxLayout(this.panelBoutons, BoxLayout.LINE_AXIS));
		this.button_ajouter= new JButton("Ajouter");
		this.button_annuler= new JButton("Annuler");
		
		this.panelBoutons.add(Box.createRigidArea(new Dimension(300,0)));
		this.panelBoutons.add(this.button_ajouter);
		this.panelBoutons.add(Box.createRigidArea(new Dimension(20,0)));
		this.panelBoutons.add(this.button_annuler);

		//construction
		this.contentPane= new JPanel();
		this.contentPane.setLayout(new BoxLayout(this.contentPane, BoxLayout.PAGE_AXIS));
		this.contentPane.add(Box.createRigidArea(new Dimension(0,30)));
		this.contentPane.add(this.panelSite);
		this.contentPane.add(Box.createRigidArea(new Dimension(0,20)));
		this.contentPane.add(this.panelId);
		this.contentPane.add(Box.createRigidArea(new Dimension(0,20)));
		this.contentPane.add(this.panelMdp);
		this.contentPane.add(Box.createRigidArea(new Dimension(0,30)));
		this.contentPane.add(new JSeparator());
		this.contentPane.add(this.panelBoutons);
		this.contentPane.add(Box.createRigidArea(new Dimension(0,20)));

		this.setContentPane(this.contentPane);
		
		//ECOUTEURS
		this.button_ajouter.addActionListener(new Clic());
		this.button_annuler.addActionListener(new Clic());
		this.textField_id.addKeyListener(new Clavier());
		this.textField_mdp.addKeyListener(new Clavier());
		this.textField_site.addKeyListener(new Clavier());
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public class Clic implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==button_ajouter){
				Principale.demandeAjout(textField_site.getText(), textField_id.getText(), textField_mdp.getText());
				
				dispose();
			}
			
			//****************************************************************************************************************************************
			if(e.getSource()==button_annuler){
				if(!(textField_id.getText().isEmpty()) && !(textField_mdp.getText().isEmpty()) & !(textField_site.getText().isEmpty())){
					
					int d= JOptionPane.showConfirmDialog( null, "Voulez-vous vraiment annuler? Ce mot de passe ne sera pas ajouté.","Attention", JOptionPane.YES_NO_OPTION);
					if(d==JOptionPane.YES_OPTION){
						dispose();
					}
				}
				else{
					dispose();
				}
			}
			
		}
		
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public class Clavier implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			if(textField_id.getText().isEmpty() || textField_mdp.getText().isEmpty() || textField_site.getText().isEmpty()){
				activeAjout(false);
			}
			else{
				activeAjout(true);
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			
		}
		
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void activeAjout(boolean b){
		this.button_ajouter.setEnabled(b);
	}
}
