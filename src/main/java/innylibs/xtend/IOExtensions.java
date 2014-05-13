package innylibs.xtend;

import java.io.File;

import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Extension methods for dealing with standard java classes involved in I/O.
 * 
 * @author Marco Zanoni
 * 
 */
public final class IOExtensions {

	/**
	 * Cannot instantiate this class
	 */
	private IOExtensions() {}

	/**
	 * Allows using the slash "/" operator for separing file name chunks. The
	 * path separator is chosen automatically by the platform. It serves simply
	 * as a macro over the standard File(File, String) constructor.
	 * 
	 * @param prefix
	 *            The parent directory
	 * @param suffix
	 *            The child file or directory to represent, relative to the
	 *            parent.
	 * @return A new File representing the child path.
	 */
	@Pure
	@Inline(value = "new $3($1, $2)", imported = File.class,
		statementExpression = false)
	public static File operator_divide(File prefix, String suffix) {
		return new File(prefix, suffix);
	}

}
