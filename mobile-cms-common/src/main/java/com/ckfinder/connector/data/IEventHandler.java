/*
 * CKFinder
 * ========
 * http://cksource.com/ckfinder
 * Copyright (C) 2007-2014, CKSource - Frederico Knabben. All rights reserved.
 *
 * The software, this file and its contents are subject to the CKFinder
 * License. Please read the license.txt file before using, installing, copying,
 * modifying or distribute this file or part of its contents. The contents of
 * this file is part of the Source Code of CKFinder.
 */
package com.ckfinder.connector.data;

import com.ckfinder.connector.configuration.IConfiguration;
import com.ckfinder.connector.errors.ConnectorException;

/**
 * Plugin event interface.
 */
public interface IEventHandler {

	/**
	 * execute event handler.
	 *
	 * @param args params for event handler.
	 * @param configuration connector configuration
	 * @return false if break executing command.
	 * @throws com.ckfinder.connector.errors.ConnectorException when error occurs.
	 */
	public boolean runEventHandler(EventArgs args, IConfiguration configuration)
			throws ConnectorException;
}
