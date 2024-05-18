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
		int sum = 0;
		for (int i = 0; i < neurons.length; i++) {
			sum += weights[index][i] * neurons [i];
		}

		if (sum >= 0) {
			neurons[index] = 1;
		} else {
			neurons[index] = -1;
		}
	}

	public int unitUpdate(int neurons[]) {
		int index = random.nextInt(neurons.length);
		unitUpdate(neurons, index);
		return index;
	}
	
	public void chainUpdate(int neurons[], int steps) {
		for (int i = 0; i < steps; i++) {
			unitUpdate(neurons);
		}
	}
	
	public void fullUpdate(int neurons[]) {
		boolean stable;
        do {
            stable = true;
            for (int i = 0; i < neurons.length; i++) {
                int oldState = neurons[i];
                unitUpdate(neurons, i);
                if (neurons[i] != oldState) {
                    stable = false;
                }
            }
        } while (!stable);
	}
}
