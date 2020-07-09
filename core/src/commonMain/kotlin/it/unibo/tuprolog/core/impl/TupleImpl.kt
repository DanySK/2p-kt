package it.unibo.tuprolog.core.impl

import it.unibo.tuprolog.core.Term
import it.unibo.tuprolog.core.Tuple

internal class TupleImpl(override val left: Term, override val right: Term) :
    CollectionImpl(Tuple.FUNCTOR, arrayOf(left, right)), Tuple {

    override val unfoldedSequence: Sequence<Term> by lazy {
        sequenceOf(left) + if (right is Tuple) right.unfoldedSequence else sequenceOf(right)
    }

    override val unfoldedList: List<Term> by lazy {
        unfoldedSequence.toList()
    }

    override val unfoldedArray: Array<Term> by lazy {
        unfoldedList.toTypedArray()
    }

    override val size: Int get() = unfoldedList.size

    override val functor: String = Tuple.FUNCTOR

    override val args: Array<Term> get() = super<CollectionImpl>.args

    override fun toString(): String = unfoldedSequence.joinToString(", ", "(", ")")
}