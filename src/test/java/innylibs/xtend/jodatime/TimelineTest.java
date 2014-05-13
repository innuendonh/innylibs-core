package innylibs.xtend.jodatime;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.Range;

@RunWith(JUnit4.class)
public class TimelineTest {

	@Test(expected = NullPointerException.class)
	public void testCreateNull() {
		Timeline.create(null);
	}

	@Test
	public void testCreate() {
		assertNotNull(Timeline.create(Period.days(1)));
	}

	@Test
	public void testDaySequence() {
		final Timeline tl = Timeline.create(Period.days(1));
		final DateTime start = DateTime.parse("2014-01-01");
		final DateTime end = DateTime.parse("2014-01-31");

		final Range<DateTime> range = Range.closed(start, end);

		final ContiguousSet<DateTime> list = ContiguousSet.create(range, tl);
		assertEquals(start, list.first());
		assertEquals(start.plus(Period.days(1)), list.higher(list.first()));
		assertEquals(end, list.last());
		assertEquals(end.minus(Period.days(1)), list.lower(list.last()));
		assertEquals(31, list.size());
	}

	@Test
	public void testDistance() {
		final Timeline tl = Timeline.create(Period.days(1));
		final DateTime start = DateTime.parse("2014-01-01");
		final DateTime end = DateTime.parse("2014-01-31");

		assertEquals(30, tl.distance(start, end));

		assertEquals(0, tl.distance(start, start));
	}

	@Test
	public void testDistanceSpurious() {
		final Timeline tl = Timeline.create(Period.days(1));
		final DateTime start = DateTime.parse("2014-01-01");
		final DateTime endPlus = DateTime.parse("2014-01-31T00:01");
		final DateTime endMinus = DateTime.parse("2014-01-30T23:59");

		assertEquals(31, tl.distance(start, endPlus));
		assertEquals(30, tl.distance(start, endMinus));

		assertEquals(1, tl.distance(start, start.plus(Period.minutes(1))));
		assertEquals(1, tl.distance(start, start.plus(Period.hours(1))));
		assertEquals(2, tl.distance(start, start.plus(Period.hours(1)).plus(Period.days(1))));
	}

}
