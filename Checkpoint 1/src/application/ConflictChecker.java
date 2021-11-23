package application;

import java.util.*;

public class ConflictChecker {
	
	private boolean hasConflicts = false;
	private ArrayList<String> conflictList = new ArrayList<String>(); 
	
	public ConflictChecker(ArrayList<Course> courseList) {
		for(int courseIndex = 0; courseIndex < courseList.size(); courseIndex++) {
			Course course = courseList.get(courseIndex);
			for(int i = courseIndex + 1; i < courseList.size(); i++) {
				if(course.equals(courseList.get(i))) {
					courseList.remove(i);
				} else if (course.compareTo(courseList.get(i)) > 0) {
					conflictList.add("Course " + course.getName() + " and Course " + courseList.get(i).getName() + " have conflicting times");
					hasConflicts = true;
				}
			}
		}
	}
	
	public boolean getHasConflicts() {
		return hasConflicts;
	}
	
	public ArrayList<String> getConflictList() {
		return conflictList;
	}

}
