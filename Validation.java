package Register.frame;
import Register.model.Student;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Register.frame.RegisterFrame;
import Register.model.Student;
import javax.swing.JOptionPane;
public class Validation {
    private static final Student Student = null;
	private Date dob;
	private String gender;
	public List<String> validateRegistration(Student student) {
        ArrayList<String> err = new ArrayList<String>();
        if (student.getName().isEmpty()) {
            err.add("Name can not be empty");
        } else if (student.getName().length() < 4) {
            err.add("Name is too short");
        } else if (student.getName().length() > 50) {
            err.add("Name is too long");
        } else if (!isString(student.getName())) {
            err.add("Only characters allowed in name");
        }
        if (student.getMailId().isEmpty()) {
            err.add("Mail Id can not be empty");
        } else if (!isValidEmailAddress(student.getMailId())) {
            err.add("Mail Id is not valid");
        }
        if (student.getMobileNo().isEmpty()) {
            err.add("Mobile Number can not be empty");
        } else if (student.getMobileNo().length() != 10) {
            err.add("Mobile Number must be 10 digit long");
        } else if (!isDigit(student.getMobileNo())) {
            err.add("Mobile Numbers must have only digits");
        }
        if (student.getPassword().isEmpty()) {
            err.add("Password not be empty");
        } else if (student.getPassword().length() < 8) {
            err.add("Password is too short");
        } else if (student.getPassword().length() > 20) {
            err.add("Password is too long");
        }/* else if (!isStrongPassword(student.getPassword())) {
            err.add("Enter Strong Password");
        }*/
        if (student.getRePassword().isEmpty()) {
            err.add("Re Password can not be empty");
        } else if (student.getRePassword().length() < 8) {
            err.add("Re Password is too short");
        } else if (student.getRePassword().length() > 20) {
            err.add("Re Password is too long");
        }
        if (!student.getPassword().equals(student.getRePassword())) {
            err.add("Both passwords are not matching");
        }
        return err;
       
    }
	public boolean isValidEmailAddress(String email) {
        String pattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(pattern);
    }

    public boolean isString(String name) {
        String pattern = "^[A-Za-z '-]+$";
        return name.matches(pattern);
    }

/* /public boolean isStrongPassword(String text) {
        String pattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@#$%^&+=]${8,16}";
        return text.matches(pattern);
    }*/

    public boolean isDigit(String mobileNo) {
        return mobileNo.matches("\\d{10}");
    }
    
 
	 
}
 
 
 
 
 
 
 

