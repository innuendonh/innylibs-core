package innylibs

import static extension innylibs.GroupingIterator.*
import org.junit.Test
import static extension org.junit.Assert.*

class GroupingIteratorTest {

	@Test
	def void testGroupBy() {
		#[].assertEquals(#[].iterator.groupBy[a, b|0].toList)
		#[].assertEquals(#[].iterator.groupBy[a, b|1].toList)
		#[].assertEquals(#[].iterator.groupBy[a, b|-1].toList)

		#[#["a"]].assertEquals(#["a"].iterator.groupBy[a, b|0].toList)
		#[#["a"]].assertEquals(#["a"].iterator.groupBy[a, b|1].toList)
		#[#["a"]].assertEquals(#["a"].iterator.groupBy[a, b|-1].toList)

		#[#["a", "a"]].assertEquals(#["a", "a"].iterator.groupBy[a, b|0].toList)
		#[#["a"], #["a"]].assertEquals(#["a", "a"].iterator.groupBy[a, b|1].toList)
		#[#["a"], #["a"]].assertEquals(#["a", "a"].iterator.groupBy[a, b|-1].toList)

		#[#["a", "a"], #["b"]].assertEquals(#["a", "a", "b"].iterator.groupBy[a, b|if(a == b) 0 else 1].toList)

	}
}
