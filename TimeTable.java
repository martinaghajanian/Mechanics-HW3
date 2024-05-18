import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;


public class TimeTable extends JFrame implements ActionListener {

	private JPanel screen = new JPanel(), tools = new JPanel();
	private JButton tool[];
	private JTextField field[];
	private CourseArray courses;
	private Color CRScolor[] = {Color.RED, Color.GREEN, Color.BLACK};

	private Autoassociator autoAssociator;
	private Random random = new Random();
	
	public TimeTable() {
		super("Dynamic Time Table");
		setSize(1000, 800);
		setLayout(new FlowLayout());
		
		screen.setPreferredSize(new Dimension(400, 800));
		add(screen);
		
		setTools();
		add(tools);
		
		setVisible(true);

		

	}
	
	public void setTools() {
		String capField[] = {"Slots:", "Courses:", "Clash File:", "Iters:", "Shift:"};
		field = new JTextField[capField.length];
		
		String capButton[] = {"Load", "Start", "Update","Step", "Print", "Continue", "Train", "Exit"};
		tool = new JButton[capButton.length];
		
		tools.setLayout(new GridLayout(2 * capField.length + capButton.length, 1));
		
		for (int i = 0; i < field.length; i++) {
			tools.add(new JLabel(capField[i]));
			field[i] = new JTextField(5);
			tools.add(field[i]);
		}
		
		for (int i = 0; i < tool.length; i++) {
			tool[i] = new JButton(capButton[i]);
			tool[i].addActionListener(this);
			tools.add(tool[i]);
		}
		
		field[0].setText("21");
		field[1].setText("486");
		field[2].setText("rye-s-93.stu");
		field[3].setText("100");
		field[4].setText("21");

	}
	
	public void draw() {
		Graphics g = screen.getGraphics();
		int width = Integer.parseInt(field[0].getText()) * 10;
		for (int courseIndex = 1; courseIndex < courses.length(); courseIndex++) {
			g.setColor(CRScolor[courses.status(courseIndex) > 0 ? 0 : 1]);
			g.drawLine(0, courseIndex, width, courseIndex);
			g.setColor(CRScolor[CRScolor.length - 1]);
			g.drawLine(10 * courses.slot(courseIndex), courseIndex, 10 * courses.slot(courseIndex) + 10, courseIndex);
		}
	}
	
	private int getButtonIndex(JButton source) {
		int result = 0;
		while (source != tool[result]) result++;
		return result;
	}
	
	public void actionPerformed(ActionEvent click) {
		int min, step, clashes;
		
		switch (getButtonIndex((JButton) click.getSource())) {
		case 0:
			int slots = Integer.parseInt(field[0].getText());
			courses = new CourseArray(Integer.parseInt(field[1].getText()) + 1, slots);
			courses.readClashes(field[2].getText());
			draw();

			autoAssociator = new Autoassociator(courses);
			
			break;
		
		case 1:
			min = Integer.MAX_VALUE;
			step = 0;

			for (int i = 1; i < courses.length(); i++) 
				courses.setSlot(i, 0);
			
			
			
			for (int iteration = 1; iteration <= Integer.parseInt(field[3].getText()); iteration++) {
				courses.iterate(Integer.parseInt(field[4].getText()));
				draw();
				clashes = courses.clashesLeft();
				if (clashes < min) {
					min = clashes;
					step = iteration;
				}
			}
				
			
			
				
			System.out.println("Shift = " + field[4].getText() + "\tMin clashes = " + min + "\tat step " + step);
			setVisible(true);
			courses.printSlotStatus();
			break;
		
		case 2:
			min = Integer.MAX_VALUE;
			step = 0;

			for (int i = 1; i < courses.length(); i++) 
				courses.setSlot(i, 0);
			
			int cycles = 1;
			for (int cycle = 1; cycle <= cycles; cycles++) {
				for (int iteration = 1; iteration <= Integer.parseInt(field[3].getText()); iteration++) {
					courses.iterate(Integer.parseInt(field[4].getText()));
					draw();
					clashes = courses.clashesLeft();
					if (clashes < min) {
						min = clashes;
						step = iteration;
					}
					int rand = Integer.parseInt(field[0].getText());
					autoAssociator.unitUpdate(courses.getTimeSlot(random.nextInt(rand)));

				}
				
			}
			
				
			System.out.println("Shift = " + field[4].getText() + "\tMin clashes = " + min + "\tat step " + step);
			setVisible(true);
			courses.printSlotStatus();
			break;
		case 3:
			courses.iterate(Integer.parseInt(field[4].getText()));
			draw();
			break;
		case 4:
			System.out.println("Exam\tSlot\tClashes");
			for (int i = 1; i < courses.length(); i++)
				System.out.println(i + "\t" + courses.slot(i) + "\t" + courses.status(i));
			break;
		case 5:
		min = Integer.MAX_VALUE;
		step = 0;
		
		for (int iteration = 1; iteration <= Integer.parseInt(field[3].getText()); iteration++) {
			courses.iterate(Integer.parseInt(field[4].getText()));
			draw();
			clashes = courses.clashesLeft();
			if (clashes < min) {
				min = clashes;
				step = iteration;
			}
		}

		
		System.out.println("Shift = " + field[4].getText() + "\tMin clashes = " + min + "\tat step " + step);
		setVisible(true);
		courses.printSlotStatus();
		break;
		case 6:  // TRAIN BUTTON:

			int numOfSlots = Integer.parseInt(field[0].getText());

			for (int i = 0; i < numOfSlots; i++) {

				int numOfCourses = courses.slotStatus(i)[0];
				int numOfClashes = courses.slotStatus(i)[1];
				int adequateNumOfCourses = courses.length()/Integer.parseInt(field[0].getText());
				int trainingCapacity = autoAssociator.getTrainingCapacity();

				int iter = 0;

				if (numOfCourses >=  adequateNumOfCourses &&  numOfClashes == 0){
					// trains if that time slot has zero clashes, and there are adequate number of courses in that timeslot
					
					int[] pattern = courses.getTimeSlot(i);
					
					if (iter < trainingCapacity) {
						autoAssociator.training(pattern);
						iter++;
						System.out.println("trained");
					}
				}
			}
			break;
		case 7:
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		new TimeTable();
	}
}
