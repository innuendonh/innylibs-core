package innylibs.guava

import com.google.common.io.ByteSink
import java.io.IOException
import java.util.zip.GZIPOutputStream

/**
 * ByteSink decorator compressing output. 
 */
final class GzipSink extends ByteSink {

	/**
	 * Static factory for gzipping the passed ByteSink
	 */
	static def gzip(ByteSink uz) {
		new GzipSink(uz)
	}

	val ByteSink unzipped

	private new(ByteSink unzipped) {
		this.unzipped = unzipped
	}

	override openStream() throws IOException {
		new GZIPOutputStream(unzipped.openStream())
	}

}
