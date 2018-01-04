/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.workflow.web.internal.portlet.action;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.workflow.web.internal.constants.WorkflowPortletKeys;

import java.util.ResourceBundle;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jeyvison Nascimento
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + WorkflowPortletKeys.CONTROL_PANEL_WORKFLOW,
		"mvc.command.name=duplicateWorkflowDefinition"
	},
	service = MVCActionCommand.class
)
public class DuplicateWorkflowDefinitionMVCActionCommand
	extends UpdateWorkflowDefinitionMVCActionCommand {

	@Override
	protected void addSuccessMessage(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(
			portal.getLocale(actionRequest));

		String successMessage = getSuccessMessage(
			resourceBundle, actionRequest);

		SessionMessages.add(actionRequest, "requestProcessed", successMessage);
	}

	protected String getSuccessMessage(
		ResourceBundle resourceBundle, ActionRequest actionRequest) {

		String duplicatedDefinitionName = ParamUtil.getString(
			actionRequest, "duplicatedDefinitionTitle");

		return LanguageUtil.format(
			resourceBundle, "duplicated-from-x",
			StringUtil.quote(duplicatedDefinitionName));
	}

}