# Ant Colony Simulation

This is the final project for the Data Structures class, available at [bossadapt.org/antsim/](https://bossadapt.org/antsim/). Beyond the original requirements, I enhanced the project by integrating it with Spring Boot and building a custom TypeScript + Next.js frontend.

## Explanation

This project simulates the autonomy of an ant colony, where ants perform specific roles influenced by their given tasks and random chance. The roles of the ants are as follows:

| Ant Type  | Purpose                                                                                      |
|-----------|----------------------------------------------------------------------------------------------|
| **Queen** | The heart of the colony; its death marks the end of the simulation. The queen consumes food per turn and produces new ants. |
| **Scout** | Randomly explores the grid, uncovering areas to enable foragers and soldiers to move to new locations. |
| **Forager** | Collects food and leaves pheromone trails for other foragers to follow.                     |
| **Soldier** | Kills balas on sight. Soldiers can break out of random movement if a bala is one space away. |
| **Bala**   | Attacks all friendly ants on sight and moves purely at random.                               |



## Backend Design
![Semester Project UML design](https://github.com/user-attachments/assets/a7266434-6f07-4577-bf6b-ef184bf5cc6a)
