package innylibs.xtend.jodatime;

import static com.google.common.base.Preconditions.*;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.MutableDateTime;
import org.joda.time.Period;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;

/**
 * A domain for representing a timeline with a discrete tick. The tick is
 * represented with a {@link Period}, allowing more abstract reasoning than a
 * {@link Duration}. The latter could be implemented as an alternative if we
 * want to be more precise in time and rely less on calendars.
 * 
 * @author Marco Zanoni
 * 
 */
public class Timeline extends DiscreteDomain<DateTime> {

	private final Period tick;

	private Timeline(final Period tick) {
		this.tick = tick;
	}

	@Override
	public DateTime next(DateTime value) {
		return value.plus(tick);
	}

	@Override
	public DateTime previous(DateTime value) {
		return value.minus(tick);
	}

	/**
	 * The distance among two instants in time is the number of periods separing
	 * them. This implementation allows start and end to be spurious, i.e., to
	 * be separated by a non-discrete number of periods. In that case, the
	 * number of returned periods is the minumum number allowing to reach an
	 * instant greater than the end.
	 */
	@Override
	public long distance(DateTime start, DateTime end) {
		final long interval = new Duration(start, end).getMillis();
		final long tickLength = tick.toDurationFrom(start).getMillis();
		long times = Math.max(interval / tickLength - 1, 0);

		final MutableDateTime cursor = start.toMutableDateTime();
		for (cursor.add(tick, (int) times); cursor.compareTo(end) < 0; cursor.add(tick)) {
			times++;
		}
		return times;
	}

	public static Timeline create(Period tick) {
		return new Timeline(checkNotNull(tick));
	}

	public static ContiguousSet<DateTime> timeline(DateTime start,
			DateTime end,
			Period tick) {
		return ContiguousSet.create(Range.closed(start, end), create(tick));
	}

	public static ContiguousSet<DateTime> timeline(DateTime start,
			Period tick,
			int length) {
		checkArgument(length > 0);
		return ContiguousSet.create(Range.closed(start,
				start.plus(tick.multipliedBy(length))),
				create(tick));
	}
}
