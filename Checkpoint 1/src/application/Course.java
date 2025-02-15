package application;

public class Course {
	private String classCode = new String();
	private String className = new String();
	private String startTime = new String();
	private String endTime = new String();
	private String days = new String();
	private String professor = new String();
	private String building = new String();
	private String room = new String();
	private double startCode = 0;
	private double endCode = 0;
	private double hourStartCode = 0;
	private double minuteStartCode = 0;
	private double hourEndCode = 0;
	private double minuteEndCode = 0;

	/**
	 * Creates a course object
	 * 
	 * @param code      - the course code
	 * @param name      - the course name
	 * @param professor - the course professor
	 * @param building  - the course building
	 * @param room      - the course room
	 * @param startTime - the time the course starts
	 * @param endTime   - the time the course ends
	 * @param days      - the days the course occurs
	 */
	public Course(String code, String name, String professor, String building, String room, String startTime,
			String endTime, String days) {
		classCode = code;
		className = name;
		this.professor = professor;
		this.building = building;
		this.room = room;
		this.startTime = startTime;
		this.endTime = endTime;
		this.days = days;
		createStartCode(startTime);
		createEndCode(endTime);
	}

	/**
	 * Creates the start code for the course in order to judge conflicts better
	 * 
	 * @param startTime - the time the course starts
	 * @return the time the class starts in the form of a double
	 */
	private double createStartCode(String startTime) {
		int startInt = 0;
		double startDouble = 0;
		if (startTime.substring(0, 2).contains("10") || startTime.substring(0, 2).contains("11")
				|| startTime.substring(0, 2).contains("12")) {
			startInt = Integer.parseInt(startTime.substring(0, 2));
			startDouble = Double.parseDouble(startTime.substring(3, 5));
		} else {
			startInt = Integer.parseInt(startTime.substring(0, 1));
			startDouble = Double.parseDouble(startTime.substring(2, 4));
		}
		startDouble = startDouble / 60;
		//startDouble = startDouble / 100;

		startCode = startInt + startDouble;
		if (startTime.contains("PM") && startInt != 12) {
			startCode = startCode + 12;
			startInt = startInt + 12;
		}

		hourStartCode = startInt;
		minuteStartCode = startDouble;
		return startCode;
	}

	/**
	 * Creates the end code for the course in order to judge conflicts better
	 * 
	 * @param endTime - the time the class ends
	 * @return the time the class ends in the form of a double
	 */
	private double createEndCode(String endTime) { 
		int endInt = 0;
		double endDouble = 0;
		if (endTime.substring(0, 2).contains("10") || endTime.substring(0, 2).contains("11")
				|| endTime.substring(0, 2).contains("12")) {
			endInt = Integer.parseInt(endTime.substring(0, 2));
			endDouble = Double.parseDouble(endTime.substring(3, 5));
		} else {  
			endInt = Integer.parseInt(endTime.substring(0, 1));
			endDouble = Double.parseDouble(endTime.substring(2, 4));
		}
		endDouble = endDouble / 60;
		//endDouble = endDouble / 100;
		
		;
		endCode = endInt + endDouble;
		if (endTime.contains("PM") && endInt != 12) { 
			endCode = endCode + 12;
			endInt = endInt + 12;
		}
		
		hourEndCode = endInt;
		minuteEndCode = endDouble;
		return endCode;
	}

	/**
	 * Gives the course name
	 * 
	 * @return the course name
	 */
	public String getName() {
		return className;
	}
	/**
	 * Gives the class code
	 * @return the class code
	 */
	public String getClassCode() {
		return classCode;
	}

	/**
	 * Gives the name of the professor teaching the class
	 * @return the professor's name
	 */
	public String getProfessor() {
		return professor;
	}
	
	/**
	 * Gives the building where the class is held
	 * @return the building name
	 */
	public String getBuilding() {
		return building;
	}
	
	/**
	 * Gives the room number where the course is taught
	 * @return the room number
	 */
	public String getRoomNumber() {
		return room;
	}
	
	/**
	 * Gives the course days
	 * 
	 * @return the course days
	 */
	public String getDays() {
		return days;
	}

	/**
	 * Gives the course start code
	 * 
	 * @return the course start code
	 */
	public double getStartCode() {
	
		return startCode;
	}

	/**
	 * Gives the course end code
	 * 
	 * @return the course end code
	 */
	public double getEndCode() {
		return endCode;
	}
	
	/**
	 * Gives the hour of the start of class 
	 * @return hour of the start of class
	 */
	public double getHourStartCode() {
		return hourStartCode;
	}
	
	/**
	 * Gives the minutes of the start of class in base 100
	 * @return minutes of the start of class
	 */
	public double getMinuteStartCode() {
		return minuteStartCode;
	}
	
	/**
	 * Gives the hour of the end of class
	 * @return hour of end of class
	 */
	public double getHourEndCode() {
		return hourEndCode;
	}
	
	/**
	 * Gives the minutes of the end of class in base 100
	 * @return minutes of the end of class
	 */
	public double getMinuteEndCode() {
		return minuteEndCode;
	}
	
	
	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}

		if (!(object instanceof Course)) {
			return false;
		}

		Course other = (Course) object;

		if (this.classCode.equals(other.classCode) && this.className.equals(other.className)
				&& this.startTime.equals(other.startTime) && this.endTime.equals(other.endTime)
				&& this.days.equals(other.days) && this.professor.equals(other.professor)
				&& this.building.equals(other.building) && this.room.equals(other.room)
				&& this.startCode == other.startCode && this.endCode == other.endCode) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Compares the times of one course to another
	 * 
	 * @param other - the course being compared
	 * @return if the courses conflict with each other
	 */
	public int compareTo(Course other) {
		if (this.days.contains("M") && other.days.contains("M")) {
			if (this.startCode == other.startCode) {
				return 1;
			} else if (this.endCode == other.endCode) {
				return 1;
			} else if (this.startCode == other.endCode) {
				return 1;
			} else if (this.endCode == other.startCode) {
				return 1;
			} else if (this.startCode > other.startCode && this.startCode < other.endCode) {
				return 1;
			} else if (other.startCode > this.startCode && other.startCode < this.endCode) {
				return 1;
			} else if (this.endCode > other.startCode && this.endCode < other.endCode) {
				return 1;
			} else if (other.endCode > this.startCode && other.endCode < this.endCode) {
				return 1;
			} else {
				return -1;
			}

		} else if (this.days.contains("Tu") && other.days.contains("Tu")) {
			if (this.startCode == other.startCode) {
				return 1;
			} else if (this.endCode == other.endCode) {
				return 1;
			} else if (this.startCode == other.endCode) {
				return 1;
			} else if (this.endCode == other.startCode) {
				return 1;
			} else if (this.startCode > other.startCode && this.startCode < other.endCode) {
				return 1;
			} else if (other.startCode > this.startCode && other.startCode < this.endCode) {
				return 1;
			} else if (this.endCode > other.startCode && this.endCode < other.endCode) {
				return 1;
			} else if (other.endCode > this.startCode && other.endCode < this.endCode) {
				return 1;
			} else {
				return -1;
			}

		} else if (this.days.contains("W") && other.days.contains("W")) {
			if (this.startCode == other.startCode) {
				return 1;
			} else if (this.endCode == other.endCode) {
				return 1;
			} else if (this.startCode == other.endCode) {
				return 1;
			} else if (this.endCode == other.startCode) {
				return 1;
			} else if (this.startCode > other.startCode && this.startCode < other.endCode) {
				return 1;
			} else if (other.startCode > this.startCode && other.startCode < this.endCode) {
				return 1;
			} else if (this.endCode > other.startCode && this.endCode < other.endCode) {
				return 1;
			} else if (other.endCode > this.startCode && other.endCode < this.endCode) {
				return 1;
			} else {
				return -1;
			}

		} else if (this.days.contains("Th") && other.days.contains("Th")) {
			if (this.startCode == other.startCode) {
				return 1;
			} else if (this.endCode == other.endCode) {
				return 1;
			} else if (this.startCode == other.endCode) {
				return 1;
			} else if (this.endCode == other.startCode) {
				return 1;
			} else if (this.startCode > other.startCode && this.startCode < other.endCode) {
				return 1;
			} else if (other.startCode > this.startCode && other.startCode < this.endCode) {
				return 1;
			} else if (this.endCode > other.startCode && this.endCode < other.endCode) {
				return 1;
			} else if (other.endCode > this.startCode && other.endCode < this.endCode) {
				return 1;
			} else {
				return -1;
			}

		} else if (this.days.contains("F") && other.days.contains("F")) {
			if (this.startCode == other.startCode) {
				return 1;
			} else if (this.endCode == other.endCode) {
				return 1;
			} else if (this.startCode == other.endCode) {
				return 1;
			} else if (this.endCode == other.startCode) {
				return 1;
			} else if (this.startCode > other.startCode && this.startCode < other.endCode) {
				return 1;
			} else if (other.startCode > this.startCode && other.startCode < this.endCode) {
				return 1;
			} else if (this.endCode > other.startCode && this.endCode < other.endCode) {
				return 1;
			} else if (other.endCode > this.startCode && other.endCode < this.endCode) {
				return 1;
			} else {
				return -1;
			}
		}
		return -1;
	}

	@Override
	public String toString() {
		return classCode + ", " + className + ", " + building + ", " + room + ", " + professor + ", " + startTime + ", "
				+ endTime + ", " + days;
		
	}
}
