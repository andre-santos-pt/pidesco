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
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.WhitespaceRule;
import pt.iscte.pidesco.javaeditor.internal.Common;
import pt.iscte.pidesco.javaeditor.internal.TokenColor;
import pt.iscte.pidesco.javaeditor.internal.TokenStyle;
import pt.iscte.pidesco.javaeditor.internal.scanner.rule.IntRule;
import pt.iscte.pidesco.javaeditor.internal.scanner.rule.KeyWordRule;
import pt.iscte.pidesco.javaeditor.internal.scanner.rule.UniqueWordRule;


public class JavaCodeScanner extends RuleBasedScanner {

	private static final IToken TOKEN_COMMENT		= Common.createToken(TokenColor.COMMENT,	TokenStyle.NORMAL);
	private static final IToken TOKEN_JAVADOC		= Common.createToken(TokenColor.COMMENT,	TokenStyle.BOLD);
	private static final IToken TOKEN_STRING		= Common.createToken(TokenColor.STRING,		TokenStyle.NORMAL);
	private static final IToken TOKEN_CHARACTER		= Common.createToken(TokenColor.CHAR,		TokenStyle.NORMAL);
	private static final IToken TOKEN_KEYWORD		= Common.createToken(TokenColor.KEYWORD,	TokenStyle.BOLD);
	private static final IToken TOKEN_NUMBER		= Common.createToken(TokenColor.NUMBER,		TokenStyle.BOLD);
	private static final IToken TOKEN_LITERAL_TRUE	= Common.createToken(TokenColor.TRUE,		TokenStyle.BOLD);
	private static final IToken TOKEN_LITERAL_FALSE	= Common.createToken(TokenColor.FALSE,		TokenStyle.BOLD);
	private static final IToken TOKEN_LITERAL_NULL	= Common.createToken(TokenColor.NULL,		TokenStyle.BOLD);
//	private static final IToken TOKEN_ID			= Common.createToken(TokenColor.ID,			TokenStyle.BOLD);
//	private static final IToken TOKEN_DEFAULT		= Common.createToken(TokenColor.BLACK,		TokenStyle.NORMAL);

	public JavaCodeScanner() {
		super();

		setRules(new IRule[] {
				new KeyWordRule(TOKEN_KEYWORD),
				new IntRule(TOKEN_NUMBER),
				new UniqueWordRule(TOKEN_LITERAL_TRUE, "true"),
				new UniqueWordRule(TOKEN_LITERAL_FALSE, "false"),
				new UniqueWordRule(TOKEN_LITERAL_NULL, "null"),
				new SingleLineRule("\"", "\"", TOKEN_STRING, '\\'),
				new SingleLineRule("'", "'", TOKEN_CHARACTER, '\\'),

				// Comments
				new EndOfLineRule("//", TOKEN_COMMENT),
				new MultiLineRule("/**", "*/", TOKEN_JAVADOC),
				new MultiLineRule("/*", "*/", TOKEN_COMMENT),

				new WhitespaceRule(Character::isWhitespace)
		});
	}


}
