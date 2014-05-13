package innylibs.xtend.guava;

import java.util.Map;

import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipse.xtext.xbase.lib.Pure;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Range;

/**
 * Extension methods for Guava utils.
 * 
 * @author Marco Zanoni
 * 
 */
public final class GuavaExtensions {

	/**
	 * Cannot instantiate this class
	 */
	private GuavaExtensions() {}

	public static <K, V> Map<K, V> toMap(final Iterable<Pair<K, V>> pairs) {
		final ImmutableMap.Builder<K, V> b = ImmutableMap.builder();
		for (final Pair<K, V> p : pairs)
			b.put(p.getKey(), p.getValue());
		return b.build();
	}

	public static <K, V> ImmutableMultimap<K, V>
			toMultimap(final Iterable<Pair<K, V>> pairs) {
		final ImmutableMultimap.Builder<K, V> b = ImmutableMultimap.builder();
		for (final Pair<K, V> p : pairs)
			b.put(p.getKey(), p.getValue());
		return b.build();
	}

	public static <K, V> ImmutableBiMap<K, V>
			toBiMap(final Map<? extends K, ? extends V> map) {
		return ImmutableBiMap.copyOf(map);
	}

	/**
	 * Closed/inclusive ".." range for Double variables.
	 * 
	 * @param lower
	 *            Lower bound of the range.
	 * @param upper
	 *            Upper bound of the range.
	 * @return A closed range
	 */
	@Pure
	@Inline(value = "$3.closed($1, $2)", imported = Range.class,
		statementExpression = false)
	public static Range<Double> operator_upTo(Double lower, Double upper) {
		return Range.closed(lower, upper);
	}

	/**
	 * Open-closed "<.." range for Double variables.
	 * 
	 * @param lower
	 *            Lower bound of the range.
	 * @param upper
	 *            Upper bound of the range.
	 * @return An open-closed range
	 */
	@Pure
	@Inline(value = "$3.openClosed($1, $2)", imported = Range.class,
		statementExpression = false)
	public static Range<Double> operator_greaterThanDoubleDot(Double lower,
			Double upper) {
		return Range.openClosed(lower, upper);
	}

	/**
	 * Closed-open "..<" range for Double variables.
	 * 
	 * @param lower
	 *            Lower bound of the range.
	 * @param upper
	 *            Upper bound of the range.
	 * @return An closed-open range
	 */
	@Pure
	@Inline(value = "$3.closedOpen($1, $2)", imported = Range.class,
		statementExpression = false)
	public static Range<Double> operator_doubleDotLessThan(Double lower,
			Double upper) {
		return Range.closedOpen(lower, upper);
	}

}
