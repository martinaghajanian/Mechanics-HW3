public class Autoassociator {
	private int weights[][];
	private int trainingCapacity;
	
	public Autoassociator(CourseArray courses) {
		weights = new int[courses.length()][courses.length()];
		trainingCapacity = (int) (0.139 * weights.length);
	}
	
	public int getTrainingCapacity() {
		return trainingCapacity;
	}
	
	public void training(int pattern[]) {
		// TO DO
	}
	
	public int unitUpdate(int neurons[]) {
		// TO DO
		// implements a single update step and
		// returns the index of the randomly selected and updated neuron
		
		return 0;
	}
	
	public void unitUpdate(int neurons[], int index) {
		// TO DO
		// implements the update step of a single neuron specified by index
	}
	
	public void chainUpdate(int neurons[], int steps) {
		// TO DO
		// implements the specified number od update steps
	}
	
	public void fullUpdate(int neurons[]) {
		// TO DO
		// updates the input until the final state achieved
	}
}
