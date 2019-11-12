/*******************************************************************************
 * Copyright (c) 2014 Andre L. Santos.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Andre L Santos - developer
 ******************************************************************************/
package pt.iscte.pidesco.javaeditor.internal.scanner.rule;

import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import pt.iscte.pidesco.javaeditor.internal.scanner.JavaCodeScanner;

public class UniqueWordRule extends WordRule {

	public UniqueWordRule(final IToken token, final String ... words) {
		super(new IWordDetector() {
			@Override
			public boolean isWordStart(char c) {
				for(String w : words)
					if(w.charAt(0) == c)
						return true;
				return false;
			}

			@Override
			public boolean isWordPart(char c) {
				return c >= 'a' && c <= 'w';
			}
		}, Token.UNDEFINED);

		for(String w : words)
			addWord(w, token);
	}
}
