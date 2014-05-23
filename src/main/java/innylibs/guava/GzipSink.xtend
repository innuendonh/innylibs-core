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
package innylibs.guava

import com.google.common.io.ByteSink
import java.io.IOException
import java.util.zip.GZIPOutputStream
import static com.google.common.base.Preconditions.*

/**
 * ByteSink decorator compressing output. 
 */
final class GzipSink extends ByteSink {

	/**
	 * Static factory for gzipping the passed ByteSink
	 */
	static def gzip(ByteSink uz) {
		new GzipSink(checkNotNull(uz))
	}

	val ByteSink unzipped

	private new(ByteSink unzipped) {
		this.unzipped = unzipped
	}

	override openStream() throws IOException {
		new GZIPOutputStream(unzipped.openStream())
	}

}
