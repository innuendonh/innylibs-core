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
