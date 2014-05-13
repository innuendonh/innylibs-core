package innylibs

import com.google.common.base.Charsets
import com.google.common.collect.AbstractIterator
import com.google.common.io.ByteSource
import com.google.common.io.CharSource
import java.io.BufferedReader

final class LineIterator extends AbstractIterator<String> {

	val BufferedReader br;

	new(BufferedReader br) {
		this.br = br
	}

	override protected computeNext() {
		br.readLine() ?: (endOfData() => [br.close()])
	}

	def static asIterator(BufferedReader br) {
		new LineIterator(br)
	}

	def static Iterable<String> lines(CharSource src) {
		[|src.openBufferedStream.asIterator]
	}

	def static Iterable<String> lines(ByteSource src) {
		src.asCharSource(Charsets::UTF_8).lines
	}

}
