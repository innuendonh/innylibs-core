package innylibs

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import static org.junit.Assert.*

@RunWith(JUnit4)
class TuplizerTest {

	@Test
	def void testTuplizer() {
		val Iterable<Integer> first = #[1, 2, 3, 4]
		val Iterable<Integer> second = #[1, 2, 3, 4]

		assertEquals(4, Tuplizer::tuplize(#[first, second]).size)

		for (tuple : Tuplizer::tuplize(#[first, second])) {
			assertEquals(2, tuple.size)
			assertEquals(tuple.get(0), tuple.get(1))
		}

	}

	@Test
	def void testTuplizer2() {
		val Iterable<Integer> first = #[2, 3, 4, 5]
		val Iterable<Integer> second = #[1, 2, 3, 4]

		assertEquals(4, Tuplizer::tuplize(#[first, second]).size)

		for (tuple : Tuplizer::tuplize(#[first, second])) {
			assertEquals(2, tuple.size)
			assertEquals(tuple.get(0) - 1, tuple.get(1))
		}

	}
}
