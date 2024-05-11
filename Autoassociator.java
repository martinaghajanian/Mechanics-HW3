import java.util.Random;


public class Autoassociator {
	private int weights[][];
	private int trainingCapacity;
	private Random random = new Random();

	
	public Autoassociator(CourseArray courses) {
		weights = new int[courses.length()][courses.length()];
		trainingCapacity = (int) (0.139 * weights.length);
	}
	
	public int getTrainingCapacity() {
		return trainingCapacity;
	}
	
	public void training(int pattern[]) {
		for (int i = 0; i < pattern.length - 1; i++) {
			for (int j = i + 1; j < pattern.length; j++) {
				weights[i][j] = pattern[i] * pattern[j];
				weights[j][i] = pattern[i] * pattern[j];
			}
		}
	}
	
	
	public void unitUpdate(int neurons[], int index) {
		// TO DO
		// implements the update step of a single neuron specified by index
	}

	public int unitUpdate(int neurons[]) {
		int index = random.nextInt(neurons.length);
		unitUpdate(neurons, index);
		return index;
	}
	
	public void chainUpdate(int neurons[], int steps) {
		for (let i = 0; i < steps; i++) {
			unitUpdate(neurons);
		}
	}
	
	public void fullUpdate(int neurons[]) {
		// TO DO
		// updates the input until the final state achieved
	}
}
