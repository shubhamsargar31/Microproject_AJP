package Register.model;
 
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
 
public class Student implements  Serializable{
 
	public long id;
    public static String name;
    public static Date dob;
    public static String gender;
    public static String mailId;
    public static String mobileNo;
    public static String password;
    public static String rePassword;
    public static String encPassword;
    public static String program;
    public static String branch;
    public static int semester;
    public Timestamp addDate;
 
    public Student() {
    }
 
    public Student(String name, Date dob, String gender, String mailId, String mobileNo, String password, String rePassword, String program, String branch, int semester) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.mailId = mailId;
        this.mobileNo = mobileNo;
        this.password = password;
        this.rePassword = rePassword;
        this.program = program;
        this.branch = branch;
        this.semester = semester;
    }
 

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", dob=" + dob + ", gender=" + gender + ", mailId=" + mailId + ", mobileNo=" + mobileNo + ", password=" + password + ", rePassword=" + rePassword + ", program=" + program + ", branch=" + branch + ", semester=" + semester + ", addDate=" + addDate + '}';
    }
 
    public long getId() {
        return id;
    }
 
    public void setId(long id) {
        this.id = id;
    }
 
    public static String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public static Date getDob() {
        return dob;
    }
 
    public void setDob(Date dob) {
        this.dob = dob;
    }
 
    public static String getGender() {
        return gender;
    }
 
    public void setGender(String gender) {
        this.gender = gender;
    }
 
    public static String getMailId() {
        return mailId;
    }
 
    public void setMailId(String mailId) {
        this.mailId = mailId;
    }
 
    public static String getMobileNo() {
        return mobileNo;
    }
 
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getRePassword() {
        return rePassword;
    }
 
    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
 
    public static String getProgram() {
        return program;
    }
 
    public void setProgram(String program) {
        this.program = program;
    }
 
    public static String getBranch() {
        return branch;
    }
 
    public void setBranch(String branch) {
        this.branch = branch;
    }
 
    public static int getSemester() {
        return semester;
    }
 
    public void setSemester(int semester) {
        this.semester = semester;
    }
 
    public Timestamp getAddDate() {
        return addDate;
    }
 
    public void setAddDate(Timestamp addDate) {
        this.addDate = addDate;
    }
 
    public static String getEncPassword() {
        return encPassword;
    }
 
    public void setEncPassword(String encPassword) {
        this.encPassword = encPassword;
    }
}
 
 
 
 
 
 
