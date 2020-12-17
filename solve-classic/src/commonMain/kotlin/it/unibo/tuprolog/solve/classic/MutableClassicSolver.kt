package it.unibo.tuprolog.solve.classic

import it.unibo.tuprolog.core.Clause
import it.unibo.tuprolog.core.Struct
import it.unibo.tuprolog.core.Term
import it.unibo.tuprolog.solve.ExecutionContextAware
import it.unibo.tuprolog.solve.FlagStore
import it.unibo.tuprolog.solve.InputStore
import it.unibo.tuprolog.solve.MutableSolver
import it.unibo.tuprolog.solve.OutputStore
import it.unibo.tuprolog.solve.flags.NotableFlag
import it.unibo.tuprolog.solve.getAllOperators
import it.unibo.tuprolog.solve.library.AliasedLibrary
import it.unibo.tuprolog.solve.library.Libraries
import it.unibo.tuprolog.solve.toOperatorSet
import it.unibo.tuprolog.theory.MutableTheory
import it.unibo.tuprolog.theory.RetractResult
import it.unibo.tuprolog.theory.Theory

internal class MutableClassicSolver(
    libraries: Libraries = Libraries.empty(),
    flags: FlagStore = FlagStore.empty(),
    staticKb: Theory = Theory.empty(),
    dynamicKb: Theory = MutableTheory.empty(),
    inputChannels: InputStore<*> = ExecutionContextAware.defaultInputChannels(),
    outputChannels: OutputStore<*> = ExecutionContextAware.defaultOutputChannels()
) : ClassicSolver(libraries, flags, staticKb, dynamicKb, inputChannels, outputChannels), MutableSolver {

    override fun loadLibrary(library: AliasedLibrary) {
        updateContext {
            val newLibraries = libraries + library
            copy(
                libraries = newLibraries,
                operators = operators + getAllOperators(newLibraries).toOperatorSet()
            )
        }
    }

    override fun unloadLibrary(library: AliasedLibrary) {
        updateContext {
            val newLibraries = libraries + library
            copy(
                libraries = newLibraries,
                operators = getAllOperators(newLibraries, staticKb, dynamicKb).toOperatorSet()
            )
        }
    }

    override fun setLibraries(libraries: Libraries) {
        updateContext {
            copy(
                libraries = libraries,
                operators = getAllOperators(libraries, staticKb, dynamicKb).toOperatorSet()
            )
        }
    }

    override fun loadStaticKb(theory: Theory) {
        updateContext {
            copy(
                staticKb = theory,
                operators = getAllOperators(libraries, theory, dynamicKb).toOperatorSet()
            )
        }
    }

    override fun appendStaticKb(theory: Theory) {
        updateContext {
            val newStaticKb = staticKb + theory
            copy(
                staticKb = newStaticKb,
                operators = operators + theory.getAllOperators().toOperatorSet()
            )
        }
    }

    override fun resetStaticKb() {
        updateContext {
            copy(
                staticKb = Theory.empty(),
                operators = getAllOperators(libraries, dynamicKb).toOperatorSet()
            )
        }
    }

    override fun loadDynamicKb(theory: Theory) {
        updateContext {
            copy(
                dynamicKb = theory.toMutableTheory(),
                operators = getAllOperators(libraries, staticKb, theory).toOperatorSet()
            )
        }
    }

    override fun appendDynamicKb(theory: Theory) {
        updateContext {
            copy(
                dynamicKb = theory.toMutableTheory(),
                operators = operators + theory.getAllOperators().toOperatorSet()
            )
        }
    }

    override fun resetDynamicKb() {
        updateContext {
            copy(
                dynamicKb = MutableTheory.empty(),
                operators = getAllOperators(libraries, staticKb).toOperatorSet()
            )
        }
    }

    override fun assertA(clause: Clause) {
        updateContext {
            copy(
                dynamicKb = dynamicKb.assertA(clause),
                operators = operators + listOf(clause).getAllOperators().toOperatorSet()
            )
        }
    }

    override fun assertA(fact: Struct) {
        updateContext {
            copy(dynamicKb = dynamicKb.assertA(fact))
        }
    }

    override fun assertZ(clause: Clause) {
        updateContext {
            copy(
                dynamicKb = dynamicKb.assertZ(clause),
                operators = operators + listOf(clause).getAllOperators().toOperatorSet()
            )
        }
    }

    override fun assertZ(fact: Struct) {
        updateContext {
            copy(dynamicKb = dynamicKb.assertZ(fact))
        }
    }

    override fun retract(clause: Clause): RetractResult<Theory> {
        val result = dynamicKb.retract(clause)
        updateContext {
            copy(
                dynamicKb = result.theory.toMutableTheory(),
                operators = operators - listOf(clause).getAllOperators().toOperatorSet()
            )
        }
        return result
    }

    override fun retract(fact: Struct): RetractResult<Theory> {
        val result = dynamicKb.retract(fact)
        updateContext {
            copy(dynamicKb = result.theory.toMutableTheory())
        }
        return result
    }

    override fun retractAll(clause: Clause): RetractResult<Theory> {
        val result = dynamicKb.retractAll(clause)
        updateContext {
            copy(
                dynamicKb = result.theory.toMutableTheory(),
                operators = operators - result.theory.getAllOperators().toOperatorSet()
            )
        }
        return result
    }

    override fun retractAll(fact: Struct): RetractResult<Theory> {
        val result = dynamicKb.retractAll(fact)
        updateContext {
            copy(dynamicKb = result.theory.toMutableTheory())
        }
        return result
    }

    override fun setFlag(name: String, value: Term) {
        updateContext {
            copy(flags = flags.set(name, value))
        }
    }

    override fun setFlag(flag: Pair<String, Term>) {
        updateContext {
            copy(flags = flags + flag)
        }
    }

    override fun setFlag(flag: NotableFlag) {
        updateContext {
            copy(flags = flags + flag)
        }
    }
}