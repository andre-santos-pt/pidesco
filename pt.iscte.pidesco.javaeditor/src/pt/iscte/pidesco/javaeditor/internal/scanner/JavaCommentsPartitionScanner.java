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
package pt.iscte.pidesco.javaeditor.internal.scanner;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import pt.iscte.pidesco.javaeditor.internal.Common;
import pt.iscte.pidesco.javaeditor.internal.TokenColor;
import pt.iscte.pidesco.javaeditor.internal.TokenStyle;
import pt.iscte.pidesco.javaeditor.internal.scanner.rule.EmptyCommentRule;

public class JavaCommentsPartitionScanner extends RuleBasedPartitionScanner {

//	private static final IToken TOKEN_STRING              = Common.createToken(TokenColor.STRING,  TokenStyle.NORMAL);
//	private static final IToken TOKEN_CHARACTER           = Common.createToken(TokenColor.CHAR,    TokenStyle.NORMAL);
	private static final IToken TOKEN_JAVA_DOC            = Common.createToken(TokenColor.COMMENT, TokenStyle.BOLD);
	private static final IToken TOKEN_MULTI_LINE_COMMENT  = Common.createToken(TokenColor.COMMENT, TokenStyle.NORMAL);
	private static final IToken TOKEN_SINGLE_LINE_COMMENT = Common.createToken(TokenColor.COMMENT, TokenStyle.NORMAL);

	/**
	 * Creates the partitioner and sets up the appropriate rules.
	 */
	public JavaCommentsPartitionScanner() {
		super();

		setPredicateRules(new IPredicateRule[]{
				// Single line comments
				new EndOfLineRule("//", TOKEN_SINGLE_LINE_COMMENT), //$NON-NLS-1$

				// Multi-line comments and javadoc
				new MultiLineRule("/**", "*/", TOKEN_JAVA_DOC), //$NON-NLS-1$ //$NON-NLS-2$
				new MultiLineRule("/*", "*/", TOKEN_MULTI_LINE_COMMENT), //$NON-NLS-1$ //$NON-NLS-2$
				new EmptyCommentRule(TOKEN_MULTI_LINE_COMMENT)

				// Strings
//				new SingleLineRule("\"", "\"", TOKEN_STRING, '\\'), //$NON-NLS-2$ //$NON-NLS-1$

				// Character constants
//				new SingleLineRule("'", "'", TOKEN_CHARACTER, '\\') //$NON-NLS-2$ //$NON-NLS-1$
		});
	}
}
