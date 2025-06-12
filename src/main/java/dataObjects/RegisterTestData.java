package dataObject;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Getter;
import lombok.Setter;

@ExcelSheet("RegisterData")
@Getter @Setter
public class RegisterTestData {
    @ExcelCellName("firstName")
    private String firstName;
    @ExcelCellName("lastName")
    private String lastName;
    @ExcelCellName("Email")
    private String emailAddress;
    @ExcelCellName("CompanyName")
    private String companyName;
    @ExcelCellName("Password")
    private String password;
    @ExcelCellName("Gender")
    private String gender;
    @ExcelCellName("Confirm password")
    private String confirmPassword;

    public RegisterTestData(){}

    public RegisterTestData(String firstName, String lastName, String emailAddress, String companyName, String password,String gender) {
        this.firstName = firstName;
        this.gender = gender;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.companyName = companyName;
        this.password = password;

    }

    public RegisterTestData(String firstName, String lastName, String emailAddress, String companyName, String password, String confirmPassword,String gender) {
        this.firstName = firstName;
        this.gender = gender;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.companyName = companyName;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return String.format("Email='%s'",emailAddress);
    }

}
