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

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.WordRule;
import pt.iscte.pidesco.javaeditor.internal.scanner.detector.EmptyCommentDetector;

/**
 * Word rule for empty comments.
 */
public class EmptyCommentRule extends WordRule implements IPredicateRule {

	private IToken fSuccessToken;

	/**
	 * Constructor for EmptyCommentRule.
	 * @param successToken
	 */
	public EmptyCommentRule(IToken successToken) {
		super(new EmptyCommentDetector());
		fSuccessToken= successToken;
		addWord("/**/", fSuccessToken); //$NON-NLS-1$
	}

	/*
	 * @see IPredicateRule#evaluate(ICharacterScanner, boolean)
	 */
	public IToken evaluate(ICharacterScanner scanner, boolean resume) {
		return evaluate(scanner);
	}

	/*
	 * @see IPredicateRule#getSuccessToken()
	 */
	public IToken getSuccessToken() {
		return fSuccessToken;
	}
}
