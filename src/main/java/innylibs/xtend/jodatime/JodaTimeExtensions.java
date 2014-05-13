package innylibs.xtend.jodatime;

import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.Pure;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.ReadWritableInstant;
import org.joda.time.ReadableDuration;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePeriod;

/**
 * Extension methods for Joda Time.
 * 
 * @author Marco Zanoni
 * 
 */
public final class JodaTimeExtensions {

	private JodaTimeExtensions() {}

	/**
	 * Gets the period corresponding to the passed number of seconds.
	 * 
	 * @param seconds
	 *            Number of seconds
	 * @return A Period representing the number of seconds
	 */
	@Pure
	@Inline(value = "$2.seconds($1)", imported = Period.class)
	public static Period seconds(final int seconds) {
		return Period.seconds(seconds);
	}

	@Pure
	@Inline(value = "$2.minutes($1)", imported = Period.class)
	public static Period minutes(final int days) {
		return Period.minutes(days);
	}

	@Pure
	@Inline(value = "$2.hours($1)", imported = Period.class)
	public static Period hours(final int days) {
		return Period.hours(days);
	}

	@Pure
	@Inline(value = "$2.days($1)", imported = Period.class)
	public static Period days(final int days) {
		return Period.days(days);
	}

	@Pure
	@Inline(value = "$2.weeks($1)", imported = Period.class)
	public static Period weeks(final int days) {
		return Period.weeks(days);
	}

	@Pure
	@Inline(value = "$2.months($1)", imported = Period.class)
	public static Period months(final int days) {
		return Period.months(days);
	}

	@Pure
	@Inline(value = "$2.years($1)", imported = Period.class)
	public static Period years(final int days) {
		return Period.years(days);
	}

	@Pure
	@Inline(value = "$1.plus($2)")
	public static Period
			operator_plus(final Period p1, final ReadablePeriod p2) {
		return p1.plus(p2);
	}

	@Pure
	@Inline(value = "$1.minus($2)")
	public static Period
			operator_minus(final Period p1, final ReadablePeriod p2) {
		return p1.minus(p2);
	}

	@Pure
	@Inline(value = "$1.plus($2)")
	public static Duration operator_plus(final Duration p1,
			final ReadableDuration p2) {
		return p1.plus(p2);
	}

	@Pure
	@Inline(value = "$1.minus($2)")
	public static Duration operator_minus(final Duration p1,
			final ReadableDuration p2) {
		return p1.minus(p2);
	}

	@Pure
	@Inline(value = "$1.negated()")
	public static Period operator_not(final Period p) {
		return p.negated();
	}

	@Pure
	@Inline(value = "new $3($1, $2)", imported = Interval.class)
	public static Interval operator_doubleDotLessThan(final ReadableInstant i1,
			final ReadableInstant i2) {
		return new Interval(i1, i2);
	}

	@Pure
	@Inline(value = "new $3($1, $2)", imported = Interval.class)
	public static DateTime operator_plus(final DateTime ts,
			final ReadableDuration duration) {
		return ts.plus(duration);
	}

	@Pure
	@Inline(value = "$1.plus($2)")
	public static DateTime operator_plus(final DateTime ts,
			final ReadablePeriod period) {
		return ts.plus(period);
	}

	@Pure
	@Inline(value = "$1.minus($2)")
	public static DateTime operator_minus(final DateTime ts,
			final ReadableDuration duration) {
		return ts.minus(duration);
	}

	@Pure
	@Inline(value = "$1.minus($2)")
	public static DateTime operator_minus(final DateTime ts,
			final ReadablePeriod period) {
		return ts.minus(period);
	}

	@Inline(value = "$1.add($2)")
	public static void operator_add(final ReadWritableInstant ts,
			final ReadableDuration duration) {
		ts.add(duration);
	}

	@Inline(value = "$1.add($2)")
	public static void operator_add(final ReadWritableInstant ts,
			final ReadablePeriod period) {
		ts.add(period);
	}

	@Inline(value = "$1.add($2, -1)")
	public static void operator_remove(final ReadWritableInstant ts,
			final ReadableDuration duration) {
		ts.add(duration, -1);
	}

	@Inline(value = "$1.add($2, -1)")
	public static void operator_remove(final ReadWritableInstant ts,
			final ReadablePeriod period) {
		ts.add(period, (-1));
	}

	@Pure
	@Inline(value = "$1.multipliedBy($2)")
	public static Period operator_multiply(final Period p, final int times) {
		return p.multipliedBy(times);
	}

//	public static Interval operator_doubleDotLessThan(DateTime start,
//			DateTime end) {
//		return new Interval(start, end);
//	}
}
