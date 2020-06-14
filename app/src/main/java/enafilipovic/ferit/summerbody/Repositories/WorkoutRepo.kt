package enafilipovic.ferit.summerbody.Repositories

import enafilipovic.ferit.summerbody.Models.ProgramWorkout
import enafilipovic.ferit.summerbody.Models.ProgramWorkoutItem
import enafilipovic.ferit.summerbody.R

object WorkoutRepo {
    val workouts: MutableList<ProgramWorkout>
    init {
        workouts =
            retrieveWorkout()
    }
    private fun retrieveWorkout():MutableList<ProgramWorkout>{
        return mutableListOf(
            ProgramWorkout(
                1, mutableListOf(
                    ProgramWorkoutItem(
                        R.drawable.wall_pushups,
                        "1. Stand about 3 feet away from a wall, facing the wall, with your feet shoulder-width apart.\n" +
                                "2. Lean forward and place your hands flat on the wall, in line with your shoulders. Your body should be in plank position, with your spine straight, not sagging or arched.\n" +
                                "3. Lower your body toward the wall and then push back.\n" +
                                "4. Repeat 10 times.",
                        "Wall Pushups"
                    ),
                    ProgramWorkoutItem(
                        R.drawable.pelvic_tilts,
                        "1. Stand about 3 feet away from a wall, facing the wall, with your feet shoulder-width apart.\n" +
                                "2. Lean forward and place your hands flat on the wall, in line with your shoulders. Your body should be in plank position, with your spine straight, not sagging or arched.\n" +
                                "3. Lower your body toward the wall and then push back.\n" +
                                "4. Repeat 10 times.",
                        "Pelvic Tilts"
                    ),
                    ProgramWorkoutItem(
                        R.drawable.shblade_squeeze,
                        "1. Stand about 3 feet away from a wall, facing the wall, with your feet shoulder-width apart.\n" +
                                "2. Lean forward and place your hands flat on the wall, in line with your shoulders. Your body should be in plank position, with your spine straight, not sagging or arched.\n" +
                                "3. Lower your body toward the wall and then push back.\n" +
                                "4. Repeat 10 times.",
                        "Shoulderblade Squeeze"
                    ),
                    ProgramWorkoutItem(
                        R.drawable.wall_pushups,
                        "1. Stand about 3 feet away from a wall, facing the wall, with your feet shoulder-width apart.\n" +
                                "2. Lean forward and place your hands flat on the wall, in line with your shoulders. Your body should be in plank position, with your spine straight, not sagging or arched.\n" +
                                "3. Lower your body toward the wall and then push back.\n" +
                                "4. Repeat 10 times.",
                        "Placeholder"
                    )
                )
            ),
            ProgramWorkout(
                2, mutableListOf(
                    ProgramWorkoutItem(
                        R.drawable.wall_pushups,
                        "1. Stand about 3 feet away from a wall, facing the wall, with your feet shoulder-width apart.\n" +
                                "2. Lean forward and place your hands flat on the wall, in line with your shoulders. Your body should be in plank position, with your spine straight, not sagging or arched.\n" +
                                "3. Lower your body toward the wall and then push back.\n" +
                                "4. Repeat 10 times.",
                        "Wall pushups"
                    ),
                    ProgramWorkoutItem(
                        R.drawable.bodyandsoul,
                        "blabla",
                        "Pushups"
                    ),
                    ProgramWorkoutItem(
                        R.drawable.fitandfab,
                        "blabla",
                        "Pushups"
                    ),
                    ProgramWorkoutItem(
                        R.drawable.bodyandsoul,
                        "blabla",
                        "Pushups"
                    )
                )
            ),
            ProgramWorkout(
                3, mutableListOf(
                    ProgramWorkoutItem(
                        R.drawable.wall_pushups,
                        "1. Stand about 3 feet away from a wall, facing the wall, with your feet shoulder-width apart.\n" +
                                "2. Lean forward and place your hands flat on the wall, in line with your shoulders. Your body should be in plank position, with your spine straight, not sagging or arched.\n" +
                                "3. Lower your body toward the wall and then push back.\n" +
                                "4. Repeat 10 times.",
                        "Wall pushups"
                    ),
                    ProgramWorkoutItem(
                        R.drawable.bodyandsoul,
                        "blabla",
                        "Pushups"
                    ),
                    ProgramWorkoutItem(
                        R.drawable.fitandfab,
                        "blabla",
                        "Pushups"
                    ),
                    ProgramWorkoutItem(
                        R.drawable.bodyandsoul,
                        "blabla",
                        "Pushups"
                    )
                )
            ),
            ProgramWorkout(
                4, mutableListOf(
                    ProgramWorkoutItem(
                        R.drawable.wall_pushups,
                        "1. Stand about 3 feet away from a wall, facing the wall, with your feet shoulder-width apart.\n" +
                                "2. Lean forward and place your hands flat on the wall, in line with your shoulders. Your body should be in plank position, with your spine straight, not sagging or arched.\n" +
                                "3. Lower your body toward the wall and then push back.\n" +
                                "4. Repeat 10 times.",
                        "Wall pushups"
                    ),
                    ProgramWorkoutItem(
                        R.drawable.bodyandsoul,
                        "blabla",
                        "Pushups"
                    ),
                    ProgramWorkoutItem(
                        R.drawable.fitandfab,
                        "blabla",
                        "Pushups"
                    ),
                    ProgramWorkoutItem(
                        R.drawable.bodyandsoul,
                        "blabla",
                        "Pushups"
                    )
                )
            )

          )
    }
    fun remove(id: Int) = workouts.removeAll{ workout -> workout.workout_id == id }
    fun get(id: Int): ProgramWorkout? = workouts.find { workout -> workout.workout_id == id }
    fun add(workout: ProgramWorkout) = workouts.add(workout)
}


