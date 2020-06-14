package enafilipovic.ferit.summerbody.Repositories

import enafilipovic.ferit.summerbody.Models.Program
import enafilipovic.ferit.summerbody.R

object ProgramRepo {
    val programs: MutableList<Program>
    init {
        programs =
            retrievePrograms()
    }
    private fun retrievePrograms():MutableList<Program>{
        return mutableListOf(
            Program(
                1,
                "Stretch & Relax",
                R.drawable.stretchandrelax,
                "   20 minutes",
                "   347 kcal",
                "   6 exercises",
                "There's nothing like a good stretch to refresh the body and reduce stress. But the benefits go way beyond relaxation: The act of stretching has also been shown to improve range of motion, boost energy and increase blood flow through the body. In addition to that good feeling, the act of lengthening the body or limbs in a consistent stretching program will produce large gains in flexibility and joint movement."
            ),
            Program(
                2,
                "Body & Soul",
                R.drawable.bodyandsoul,
                "   30 minutes",
                "   267 kcal",
                "   6 exercises",
                "There's nothing like a good stretch to refresh the body and reduce stress. But the benefits go way beyond relaxation: The act of stretching has also been shown to improve range of motion, boost energy and increase blood flow through the body. In addition to that good feeling, the act of lengthening the body or limbs in a consistent stretching program will produce large gains in flexibility and joint movement."
            ),
            Program(
                3,
                "Fit & Fab",
                R.drawable.fitandfab,
                "   25 minutes",
                "  467 kcal",
                "   6 exercises",
                "There's nothing like a good stretch to refresh the body and reduce stress. But the benefits go way beyond relaxation: The act of stretching has also been shown to improve range of motion, boost energy and increase blood flow through the body. In addition to that good feeling, the act of lengthening the body or limbs in a consistent stretching program will produce large gains in flexibility and joint movement."
            ),
            Program(
                4,
                "Muscle & Strength",
                R.drawable.muscandstrength,
                "   20 minutes",
                "   412 kcal",
                "   6 exercises",
                "There's nothing like a good stretch to refresh the body and reduce stress. But the benefits go way beyond relaxation: The act of stretching has also been shown to improve range of motion, boost energy and increase blood flow through the body. In addition to that good feeling, the act of lengthening the body or limbs in a consistent stretching program will produce large gains in flexibility and joint movement."
            )
        )
    }
    fun remove(id: Int) = programs.removeAll{ program -> program.id == id }
    fun get(id: Int): Program? = programs.find { program -> program.id == id }
    fun add(program: Program) = programs.add(program)
}


