package it.unibo.tuprolog.solve.problogclassic.fsm

import it.unibo.tuprolog.core.Substitution
import it.unibo.tuprolog.core.Term
import it.unibo.tuprolog.solve.Solution
import it.unibo.tuprolog.solve.exception.TuPrologRuntimeException
import it.unibo.tuprolog.solve.problogclassic.ProblogClassicExecutionContext
import it.unibo.tuprolog.utils.Cursor

internal data class StatePrimitiveExecution(override val context: ProblogClassicExecutionContext) : AbstractState(context) {

    private fun ProblogClassicExecutionContext.copyFromCurrentPrimitive(
        goals: Cursor<out Term>? = null,
        parentProcedure: Boolean = false,
        substitution: Substitution? = null
    ): ProblogClassicExecutionContext {
        val ctx = primitives.current?.let { apply(it.sideEffects) } ?: this
        return ctx.copy(
            goals = goals ?: this.goals,
            procedure = if (parentProcedure) parent?.procedure else procedure,
            primitives = Cursor.empty(),
            substitution = (substitution ?: this.substitution) as Substitution.Unifier,
            step = nextStep()
        )
    }

    override fun computeNext(): State = try {
        when (val sol = context.primitives.current?.solution) {
            is Solution.Yes -> {
                StateGoalSelection(
                    context.copyFromCurrentPrimitive(
                        goals = context.goals.next,
                        parentProcedure = true,
                        substitution = (context.substitution + sol.substitution)
                    )
                )
            }
            is Solution.No -> {
                StateBacktracking(context.copyFromCurrentPrimitive())
            }
            is Solution.Halt -> {
                StateException(
                    sol.exception.updateContext(context),
                    context.copyFromCurrentPrimitive()
                )
            }
            null -> {
                StateBacktracking(context.copyFromCurrentPrimitive())
            }
            else -> throw IllegalStateException("This should never happen")
        }
    } catch (exception: TuPrologRuntimeException) {
        StateException(
            exception.updateContext(context),
            context.copy(step = nextStep())
        )
    }
}
