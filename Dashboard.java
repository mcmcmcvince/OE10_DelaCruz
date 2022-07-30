package copl_ex1;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Dashboard extends JFrame {

	private JPanel contentPane;
	JLabel lbluserT;
	private JTextField txtStudentNo;
	private JTextField txtStudentName;
	private JTextField txtAddress;
	private JTextField textField_3;
	private JTable tableView;
	@SuppressWarnings("rawtypes")
	private JComboBox txtGender;
	private JTextField txtContactNo;
	private JLabel lblClock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Dashboard() {
		setTitle("Student Record System");
		initialize();
		clock();
		viewRecords();
	}
	
	/**
	 * Create the frame.
	 * @return 
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1090, 531);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lbluserT = new JLabel();
		lbluserT.setBounds(51, 23, 151, 29);
		lbluserT.setFont(new Font("Microsoft Himalaya", Font.BOLD, 30));
		contentPane.add(lbluserT);
		
		JLabel lblNewLabel = new JLabel("Student Record System");
		lblNewLabel.setBounds(51, 76, 264, 39);
		lblNewLabel.setFont(new Font("Microsoft Himalaya", Font.BOLD, 32));
		contentPane.add(lblNewLabel);
		
		txtStudentNo = new JTextField();
		txtStudentNo.setBounds(149, 127, 197, 34);
		contentPane.add(txtStudentNo);
		txtStudentNo.setColumns(10);
		
		txtStudentName = new JTextField();
		txtStudentName.setBounds(149, 171, 197, 34);
		txtStudentName.setColumns(10);
		contentPane.add(txtStudentName);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(149, 223, 197, 34);
		txtAddress.setColumns(10);
		contentPane.add(txtAddress);
		
		JLabel lblNewLabel_1 = new JLabel("Student Number");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(31, 128, 108, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Student Name");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(31, 179, 98, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Student Address");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_2.setBounds(31, 231, 108, 14);
		contentPane.add(lblNewLabel_1_2);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnUpdate.setBounds(496, 381, 89, 23);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableView.getSelectedRow()>= 0 ) {
					updateRecord(txtStudentNo.getText());
				}
			}
		});
		contentPane.add(btnUpdate);
		
		JButton btnView = new JButton("REFRESH");
		btnView.setBounds(388, 381, 86, 23);
		btnView.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtStudentNo.setEnabled(true);
				viewRecords();
			}
		});
		contentPane.add(btnView);
		
		JButton btnNewButton_2 = new JButton("ADD");
		btnNewButton_2.setBounds(149, 350, 89, 23);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addRecord();
			}
		});
		contentPane.add(btnNewButton_2);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnDelete.setBounds(787, 381, 89, 23);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableView.getSelectedRow()>= 0 ) {
					deleteRecord(txtStudentNo.getText());
				}
			}
		});
		contentPane.add(btnDelete);
		
		JButton btnNewButton_3 = new JButton("CLEAR");
		btnNewButton_3.setBounds(257, 350, 89, 23);
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTextfield();
			}
		});
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("EXIT");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_4.setBounds(886, 381, 89, 23);
		contentPane.add(btnNewButton_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(416, 83, 460, 23);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(356, 119, 619, 246);
		contentPane.add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		tableView = new JTable();
		scrollPane.setViewportView(tableView);
		tableView.setColumnSelectionAllowed(true);
		tableView.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				txtStudentNo.setEnabled(false);
				String id = tableView.getValueAt(tableView.getSelectedRow(), 0).toString();
				textField(id);
			}
		});
		tableView.setFont(new Font("Tahoma", Font.PLAIN, 10));
		tableView.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tableView.setBackground(new Color(255, 182, 193));
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(180, 106, 80, 75);
		lblNewLabel_2.setIcon(new ImageIcon("D:\\ICONS COPL\\medical-record.png"));
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_5 = new JButton("Search");
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_5.setBounds(886, 83, 89, 21);
		contentPane.add(btnNewButton_5);
		
		txtGender = new JComboBox();
		txtGender.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtGender.setBounds(149, 268, 197, 22);
		txtGender.setModel(new DefaultComboBoxModel(new String[] {"Choose", "Male", "Female", "Other"}));
		contentPane.add(txtGender);
		
		txtContactNo = new JTextField();
		txtContactNo.setBounds(149, 301, 197, 34);
		contentPane.add(txtContactNo);
		txtContactNo.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Gender");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(31, 272, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Contact Number");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(31, 301, 108, 30);
		contentPane.add(lblNewLabel_4);
		
		lblClock = new JLabel("");
		lblClock.setFont(new Font("Microsoft Himalaya", Font.BOLD, 30));
		lblClock.setBounds(685, 23, 323, 29);
		contentPane.add(lblClock);
		
		JLabel lblNewLabel_4_1 = new JLabel("Search");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4_1.setBounds(356, 87, 86, 14);
		contentPane.add(lblNewLabel_4_1);
	}
	

	//db connection
		static Connection connect() {
			try {
				String myDriver = "com.mysql.cj.jdbc.Driver";
				//connection string
				String url = "jdbc:mysql://localhost:3306/copl_db";
				Class.forName(myDriver);
				return (Connection)DriverManager.getConnection(url,"root","");
			}catch(Exception e) {
				System.out.print("Cannot connect to the database");
			}
			return null;
		}
		//add record
		public void addRecord() {
			Connection con = connect();
			Calendar date = Calendar.getInstance();
			java.sql.Date datecreated = new java.sql.Date(date.getTime().getTime());
			
			try {
				String sql = "INSERT INTO student_tbl (student_no, student_name,gender,contact_no,address,date_created) VALUES (?,?,?,?,?,NOW())";
				PreparedStatement ps =(PreparedStatement) con.prepareStatement(sql);
				
				ps.setString(1,txtStudentNo.getText());
				ps.setString(2,txtStudentName.getText());
				ps.setString(3,txtGender.getSelectedItem().toString());
				ps.setString(4,txtContactNo.getText());
				ps.setString(5,txtAddress.getText());
				ps.execute();
				
				
				JOptionPane.showMessageDialog(null,"Record successfully added...");
				clearTextfield();
			}catch(Exception e) {
				System.out.print("Error... add" + e);
			}
		}
		//View Records
		public void viewRecords() {
			// TODO Auto-generated method stub
			Connection con = connect();
			DefaultTableModel mod = new DefaultTableModel();
			mod.addColumn("Id");
			mod.addColumn("Student Number");
			mod.addColumn("Student Name");
			mod.addColumn("Gender");
			mod.addColumn("Contact Number");
			mod.addColumn("Address");
			mod.addColumn("Date of Registration");
			
			//String colunm[]= {"Number","Student No", "Student Name","Address","Date of Registration"};
			try {
				String sql = "SELECT * from student_tbl";
				Statement st = (Statement) con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()) {
					mod.addRow(new Object[] {
						rs.getInt("id"),
						rs.getString("student_no"),	
						rs.getString("student_name"),
						rs.getString("gender"),
						rs.getString("contact_no"),
						rs.getString("address"),
						rs.getString("date_created")
					});
				}
				rs.close();
				st.close();
				con.close();
				
				tableView.setModel(mod);
				//tableView.setAutoResizeMode(0);
				tableView.getColumnModel().getColumn(0).setPreferredWidth(10);
				tableView.getColumnModel().getColumn(1).setPreferredWidth(30);
				tableView.getColumnModel().getColumn(2).setPreferredWidth(30);
				tableView.getColumnModel().getColumn(3).setPreferredWidth(30);
				tableView.getColumnModel().getColumn(4).setPreferredWidth(30);

				
			}catch(Exception ex) {
				System.out.print("Error...view" + ex);
			}
			
		}
		
		private void clearTextfield() {
			// TODO Auto-generated method stub
			txtStudentNo.setText("");
			txtStudentName.setText("");
			txtAddress.setText("");
			txtGender.setSelectedItem("Choose");
			txtContactNo.setText("");
			//JOptionPane.showMessageDialog(null, "Clear successful");
		}
		
		// delete 
		
		private void deleteRecord(String id) {
			Connection con = connect();
			try {
				String sql = "DELETE from student_tbl where student_no = ?";
				PreparedStatement ps =(PreparedStatement) con.prepareStatement(sql);
				ps.setString(1, id);
				ps.execute();
				
				ps.close();
				con.close();
				JOptionPane.showConfirmDialog(null, "Record deleted from database...");
				clearTextfield();
			}catch(Exception ex) {
				System.out.print("Error...delete" + ex);
			}
		}
		
		// update 
		private void updateRecord(String id) {
			Connection con = connect();
			try {
				String sql = "UPDATE student_tbl SET student_no = ?, student_name = ?, gender = ?, contact_no = ?, address = ? where student_no = ?";
				PreparedStatement ps =(PreparedStatement) con.prepareStatement(sql);
				ps.setString(1,txtStudentNo.getText());
				ps.setString(2,txtStudentName.getText());
				ps.setString(3,txtGender.getSelectedItem().toString());
				ps.setString(4,txtContactNo.getText());
				ps.setString(5,txtAddress.getText());
				ps.setString(6,txtStudentNo.getText());
				ps.execute();
				
				ps.close();
				con.close();
				JOptionPane.showMessageDialog(null, "Record successfully updated...");
				clearTextfield();
			}catch(Exception ex) {
				System.out.print("Error...update" + ex);
			}
		}
		
		// click event form table to input field
		private void textField(String id) {
			Connection con = connect();
			try {
				String sql = "SELECT * from student_tbl where id = ?";
				PreparedStatement ps =(PreparedStatement) con.prepareStatement(sql);
				ps.setString(1, id);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					txtStudentNo.setText(rs.getString("student_no"));
					txtStudentName.setText(rs.getString("student_name"));
					if(rs.getString("gender").trim().equals("Male")) {
						txtGender.setSelectedItem("Male");
					}else{
						txtGender.setSelectedItem("Female");
					}

					txtContactNo.setText(rs.getString("contact_no"));
					txtAddress.setText(rs.getString("address"));
				}
			}catch(Exception ex) {
				System.out.print("Error...txt" + ex);
			}
		}
		
		
		
		public void clock() {
			Thread clock = new Thread() {
				public void run () {
					try {
						while(true) {
							Calendar cl = new GregorianCalendar();
							int day = cl.get(Calendar.DAY_OF_MONTH);
							int month = cl.get(Calendar.MONTH);
							int year = cl.get(Calendar.YEAR);
							
							int sec = cl.get(Calendar.SECOND);
							int min = cl.get(Calendar.MINUTE);
							int hr = cl.get(Calendar.HOUR);
							
							lblClock.setText("Time : " + hr + ":" + min + ":" + sec + " | Date : " + month + "/" + day + "/" + year);
							sleep(1000);
							
						}
					}catch(Exception ex) {
						ex.printStackTrace();
					}
				}
			};
			clock.start();
		}
}
