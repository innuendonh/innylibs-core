package innylibs;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;
import com.google.common.collect.UnmodifiableIterator;

public class GroupingIterator<T> extends UnmodifiableIterator<List<T>> {

	private final PeekingIterator<T> iterator;
	private final Comparator<T> comparator;

	private GroupingIterator(final Iterator<T> iterator,
			Comparator<T> comparator) {
		this.iterator = Iterators.peekingIterator(iterator);
		this.comparator = comparator;
	}

	public static <T> UnmodifiableIterator<List<T>>
			groupBy(final Iterator<T> iterator, final Comparator<T> comparator) {
		checkNotNull(iterator);
		checkNotNull(comparator);
		return new GroupingIterator<T>(iterator, comparator);
	}

	public static <T> Iterable<List<T>> groupBy(final Iterable<T> iterable,
			final Comparator<T> comparator) {
		checkNotNull(iterable);
		checkNotNull(comparator);
		return new Iterable<List<T>>() {
			@Override
			public Iterator<List<T>> iterator() {
				return groupBy(iterable.iterator(), comparator);
			}
		};
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public List<T> next() {
		if (!hasNext()) throw new NoSuchElementException();

		final ImmutableList.Builder<T> array = ImmutableList.builder();

		T last;
		do {
			last = iterator.next();
			array.add(last);
		} while (hasNext() && comparator.compare(last, iterator.peek()) == 0);

		return array.build();
	}
}
