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

public class KeyWordRule extends WordRule {

	private final static String[] JAVA_KEYWORDS = {"abstract", "continue", "for", "new", "switch", "assert", "default",
			"goto", "package", "synchronized", "boolean", "do", "if", "private", "this", "break", "double",
			"implements", "protected", "throw", "byte", "else", "import", "public", "throws", "case", "enum",
			"instanceof", "return", "transient", "catch", "extends", "int", "short", "try", "char", "final",
			"interface", "static", "void", "class", "finally", "long", "strictfp", "volatile", "const", "float",
			"native", "super", "while"};

	public KeyWordRule(IToken token) {
		super(new IWordDetector() {
			@Override
			public boolean isWordStart(char c) {
				return Character.isJavaIdentifierStart(c);
			}

			@Override
			public boolean isWordPart(char c) {
				return Character.isJavaIdentifierPart(c);
			}
		}, Token.UNDEFINED);

		for(String word : JAVA_KEYWORDS)
			addWord(word, token);
	}
}
