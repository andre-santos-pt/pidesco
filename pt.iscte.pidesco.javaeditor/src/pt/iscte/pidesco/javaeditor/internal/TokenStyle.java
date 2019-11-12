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
package pt.iscte.pidesco.javaeditor.internal;

import org.eclipse.swt.SWT;

public enum TokenStyle {
	NORMAL(SWT.NORMAL),
	BOLD(SWT.BOLD),
	ITALIC(SWT.ITALIC),
	STRIKETHROUGH(536870912),
	UNDERLINE(1073741824);

	public final int style;

	TokenStyle(int style) {
		this.style = style;
	}
}
