package it.unibo.tuprolog.solve.classic

import it.unibo.tuprolog.core.Struct
import it.unibo.tuprolog.core.operators.OperatorSet
import it.unibo.tuprolog.solve.ExecutionContextAware
import it.unibo.tuprolog.solve.FlagStore
import it.unibo.tuprolog.solve.InputStore
import it.unibo.tuprolog.solve.OutputStore
import it.unibo.tuprolog.solve.Solution
import it.unibo.tuprolog.solve.Solver
import it.unibo.tuprolog.solve.TimeDuration
import it.unibo.tuprolog.solve.classic.fsm.State
import it.unibo.tuprolog.solve.classic.fsm.StateInit
import it.unibo.tuprolog.solve.classic.fsm.clone
import it.unibo.tuprolog.solve.currentTimeInstant
import it.unibo.tuprolog.solve.getAllOperators
import it.unibo.tuprolog.solve.library.Libraries
import it.unibo.tuprolog.solve.toOperatorSet
import it.unibo.tuprolog.theory.MutableTheory
import it.unibo.tuprolog.theory.Theory

internal open class ClassicSolver(
    libraries: Libraries = Libraries.empty(),
    flags: FlagStore = FlagStore.empty(),
    staticKb: Theory = Theory.empty(),
    dynamicKb: Theory = MutableTheory.empty(),
    inputChannels: InputStore<*> = ExecutionContextAware.defaultInputChannels(),
    outputChannels: OutputStore<*> = ExecutionContextAware.defaultOutputChannels()
) : Solver {

    private var state: State = StateInit(
        ClassicExecutionContext(
            libraries = libraries,
            flags = flags,
            staticKb = staticKb,
            dynamicKb = dynamicKb.toMutableTheory(),
            operators = getAllOperators(libraries, staticKb, dynamicKb).toOperatorSet(),
            inputChannels = inputChannels,
            outputChannels = outputChannels
        )
    )

    protected fun updateContext(contextMapper: ClassicExecutionContext.() -> ClassicExecutionContext) {
        val ctx = state.context
        val newCtx = ctx.contextMapper()
        if (newCtx != ctx) {
            state = state.clone(newCtx)
        }
    }

    override fun solve(goal: Struct, maxDuration: TimeDuration): Sequence<Solution> {
        val initialContext = ClassicExecutionContext(
            query = goal,
            libraries = libraries,
            flags = flags,
            staticKb = staticKb,
            dynamicKb = dynamicKb.toMutableTheory(),
            operators = operators,
            inputChannels = inputChannels,
            outputChannels = outputChannels,
            maxDuration = maxDuration,
            startTime = currentTimeInstant()
        )

        state = StateInit(initialContext)

        return SolutionIterator(state) { newState, newStep ->
            require(newState.context.step == newStep)
            state = newState
        }.asSequence()
    }

    override val libraries: Libraries
        get() = state.context.libraries

    override val flags: FlagStore
        get() = state.context.flags

    override val staticKb: Theory
        get() = state.context.staticKb

    override val dynamicKb: Theory
        get() = state.context.dynamicKb

    override val inputChannels: InputStore<*>
        get() = state.context.inputChannels

    override val outputChannels: OutputStore<*>
        get() = state.context.outputChannels

    override val operators: OperatorSet
        get() = state.context.operators
}