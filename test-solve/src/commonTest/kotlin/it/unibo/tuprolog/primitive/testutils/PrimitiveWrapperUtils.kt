package it.unibo.tuprolog.primitive.testutils

import it.unibo.tuprolog.core.Term
import it.unibo.tuprolog.primitive.Primitive
import it.unibo.tuprolog.primitive.PrimitiveWrapper
import it.unibo.tuprolog.primitive.Signature
import it.unibo.tuprolog.primitive.extractSignature
import it.unibo.tuprolog.solve.DummyInstances
import it.unibo.tuprolog.solve.ExecutionContext
import it.unibo.tuprolog.solve.Solve
import kotlin.test.assertTrue

/**
 * Test class for [PrimitiveWrapper]
 *
 * @author Enrico
 */
internal object PrimitiveWrapperUtils {

    /** A default primitive result to be used in tests */
    internal val defaultPrimitiveResult = emptySequence<Nothing>()
    /** A test primitive */
    internal val primitive: Primitive = { defaultPrimitiveResult }

    /** A function to create a Solve.Request with provided [signature] and [argList] */
    internal fun createPrimitiveRequest(signature: Signature, argList: List<Term>) =
        Solve.Request(signature, argList, DummyInstances.executionContext)

    /** Utility function to create a primitive wrapper */
    internal inline fun createPrimitiveWrapper(
        signature: Signature,
        crossinline uncheckedImplementation: Primitive
    ): PrimitiveWrapper<ExecutionContext> = PrimitiveWrapper.wrap(signature) { uncheckedImplementation(it) }

    /** All under test requests */
    private val allRequests by lazy {
        (WrapperUtils.allMatchingRawStruct + WrapperUtils.allNotMatchingStruct).flatten()
            .map { createPrimitiveRequest(it.extractSignature(), it.argsList) }
    }

    /** All ground requests */
    internal val allGroundRequests by lazy {
        allRequests.filter { it.query.isGround }
            .also { assertTrue("Test data empty") { it.isNotEmpty() } }
    }

    /** All non-ground requests */
    internal val nonAllGroundRequests by lazy {
        allRequests.filterNot { it.query.isGround }
            .also { assertTrue("Test data empty") { it.isNotEmpty() } }
    }
}