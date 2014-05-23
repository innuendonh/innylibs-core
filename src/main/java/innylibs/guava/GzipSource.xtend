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

import com.google.common.io.ByteSource
import java.io.IOException
import java.util.zip.GZIPInputStream

import static com.google.common.base.Preconditions.*

/**
 * ByteSource decorator for unzipping streams.
 */
final class GzipSource extends ByteSource {

	/**
	 * Factory decompressing the passed source
	 */
	static def gunzip(ByteSource z) {
		new GzipSource(checkNotNull(z))
	}

	val ByteSource zipped;

	private new(ByteSource zipped) {
		this.zipped = zipped
	}

	override openStream() throws IOException {
		new GZIPInputStream(zipped.openStream())
	}

}
