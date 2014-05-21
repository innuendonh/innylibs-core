package innylibs

import com.google.common.collect.UnmodifiableIterator
import java.util.Iterator
import java.util.List
import java.util.NoSuchElementException

import static com.google.common.base.Preconditions.*

/**
 * Creates a sequence of tuples of order K from a set of K sequences.
 * Tuple n contains the elements in position n of each of the K sequences. 
 * If sequences are of different length, the tuple sequence has the length of the
 * shorter of the input sequences.
 * 
 * Tuples are computed on the fly while iterating.
 */
final class Tuplizer<E> extends UnmodifiableIterator<List<E>> {

	val List<Iterator<? extends E>> delegates

	private new(List<Iterator<? extends E>> delegates) {
		this.delegates = delegates.immutableCopy
	}

	override hasNext() {
		delegates.forall[hasNext]
	}

	override next() {
		if(hasNext)
			delegates.map[next].toList.immutableCopy
		else
			throw new NoSuchElementException
	}
	
	/**
	 * Gets a sequence of tuples from the sequence of sequences in input.
	 */
	def static <T> Iterable<List<T>> tuplize(Iterable<? extends Iterable<? extends T>> inputs) {
		checkNotNull(inputs);
		[|new Tuplizer<T>(inputs.map[iterator].toList)]
	}

}
