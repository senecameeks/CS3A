
public class Student {

	public String firstName;
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getGradeEntered() {
		return gradeEntered;
	}

	public void setGradeEntered(String gradeEntered) {
		this.gradeEntered = gradeEntered;
	}

	public String getCurrentGrade() {
		return currentGrade;
	}

	public void setCurrentGrade(String currentGrade) {
		this.currentGrade = currentGrade;
	}

	public String getClassYear() {
		return classYear;
	}

	public void setClassYear(String classYear) {
		this.classYear = classYear;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}

	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public String getAdvisor() {
		return advisor;
	}

	public void setAdvisor(String advisor) {
		this.advisor = advisor;
	}

	public String middleName;
	public String lastName;
	public String sex;
	public String gradeEntered;
	public String currentGrade;
	public String classYear;
	public String birthDay;
	public String birthMonth;
	public String birthYear;
	public String advisor;
	
	public Student(String first, String middle, String last, String sX, String gE, String cG, String cY, String bD, String bM, String bY, String adv)
	{
		firstName = first;
		middleName = middle;
		lastName = last;
		sex = sX;
		gradeEntered = gE; 
		currentGrade = cG; 
		classYear = cY;
		birthDay = bD; 
		birthMonth = bM; 
		birthYear = bY;
		advisor = adv; 
		
		this.lastName = last; 
	}
	

	
	
	
}
