# Martin Aghajanian

## Clash Files
- **yor-f-83 (Topt = 19 slots)**
- **rye-s-93 (Topt = 21 slots)**
- **Log file** contains time slots used for both clash files

## Tasks Completed
- **Task 1**
- **Task 2**
- **Task 3**
- **Task 4:** 
  - Added a train button that trains based on the number of courses and clashes of a specific timeslot.
  - These timeslots are from `trainable_logs` with zero clashes and more than or equal to 181/19 courses in the case of the `yor-f-83.stu` clash file.
- **Task 5:**
  - Added an update button that performs `unitupdate` at each iteration.

## Thoughts on Project Dynamics and Mechanics

### Energy Function
- In a Hopfield network, the dynamics are driven by minimizing an energy function.
- For our timetabling problem, the energy function represents the number of clashes.
- As the network evolves, it aims to reach a state with minimal energy, ideally a clash-free timetable.

### State Updates
- The network's state is updated iteratively.
- Each neuron (representing a time slot for a course) updates its state based on input from other neurons and a predefined rule.
- This is similar to physical systems moving towards a lower energy state, akin to particles settling into a minimum energy configuration.

### Convergence
- The Hopfield network's ability to converge to a stable state (a local minimum of the energy function) is crucial.
- This convergence depends on the network parameters and initial conditions, analogous to how certain systems reach equilibrium over time in mechanics.

### Autoassociator
- Introducing an autoassociator to remember clash-free timeslots and guide future iterations mimics how learning and adaptation are applied in mechanical systems.
- This improves performance based on past experiences.

### Optimization
- Both mechanical systems and timetabling algorithms seek optimal configurations.
- In mechanics, this could be the least energy state, while in timetabling, it's the clash-free state with minimal slots.
