package it.unibo.tuprolog.solve.solver.statemachine.state

import it.unibo.tuprolog.solve.Solve
import it.unibo.tuprolog.solve.solver.SolverStrategies
import kotlinx.coroutines.CoroutineScope

/**
 * Base class of states representing the computation end
 *
 * @author Enrico
 */
internal sealed class StateEnd(
        override val solveRequest: Solve.Request,
        override val executionScope: CoroutineScope,
        override val solverStrategies: SolverStrategies
) : AbstractState(solveRequest, executionScope, solverStrategies), FinalState {

    internal data class True(
            override val solveRequest: Solve.Request,
            override val executionScope: CoroutineScope,
            override val solverStrategies: SolverStrategies
    ) : StateEnd(solveRequest, executionScope, solverStrategies) {

        override fun behave(): Sequence<State> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    internal data class TrueWithChoicePoint(
            override val solveRequest: Solve.Request,
            override val executionScope: CoroutineScope,
            override val solverStrategies: SolverStrategies
    ) : StateEnd(solveRequest, executionScope, solverStrategies) {

        override fun behave(): Sequence<State> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    internal data class False(
            override val solveRequest: Solve.Request,
            override val executionScope: CoroutineScope,
            override val solverStrategies: SolverStrategies
    ) : StateEnd(solveRequest, executionScope, solverStrategies) {

        override fun behave(): Sequence<State> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    internal data class Halt(
            override val solveRequest: Solve.Request,
            override val executionScope: CoroutineScope,
            override val solverStrategies: SolverStrategies
    ) : StateEnd(solveRequest, executionScope, solverStrategies) {

        override fun behave(): Sequence<State> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    internal data class Timeout(
            override val solveRequest: Solve.Request,
            override val executionScope: CoroutineScope,
            override val solverStrategies: SolverStrategies
    ) : StateEnd(solveRequest, executionScope, solverStrategies) {

        override fun behave(): Sequence<State> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}
