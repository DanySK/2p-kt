package it.unibo.tuprolog.solve.libs.oop.impl

import it.unibo.tuprolog.core.*
import it.unibo.tuprolog.solve.libs.oop.ObjectRef
import it.unibo.tuprolog.solve.libs.oop.Result
import it.unibo.tuprolog.solve.libs.oop.invoke

internal class ObjectRefImpl(override val `object`: Any) : ObjectRef, Atom by Atom.of(nameOf(`object`)) {
    companion object {
        private fun nameOf(any: Any): String = "<object:${any::class.qualifiedName}#${any.hashCode()}>"
    }

    override val isConstant: Boolean
        get() = true

    override fun invoke(methodName: String, arguments: List<Term>): Result =
        `object`.invoke(methodName, arguments)

    override fun freshCopy(): Atom = this

    override fun freshCopy(scope: Scope): Atom = this

    override fun <T : Term> `as`(): T = this as T

    override fun <T : Term> castTo(): T = this as T

    override fun apply(substitution: Substitution): Term = this

    override fun apply(substitution: Substitution, vararg substitutions: Substitution): Term = this

    override fun get(substitution: Substitution, vararg substitutions: Substitution): Term = this

    override fun <T> accept(visitor: TermVisitor<T>): T =
        visitor.visit(this)
}