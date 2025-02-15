package application;

import java.util.*;

public class ConflictChecker {

	private boolean hasConflicts = false;
	private ArrayList<String> conflictList = new ArrayList<String>();

	/**
	 * Creates a Conflict Checker which will check the course list for any conflicting times
	 * 
	 * @param courseList - the array of courses a student is taking
	 */
	public ConflictChecker(ArrayList<Course> courseList) {
		
		//Getting the intial course to check the remaining courses against
		for (int courseIndex = 0; courseIndex < courseList.size(); courseIndex++) {
			Course course = courseList.get(courseIndex);
			
			//Going through each course to check against the intial course for any conflicts
			for (int i = courseIndex + 1; i < courseList.size(); i++) {
				
				//Removing the checked course if identical to original course
				if (course.equals(courseList.get(i))) {
					courseList.remove(i);
					
				//Adding any conflicting courses to a conflict list
				//Setting hasConflicts to true to mark that the list has conflicts
				} else if (course.compareTo(courseList.get(i)) > 0) {
					conflictList.add("Course " + course.getName() + " and Course " + courseList.get(i).getName()
							+ " have conflicting times");
					hasConflicts = true;
				}
			}
		}
	}

	/**
	 * Returns whether the schedule has conflicts or not
	 * 
	 * @return - true/false as to if the schedule has conflicts
	 */
	public boolean getHasConflicts() {
		return hasConflicts;
	}

	/**
	 * Returns the list of conflicting courses
	 * 
	 * @return = the list of conflicting courses
	 */
	public ArrayList<String> getConflictList() {
		return conflictList;
	}

}
