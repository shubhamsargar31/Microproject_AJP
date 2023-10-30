package Register.frame;
import java.awt.*;
import javax.swing.text.JTextComponent.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.text.*;
import java.util.*;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

import ODBC.Database.MySQLConnectionExample;

/*imports for JDatePicker*/
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.text.JTextComponent;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import Register.model.Student;
import Register.frame.Validation;
public class RegisterFrame extends JFrame implements ActionListener {
    protected static final JTextComponent txtReceipt = null;
	JLabel message;
    JLabel name, dobLabel, genderLabel, dobFormat;
    JTextField nameField;
    JRadioButton genderMale, genderFemale;
    ButtonGroup genderGroup;
    JLabel mailIdLabel, mobileNoLabel;
    JTextField mailIdField, mobileNoField;
    JLabel passwordLabel, rePasswordLabel;
    JPasswordField passwordField, rePasswordField;
    JLabel programLabel;
    JComboBox<String> programList;
    JLabel branchLabel, semesterLabel;
    JComboBox<String> branchList;
    JComboBox<Integer> semesterList;
    JButton registerButton;
    Container container;
    /*JDatePicker*/
    JDatePanelImpl datePanel;
    JDatePickerImpl datePicker;
    public RegisterFrame() {
    	this.setBounds(20,20,750,800);
        message = new JLabel("Register a new Student");
        message.setFont(new Font("Courier", Font.BOLD, 20));
        name = new JLabel("Name");
        nameField = new JTextField();
        dobLabel = new JLabel("DOB");
        //dobField = new JTextField();
        /*Adding JDatePicker date picker*/
        UtilDateModel model = new UtilDateModel();
        model.setDate(1999, 01, 02);
        model.setSelected(true);
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        dobFormat = new JLabel("(yyyy-mm-dd)");
        /*End Date picker*/
        genderLabel = new JLabel("Gender");
        genderMale = new JRadioButton("Male", true);
        genderFemale = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(genderMale);
        genderGroup.add(genderFemale);
        mailIdLabel = new JLabel("Mail Id");
        mailIdField = new JTextField();
        mobileNoLabel = new JLabel("Mobile No");
        mobileNoField = new JTextField();
        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();
        rePasswordLabel = new JLabel("Re Password");
        rePasswordField = new JPasswordField();
        programLabel = new JLabel("Courses");
        programList = new JComboBox<String>();
        programList.addItem("ME/M Tect");
        programList.addItem("BE/B Tect");
        programList.addItem("Diploma");
        branchLabel = new JLabel("Branch");
        branchList = new JComboBox<String>();
        branchList.addItem("Computer Science and Engineering");
        branchList.addItem("Electronics and Telecommunications");
        branchList.addItem("Information Technology");
        branchList.addItem("Electrical Engineering");
        branchList.addItem("Electrical and Electronics Engineering");
        branchList.addItem("Civil Engineering");
        semesterLabel = new JLabel("Semester");
        semesterList = new JComboBox<>();
        semesterList.addItem(1);
        semesterList.addItem(2);
        semesterList.addItem(3);
        semesterList.addItem(4);
        semesterList.addItem(5);
        semesterList.addItem(6);
        semesterList.addItem(7);
        semesterList.addItem(8);
        registerButton = new JButton("Register");
        		registerButton.addActionListener(new ActionListener() {
        		    public void actionPerformed(ActionEvent e) {
        	
        		        if (e.getSource() == registerButton) {
        		        	Student student1=new Student();
        		            System.out.println("Register Button Clicked");
        		            String gender = null;
        		            if (genderFemale.isSelected()) {
        		                gender = "Female";
        		            }
        		            if (genderMale.isSelected()) {
        		                gender = "Male";
        		            }
        		            String programName = programList.getSelectedItem().toString();
        		            String branchName = branchList.getSelectedItem().toString();
        		            int semesterNo = Integer.parseInt(semesterList.getSelectedItem().toString());
        		            String dobString = datePicker.getJFormattedTextField().getText();
        		            
        		            if (dobString.isEmpty()) {
        		                JOptionPane.showMessageDialog(RegisterFrame.this, "Date of Birth is Empty", "Error", JOptionPane.ERROR_MESSAGE);
        		                return;
        		            }

        		            Date Datedob = null;
        		            try {
        		                Datedob = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(dobString).getTime());
        		            } catch (ParseException ex) {
        		                System.out.println("Exception " + ex);
        		                JOptionPane.showMessageDialog(RegisterFrame.this, "Date of birth format is incorrect", "Error", JOptionPane.ERROR_MESSAGE);
        		                return;
        		            }

        		            // Assuming you have a Student class constructor that accepts these parameters
        		            Student student = new Student(nameField.getText(), Datedob, gender, mailIdField.getText(), mobileNoField.getText(), passwordField.getText(), rePasswordField.getText(), programName, branchName, semesterNo);
        		            Validation validator = new Validation();

        		            // Call the validation method and get the list of errors
        		            List<String> validationErrors = validator.validateRegistration(student);

        		            if (!validationErrors.isEmpty()) {
        		                // Display validation errors using JOptionPane
        		                StringBuilder errorMessage = new StringBuilder("Validation Errors:\n");
        		                for (String error : validationErrors) {
        		                    errorMessage.append("- ").append(error).append("\n");
        		                }
        		                JOptionPane.showMessageDialog(RegisterFrame.this, errorMessage.toString(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        		            } else {
//        		            	JOptionPane.showMessageDialog(RegisterFrame.this, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        		            	Connection conn = null;
        		            	  PreparedStatement ps = null;
        		                  int st = 0; // status

        		                  try {
        		                      conn = DriverManager.getConnection("jdbc:Mysql://localhost/student?user=root&password=Shubham_2006");
        		                          System.out.println("Connected to the database.");
        		                          String query = "INSERT INTO student1 (name, dob, gender, mailid, mobile_no, program, branch, semester) VALUES (?,?,?,?,?,?,?,?)";
        		                          ps = conn.prepareStatement(query);
        		                          ps.setString(1, Student.getName());
        		                          ps.setDate(2, new java.sql.Date(Student.getDob().getTime()));
        		                          ps.setString(3, Student.getGender());
        		                          ps.setString(4, Student.getMailId());
        		                          ps.setString(5, Student.getMobileNo());
        		                          ps.setString(6, Student.getEncPassword());
        		                          ps.setString(7, Student.getProgram());
        		                          ps.setString(8, Student.getBranch());
        		                          ps.setInt(9, Student.getSemester());
        		                          st = ps.executeUpdate();
        		                          System.out.println("Inserted student " + st);
        		                          JOptionPane.showMessageDialog(RegisterFrame.this, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);   		                         
        		                          conn.close();
        		                          
        		                  } catch (SQLException e1) {
        		                      System.out.println("Database connection error.");
        		                    e1.printStackTrace();
        		                  }
        		          
        		            }
        		        
        		            // Show a success message
        		            
        		        }
        		    }
        		});

        container = getContentPane();
        container.setLayout(null);
        setBounds();
        addComponents();
    }
    public void setBounds() {
        message.setBounds(50, 10, 600, 30);
        name.setBounds(50, 60, 100, 30);
        nameField.setBounds(130, 60, 200, 30);
        dobLabel.setBounds(50, 110, 100, 30);
        /*JDatePicker*/
        datePicker.setBounds(130, 110, 200, 30);
        dobFormat.setBounds(350, 110, 200, 30);
        genderLabel.setBounds(50, 160, 100, 30);
        genderMale.setBounds(130, 160, 100, 30);
        genderFemale.setBounds(240, 160, 100, 30);
        mailIdLabel.setBounds(50, 210, 100, 30);
        mailIdField.setBounds(130, 210, 200, 30);
        mobileNoLabel.setBounds(50, 260, 100, 30);
        mobileNoField.setBounds(130, 260, 200, 30);
        passwordLabel.setBounds(50, 310, 100, 30);
        passwordField.setBounds(130, 310, 200, 30);
        rePasswordLabel.setBounds(50, 360, 100, 30);
        rePasswordField.setBounds(130, 360, 200, 30);
        programLabel.setBounds(50, 410, 100, 30);
        programList.setBounds(130, 410, 200, 30);
        branchLabel.setBounds(50, 460, 100, 30);
        branchList.setBounds(130, 460, 200, 30);
        semesterLabel.setBounds(50, 510, 100, 30);
        semesterList.setBounds(130, 510, 200, 30);
        registerButton.setBounds(147, 575, 143, 30);
    }
    public void addComponents() {
        container.add(message);
        container.add(name);
        container.add(nameField);
        container.add(dobLabel);
        /*JDatePicker*/
        container.add(datePicker);
        container.add(dobFormat);
        container.add(genderLabel);
        container.add(genderMale);
        container.add(genderFemale);
        container.add(mailIdLabel);
        container.add(mailIdField);
        container.add(mobileNoLabel);
        container.add(mobileNoField);
        container.add(passwordLabel);
        container.add(passwordField);
        container.add(rePasswordLabel);
        container.add(rePasswordField);
        container.add(programLabel);
        container.add(programList);
        container.add(branchLabel);
        container.add(branchList);
        container.add(semesterLabel);
        container.add(semesterList);
        container.add(registerButton);
        }
  
	public static void main(String[] args) {
        RegisterFrame frame = new RegisterFrame();
        frame.setTitle("Student Register Form");
        frame.setVisible(true);
        frame.setBounds(500, 100, 500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }
    /*JDate Picker drop down*/
    public class DateLabelFormatter extends AbstractFormatter {
        private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }
        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }
            return "";
        }
    }

 
    @Override
    public void actionPerformed(ActionEvent e) {
       
    }
}
 
 
 
 
 
 
