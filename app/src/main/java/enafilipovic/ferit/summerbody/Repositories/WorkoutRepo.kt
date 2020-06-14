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
                        "Wall pushups"
                    ),
                    ProgramWorkoutItem(
                        R.drawable.pelvic_tilts,
                        "1. Take a deep breath, tighten your buttocks, and tilt your hips slightly forward.\n" +
                                "2.Hold for a 3-count.\n" +
                                "3. Now tilt your hips back, and hold for 3 seconds. (It’s a very subtle movement.)\n" +
                                "4. Repeat 8 to 12 times.",
                        "Pelvic tilts"
                    ),
                    ProgramWorkoutItem(
                        R.drawable.shblade_squeeze,
                        "1. Sit up straight in your seat, rest your hands in your lap, and squeeze your shoulder blades toward one another.\n" +
                                "2. Focus on keeping your shoulders down, not hunched up toward your ears, and hold for 3 seconds.\n" +
                                "3. Release and repeat 8 to 12 times.",
                        "Shoulderblade squeeze"
                    ),
                    ProgramWorkoutItem(
                        R.drawable.toe_taps,
                        "1. Sitting in a chair and keeping your heels on the floor, lift your toes high enough that you can feel the muscles along your shin working. (This helps keep blood circulating in your legs and also strengthens the lower leg.)\n" +
                                "2. Repeat 20 times.",
                        "Toe taps"
                    )
                )
            ),
            ProgramWorkout(
                2, mutableListOf(
                    ProgramWorkoutItem(
                        R.drawable.heel_raises,
                        "1. Sitting in a chair, keep your toes and the balls of your feet on the floor and lift your heels.\n" +
                                "2. Repeat 20 times.",
                        "Heel raises"
                    ),
                    ProgramWorkoutItem(
                        R.drawable.knee_lifts,
                        "1. Seated in a chair, with your arms resting but not pressing on the armrests, contract your right quadriceps muscles and lift your leg. Your knee and the back of your thigh should be 2 or 3 inches off the seat.\n" +
                                "2. Pause for 3 seconds and slowly lower your leg.\n" +
                                "3. Complete 8 to 12 repetitions and then repeat with the opposite leg.",
                        "Knee Lifts"
                    ),
                    ProgramWorkoutItem(
                        R.drawable.ankle_rotations,
                        "1. Seated in a chair, lift your right foot off the floor and slowly rotate your foot 5 times to the right and then 5 times to the left.\n" +
                                "2. Repeat with the left foot.",
                        "Ankle rotations"
                    )
                )
            ),
            ProgramWorkout(
                3, mutableListOf(
                    ProgramWorkoutItem(
                        R.drawable.neck_stretch,
                        "1. Stand with your feet flat on the floor, shoulder-width apart. Keep your hands relaxed at your sides.\n" +
                                "2. Don’t tip your head forward or backward as you turn your head slowly to the right. Stop when you feel a slight stretch. Hold for 10 to 30 seconds.\n" +
                                "3. Now turn to the left. Hold for 10 to 30 seconds.\n" +
                                "4. Repeat 3 to 5 times.",
                        "Neck stretch"
                    ),
                    ProgramWorkoutItem(
                        R.drawable.back_stretch,
                        "1. Sit in a firm chair. Place your feet flat on the floor, shoulder-width apart.\n" +
                                "2. Hold your arms up and out in front at shoulder height, with your palms facing outward and the backs of your hands pressed together. Relax your shoulders so they’re not scrunched up near your ears.\n" +
                                "3. Reach your fingertips out until you feel a stretch. Your back will move away from the back of the chair.\n" +
                                "4. Stop and hold for 10 to 30 seconds.\n" +
                                "5. Repeat 3 to 5 times.",
                        "Back stretch"
                    ),
                    ProgramWorkoutItem(
                        R.drawable.knee_lifts,
                        "1. Seated in a chair, with your arms resting but not pressing on the armrests, contract your right quadriceps muscles and lift your leg. Your knee and the back of your thigh should be 2 or 3 inches off the seat.\n" +
                                "2. Pause for 3 seconds and slowly lower your leg.\n" +
                                "3. Complete 8 to 12 repetitions and then repeat with the opposite leg.",
                        "Knee Lifts"
                    ),
                    ProgramWorkoutItem(
                        R.drawable.ankle_rotations,
                        "1. Seated in a chair, lift your right foot off the floor and slowly rotate your foot 5 times to the right and then 5 times to the left.\n" +
                                "2. Repeat with the left foot.",
                        "Ankle rotations"
                    )
                )
            ),
            ProgramWorkout(
                4, mutableListOf(
                    ProgramWorkoutItem(
                        R.drawable.knee_lifts,
                        "1. Seated in a chair, with your arms resting but not pressing on the armrests, contract your right quadriceps muscles and lift your leg. Your knee and the back of your thigh should be 2 or 3 inches off the seat.\n" +
                                "2. Pause for 3 seconds and slowly lower your leg.\n" +
                                "3. Complete 8 to 12 repetitions and then repeat with the opposite leg.",
                        "Knee Lifts"
                    ),
                    ProgramWorkoutItem(
                        R.drawable.ankle_rotations,
                        "1. Seated in a chair, lift your right foot off the floor and slowly rotate your foot 5 times to the right and then 5 times to the left.\n" +
                                "2. Repeat with the left foot.",
                        "Ankle rotations"
                    ),
                    ProgramWorkoutItem(
                        R.drawable.neck_stretch,
                        "1. Stand with your feet flat on the floor, shoulder-width apart. Keep your hands relaxed at your sides.\n" +
                                "2. Don’t tip your head forward or backward as you turn your head slowly to the right. Stop when you feel a slight stretch. Hold for 10 to 30 seconds.\n" +
                                "3. Now turn to the left. Hold for 10 to 30 seconds.\n" +
                                "4. Repeat 3 to 5 times.",
                        "Neck stretch"
                    ),
                    ProgramWorkoutItem(
                        R.drawable.back_stretch,
                        "1. Sit in a firm chair. Place your feet flat on the floor, shoulder-width apart.\n" +
                                "2. Hold your arms up and out in front at shoulder height, with your palms facing outward and the backs of your hands pressed together. Relax your shoulders so they’re not scrunched up near your ears.\n" +
                                "3. Reach your fingertips out until you feel a stretch. Your back will move away from the back of the chair.\n" +
                                "4. Stop and hold for 10 to 30 seconds.\n" +
                                "5. Repeat 3 to 5 times.",
                        "Back stretch"
                    )
                )
            )

          )
    }
    fun remove(id: Int) = workouts.removeAll{ workout -> workout.workout_id == id }
    fun get(id: Int): ProgramWorkout? = workouts.find { workout -> workout.workout_id == id }
    fun add(workout: ProgramWorkout) = workouts.add(workout)
}


