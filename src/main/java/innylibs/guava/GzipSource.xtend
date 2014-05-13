package innylibs.guava

import com.google.common.io.ByteSource
import java.io.IOException
import java.util.zip.GZIPInputStream

/**
 * ByteSource decorator for unzipping streams.
 */
final class GzipSource extends ByteSource {

	/**
	 * Factory decompressing the passed source
	 */
	static def gunzip(ByteSource z) {
		new GzipSource(z)
	}

	val ByteSource zipped;

	private new(ByteSource zipped) {
		this.zipped = zipped
	}

	override openStream() throws IOException {
		new GZIPInputStream(zipped.openStream())
	}

}
