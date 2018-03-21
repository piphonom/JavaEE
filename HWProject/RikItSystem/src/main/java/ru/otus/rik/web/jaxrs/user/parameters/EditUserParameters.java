package ru.otus.rik.web.jaxrs.user.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.ConstraintViolationException;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditUserParameters {

    public static final String KEYWORD_NAME = "name";
    public static final String KEYWORD_LOCATION = "location";

    @JsonProperty("email")
    @Email
    @NotBlank
    @ApiParam(value = "User email", required = true)
    private String email;

    @JsonProperty("department")
    // TODO: add @Pattern with regexp instead of check in parseDepartment()
    @ApiParam(value = "Existed department location and name separated by &")
    private String department;

    @JsonProperty("position")
    @ApiParam(value = "Existed position name")
    private String position;

    public String getDepartmentName() {
        return parseDepartment(KEYWORD_NAME);
    }

    public String getDepartmentLocation() {
        return parseDepartment(KEYWORD_LOCATION);
    }

    private String parseDepartment(String keyword) {
        String[] departmentInfo = department.split("&");
        if (departmentInfo.length != 2) {
            throw new ConstraintViolationException("Department info has wrong format", null);
        }
        return keyword.equalsIgnoreCase(KEYWORD_LOCATION) ? departmentInfo[0] : departmentInfo[1];
    }
}
