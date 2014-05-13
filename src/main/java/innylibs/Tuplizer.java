package innylibs;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;

public class Tuplizer {

	public static <T> Iterable<List<T>>
			tuplize(final Iterable<? extends Iterable<? extends T>> inputs) {
		checkNotNull(inputs);
		return new FluentIterable<List<T>>() {
			@Override
			public Iterator<List<T>> iterator() {
				return tuplize(iterators(inputs));
			}
		};
	}

	/**
	 * Returns an iterator over the iterators of the given iterables.
	 */
	private static <T> UnmodifiableIterator<Iterator<? extends T>>
			iterators(Iterable<? extends Iterable<? extends T>> iterables) {
		final Iterator<? extends Iterable<? extends T>> iterableIterator =
				iterables.iterator();
		return new UnmodifiableIterator<Iterator<? extends T>>() {
			@Override
			public boolean hasNext() {
				return iterableIterator.hasNext();
			}

			@Override
			public Iterator<? extends T> next() {
				return iterableIterator.next().iterator();
			}
		};
	}

	private static <T> UnmodifiableIterator<List<T>>
			tuplize(final Iterator<Iterator<? extends T>> iterator) {
		checkNotNull(iterator);

		final ImmutableList<Iterator<? extends T>> delegates =
				ImmutableList.copyOf(iterator);
		// .toArray(new Iterator<?>[] {})

		return new UnmodifiableIterator<List<T>>() {
			@Override
			public boolean hasNext() {
				for (Iterator<?> d : delegates)
					if (!d.hasNext()) return false;
				return true;
			}

			@Override
			public List<T> next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}

				ImmutableList.Builder<T> builder = ImmutableList.builder();
				for (Iterator<? extends T> d : delegates)
					builder.add(d.next());
				return builder.build();
			}
		};
	}

}
