package it.unibo.tuprolog.solve.systemtest

import it.unibo.tuprolog.solve.*
import it.unibo.tuprolog.solve.channel.InputChannel
import it.unibo.tuprolog.solve.channel.OutputChannel
import it.unibo.tuprolog.solve.exception.PrologWarning
import it.unibo.tuprolog.solve.library.AliasedLibrary
import it.unibo.tuprolog.solve.library.Libraries
import it.unibo.tuprolog.solve.stdlib.DefaultBuiltins
import it.unibo.tuprolog.theory.ClauseDatabase
import kotlin.test.Ignore
import kotlin.test.Test

class ClassicSolverSystemTesting : SolverFactory, SolverTest {

    private val prototype = SolverTest.prototype(this)

    override val defaultBuiltins: AliasedLibrary
        get() = DefaultBuiltins

    override fun solverOf(
        libraries: Libraries,
        flags: PrologFlags,
        staticKb: ClauseDatabase,
        dynamicKb: ClauseDatabase,
        stdIn: InputChannel<String>,
        stdOut: OutputChannel<String>,
        stdErr: OutputChannel<String>,
        warnings: OutputChannel<PrologWarning>
    ) = Solver.classic(
        libraries, flags, staticKb, dynamicKb, stdIn, stdOut, stdErr, warnings
    )

    override fun mutableSolverOf(
        libraries: Libraries,
        flags: PrologFlags,
        staticKb: ClauseDatabase,
        dynamicKb: ClauseDatabase,
        stdIn: InputChannel<String>,
        stdOut: OutputChannel<String>,
        stdErr: OutputChannel<String>,
        warnings: OutputChannel<PrologWarning>
    ) = MutableSolver.classic(
        libraries, flags, staticKb, dynamicKb, stdIn, stdOut, stdErr, warnings
    )

    @Test
    override fun testFindAll() {
        prototype.testFindAll()
    }

    @Test
    override fun testAssert() {
        prototype.testAssert()
    }

    @Test
    override fun testAssertZ() {
        prototype.testAssertZ()
    }

    @Test
    @Ignore
    override fun testAssertA() {
        prototype.testAssertA()
    }

    @Test
    override fun testWrite() {
        prototype.testWrite()
    }

    @Test
    override fun testStandardOutput() {
        prototype.testStandardOutput()
    }

    @Test
    override fun testTrue() {
        prototype.testTrue()
    }

    @Test
    override fun testIfThen1() {
        prototype.testIfThen1()
    }

    @Test
    override fun testIfThen2() {
        prototype.testIfThen2()
    }

    @Test
    override fun testIfThenElse1() {
        prototype.testIfThenElse1()
    }

    @Test
    override fun testIfThenElse2() {
        prototype.testIfThenElse2()
    }


    @Test
    override fun testTimeout1() {
        prototype.testTimeout1()
    }

    @Test
    override fun testTimeout2() {
        prototype.testTimeout2()
    }

    @Test
    override fun testTimeout3() {
        prototype.testTimeout3()
    }

    @Test
    override fun testTimeout4() {
        prototype.testTimeout4()
    }

    @Test
    override fun testUnification() {
        prototype.testUnification()
    }

    @Test
    override fun testSimpleCutAlternatives() {
        prototype.testSimpleCutAlternatives()
    }

    @Test
    override fun testCutAndConjunction() {
        prototype.testCutAndConjunction()
    }

    @Test
    override fun testCutConjunctionAndBacktracking() {
        prototype.testCutConjunctionAndBacktracking()
    }

    @Test
    override fun testMaxDurationParameterAndTimeOutException() {
        prototype.testMaxDurationParameterAndTimeOutException()
    }

    @Test
    override fun testPrologStandardSearchTreeExample() {
        prototype.testPrologStandardSearchTreeExample()
    }

    @Test
    override fun testPrologStandardSearchTreeWithCutExample() {
        prototype.testPrologStandardSearchTreeWithCutExample()
    }

    @Test
    override fun testBacktrackingWithCustomReverseListImplementation() {
        prototype.testBacktrackingWithCustomReverseListImplementation()
    }

    @Test
    override fun testWithPrologStandardConjunctionExamples() {
        prototype.testWithPrologStandardConjunctionExamples()
    }

    @Test
    override fun testConjunctionProperties() {
        prototype.testConjunctionProperties()
    }

    @Test
    override fun testCallPrimitive() {
        prototype.testCallPrimitive()
    }

    @Test
    override fun testCallPrimitiveTransparency() {
        prototype.testCallPrimitiveTransparency()
    }

    @Test
    override fun testCatchPrimitive() {
        prototype.testCatchPrimitive()
    }

    @Test
    override fun testCatchPrimitiveTransparency() {
        prototype.testCatchPrimitiveTransparency()
    }

    @Test
    override fun testHaltPrimitive() {
        prototype.testHaltPrimitive()
    }

    @Test
    override fun testNotPrimitive() {
        prototype.testNotPrimitive()
    }

    @Test
    override fun testNotModularity() {
        prototype.testNotModularity()
    }

    @Test
    override fun testIfThenRule() {
        prototype.testIfThenRule()
    }

    @Test
    override fun testIfThenElseRule() {
        prototype.testIfThenElseRule()
    }

    @Test
    override fun testNumbersRangeListGeneration() {
        prototype.testNumbersRangeListGeneration()
    }

    @Test
    override fun testConjunction() {
        prototype.testConjunction()
    }

    @Test
    override fun testConjunctionWithUnification() {
        prototype.testConjunctionWithUnification()
    }

    @Test
    override fun testBuiltinApi() {
        prototype.testBuiltinApi()
    }

    @Test
    override fun testDisjunction() {
        prototype.testDisjunction()
    }

    @Test
    override fun testFailure() {
        prototype.testFailure()
    }

    @Test
    override fun testDisjunctionWithUnification() {
        prototype.testDisjunctionWithUnification()
    }

    @Test
    override fun testConjunctionOfConjunctions() {
        prototype.testConjunctionOfConjunctions()
    }

    @Test
    override fun testMember() {
        prototype.testMember()
    }

    @Test
    override fun testBasicBacktracking1() {
        prototype.testBasicBacktracking1()
    }

    @Test
    override fun testBasicBacktracking2() {
        prototype.testBasicBacktracking2()
    }

    @Test
    override fun testBasicBacktracking3() {
        prototype.testBasicBacktracking3()
    }

    @Test
    override fun testBasicBacktracking4() {
        prototype.testBasicBacktracking4()
    }

    @Test
    @Ignore
    override fun testStandardGreaterThan() {
        prototype.testStandardGreaterThan()
    }

    @Test
    override fun testStandardEqual() {
        prototype.testStandardEqual()
    }

    @Test
    @Ignore
    override fun testStandardLowerThan() {
        prototype.testStandardLowerThan()
    }

    @Test
    override fun testStandardGreaterThanOrEqual() {
        prototype.testStandardGreaterThanOrEqual()
    }

    @Test
    @Ignore
    override fun testStandardLowerThanOrEqual() {
        prototype.testStandardLowerThanOrEqual()
    }

    @Test
    override fun testStandardNotEqual() {
        prototype.testStandardNotEqual()
    }


}
