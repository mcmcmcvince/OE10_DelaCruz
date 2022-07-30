package copl_ex1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;


public class login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public login() {
		setBackground(Color.WHITE);
		setFont(new Font("Arial", Font.BOLD, 12));
		setTitle("LOGIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 295);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(174, 62, 308, 27);
		txtUsername.setBackground(new Color(245, 245, 245));
		txtUsername.setToolTipText("");
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(174, 115, 308, 27);
		txtPassword.setBackground(new Color(245, 245, 245));
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(174, 163, 135, 23);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					Connection con =(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/copl_db","root","");
					Statement stmt =(Statement) con.createStatement();
					
					String sql = "Select * from users_tbl where username='"+ txtUsername.getText().toString()+"' and password='"+ txtPassword.getText()+"'";
					
					ResultSet rs = ((java.sql.Statement)stmt).executeQuery(sql);
					
					if(rs.next()) {
						String userName = txtUsername.getText();
						Dashboard frmtwo = new Dashboard();
						frmtwo.lbluserT.setText(userName);
						frmtwo.setVisible(true);
						dispose();
						
					JOptionPane.showMessageDialog(null, "Login successful...","Login Alert",2);
				}else if (txtUsername.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Username is required...","Login Warning",2);
				}else if (txtPassword.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Password is required...","Login Warning",2);
				}else {
					JOptionPane.showMessageDialog(null, "Username or password incorrect...","Login Warning",2);
				}
			}catch(Exception ex) {
				System.out.print(ex);
			}
				
			}
		});
		contentPane.add(btnLogin);
		
		JButton btnCancel = new JButton("EXIT");
		btnCancel.setBounds(347, 163, 135, 23);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(btnCancel);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Mcvince Paul\\Dropbox\\My PC (LAPTOP-87L8MGVC)\\Downloads\\key (1).png"));
		lblNewLabel.setBounds(10, 42, 152, 171);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("USERNAME:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(174, 37, 104, 23);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("PASSWORD:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(174, 92, 104, 23);
		contentPane.add(lblNewLabel_2);
	}
}
