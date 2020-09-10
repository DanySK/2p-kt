package it.unibo.tuprolog.solve.primitive

import it.unibo.tuprolog.core.*
import it.unibo.tuprolog.core.operators.Specifier
import it.unibo.tuprolog.solve.AbstractWrapper
import it.unibo.tuprolog.solve.ExecutionContext
import it.unibo.tuprolog.solve.Signature
import it.unibo.tuprolog.solve.exception.error.DomainError
import it.unibo.tuprolog.solve.exception.error.InstantiationError
import it.unibo.tuprolog.solve.exception.error.TypeError
import org.gciatto.kt.math.BigInteger
import it.unibo.tuprolog.core.List as LogicList

/**
 * Wrapper class for [Primitive] implementation
 *
 * @author Enrico
 * @author Giovanni
 */
abstract class PrimitiveWrapper<C : ExecutionContext> : AbstractWrapper<Primitive> {

    constructor(signature: Signature) : super(signature)
    constructor(name: String, arity: Int, vararg: Boolean = false) : super(name, arity, vararg)

    /** The function expressing the implementation of the primitive, without any check for application to correct signature */
    protected abstract fun uncheckedImplementation(request: Solve.Request<C>): Sequence<Solve.Response>

    /** Checked primitive implementation */
    @Suppress("UNCHECKED_CAST")
    final override val wrappedImplementation: Primitive =
        primitiveOf(signature, ::uncheckedImplementation as Primitive)

    companion object {

        // TODO: 16/01/2020 test the three "wrap" functions

        /**
         * Utility factory to build a [PrimitiveWrapper] out of a [Signature] and a [Primitive] function
         */
        fun <C : ExecutionContext> wrap(signature: Signature, primitive: Primitive): PrimitiveWrapper<C> =
            FromFunction(signature, primitive)

        /**
         * Utility factory to build a [PrimitiveWrapper] out of a [Primitive] function
         */
        fun <C : ExecutionContext> wrap(
            name: String,
            arity: Int,
            vararg: Boolean,
            primitive: Primitive
        ): PrimitiveWrapper<C> =
            FromFunction(name, arity, vararg, primitive)

        /**
         * Utility factory to build a [PrimitiveWrapper] out of a [Primitive] function
         */
        fun <C : ExecutionContext> wrap(name: String, arity: Int, primitive: Primitive): PrimitiveWrapper<C> =
            wrap(name, arity, false, primitive)

        /** Private class to support the wrap methods, without using the object literal notation */
        private class FromFunction<C : ExecutionContext>(
            signature: Signature,
            private val uncheckedPrimitive: Primitive
        ) : PrimitiveWrapper<C>(signature) {

            constructor(name: String, arity: Int, vararg: Boolean = false, uncheckedPrimitive: Primitive)
                    : this(Signature(name, arity, vararg), uncheckedPrimitive)

            override fun uncheckedImplementation(request: Solve.Request<C>): Sequence<Solve.Response> =
                uncheckedPrimitive(request)
        }

        private fun ensurerVisitor(context: ExecutionContext, procedure: Signature): TermVisitor<TypeError?> =
            object : TermVisitor<TypeError?> {
                override fun defaultValue(term: Term): Nothing? = null

                override fun visit(term: Struct) = when {
                    term.functor in Clause.notableFunctors && term.arity == 2 -> {
                        term.argsSequence.map { it.accept(this) }.filterNotNull().firstOrNull()
                    }
                    else -> defaultValue(term)
                }

                override fun visit(term: Numeric): TypeError {
                    return TypeError.forGoal(context, procedure, TypeError.Expected.CALLABLE, term)
                }
            }

        fun <C : ExecutionContext> Solve.Request<C>.checkTermIsRecursivelyCallable(term: Term): TypeError? =
            term.accept(ensurerVisitor(context, signature))

        /** Utility function to ensure that all arguments of Solve.Request are instantiated and *not* (still) Variables */
        fun <C : ExecutionContext> Solve.Request<C>.ensuringAllArgumentsAreInstantiated(): Solve.Request<C> =
            arguments.withIndex().firstOrNull { it.value is Var }?.let {
                ensureIsInstantiated(it.value, it.index)
            } ?: this

        private fun <C : ExecutionContext> Solve.Request<C>.ensureIsInstantiated(
            term: Term?,
            index: Int
        ): Solve.Request<C> =
            (term as? Var)?.let {
                throw InstantiationError.forArgument(
                    context,
                    signature,
                    it,
                    index
                )
            } ?: this

        fun <C : ExecutionContext> Solve.Request<C>.ensuringArgumentIsWellFormedClause(index: Int): Solve.Request<C> {
            ensuringArgumentIsInstantiated(index)
            ensuringArgumentIsStruct(index)
            val candidate = arguments[index]
            when {
                candidate is Clause -> {
                    if (!candidate.isWellFormed) {
                        throw DomainError.forArgument(context, signature, DomainError.Expected.CLAUSE, candidate, index)
                    }
                    return this
                }
                candidate is Struct && candidate.functor == Clause.FUNCTOR && candidate.arity == 2 ->
                    throw DomainError.forArgument(context, signature, DomainError.Expected.CLAUSE, candidate, index)
                else -> throw TypeError.forArgument(context, signature, TypeError.Expected.CALLABLE, candidate, index)
            }
        }

        fun <C : ExecutionContext> Solve.Request<C>.ensuringArgumentIsInstantiated(index: Int): Solve.Request<C> =
            ensureIsInstantiated(arguments[index], index)

        fun <C : ExecutionContext> Solve.Request<C>.ensuringArgumentIsNumeric(index: Int): Solve.Request<C> =
            when (val arg = arguments[index]) {
                !is Numeric -> throw TypeError.forArgument(context, signature, TypeError.Expected.NUMBER, arg, index)
                else -> this
            }

        fun <C : ExecutionContext> Solve.Request<C>.ensuringArgumentIsStruct(index: Int): Solve.Request<C> =
            when (val arg = arguments[index]) {
                !is Struct -> throw TypeError.forArgument(context, signature, TypeError.Expected.CALLABLE, arg, index)
                else -> this
            }

        fun <C : ExecutionContext> Solve.Request<C>.ensuringArgumentIsCallable(index: Int): Solve.Request<C> =
            ensuringArgumentIsStruct(index)

        fun <C : ExecutionContext> Solve.Request<C>.ensuringArgumentIsCompound(index: Int): Solve.Request<C> =
            when (val arg = arguments[index]) {
                !is Struct, is Atom -> throw TypeError.forArgument(
                    context,
                    signature,
                    TypeError.Expected.COMPOUND,
                    arg,
                    index
                )
                else -> this
            }

        fun <C : ExecutionContext> Solve.Request<C>.ensuringArgumentIsAtom(index: Int): Solve.Request<C> =
            when (val arg = arguments[index]) {
                !is Atom -> throw TypeError.forArgument(context, signature, TypeError.Expected.ATOM, arg, index)
                else -> this
            }

        fun <C : ExecutionContext> Solve.Request<C>.ensuringArgumentIsSpecifier(index: Int): Solve.Request<C> =
            arguments[index].let { arg ->
                when {
                    arg !is Atom -> throw DomainError.forArgument(context, signature, DomainError.Expected.OPERATOR_SPECIFIER, arg, index)
                    else -> {
                        try {
                            Specifier.fromTerm(arg)
                            this
                        } catch (e: IllegalArgumentException) {
                            throw DomainError.forArgument(context, signature, DomainError.Expected.OPERATOR_SPECIFIER, arg, index)
                        }
                    }
                }
            }

        fun <C : ExecutionContext> Solve.Request<C>.ensuringArgumentIsInteger(index: Int): Solve.Request<C> =
            when (val arg = arguments[index]) {
                !is Integer -> throw TypeError.forArgument(context, signature, TypeError.Expected.INTEGER, arg, index)
                else -> this
            }

        fun <C : ExecutionContext> Solve.Request<C>.ensuringArgumentIsList(index: Int): Solve.Request<C> =
            when (val arg = arguments[index]) {
                !is LogicList -> throw TypeError.forArgument(context, signature, TypeError.Expected.LIST, arg, index)
                else -> this
            }

        fun <C : ExecutionContext> Solve.Request<C>.ensuringArgumentIsNonNegativeInteger(index: Int): Solve.Request<C> =
            ensuringArgumentIsInteger(index)
                .arguments[index].let { arg ->
                when {
                    arg !is Integer || arg.intValue < BigInteger.ZERO -> throw DomainError.forArgument(
                        context,
                        signature,
                        DomainError.Expected.NOT_LESS_THAN_ZERO,
                        arg,
                        index
                    )
                    else -> this
                }
            }
    }
}
