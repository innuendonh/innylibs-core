/**
 * Copyright 2014 Marco Zanoni
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
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
