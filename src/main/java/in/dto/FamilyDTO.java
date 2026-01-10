package in.dto;

public class FamilyDTO {
	
	private String email;
	private String fatherName;
	private String motherName;
	private String maritialStatus;
	private Integer siblings;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getMaritialStatus() {
		return maritialStatus;
	}
	public void setMaritialStatus(String maritialStatus) {
		this.maritialStatus = maritialStatus;
	}
	public Integer getSiblings() {
		return siblings;
	}
	public void setSiblings(Integer siblings) {
		this.siblings = siblings;
	}
}
