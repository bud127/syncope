/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.syncope.client.console.wizards.resources;

import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;
import org.apache.syncope.client.console.panels.TogglePanel;
import org.apache.syncope.client.console.wicket.markup.html.form.AjaxTextFieldPanel;
import org.apache.syncope.common.lib.to.MappingItemTO;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class JEXLTransformersTogglePanel extends TogglePanel<Serializable> {

    private static final long serialVersionUID = -1284019117452782479L;

    private final AjaxTextFieldPanel propagationJEXLTransformer;

    private final AjaxTextFieldPanel pullJEXLTransformer;

    public JEXLTransformersTogglePanel(final WebMarkupContainer container) {
        super("outer", "jexlTransformersTogglePanel");

        Form<?> form = new Form<>("form");
        addInnerObject(form);

        propagationJEXLTransformer = new AjaxTextFieldPanel(
                "propagationJEXLTransformer",
                "Propagation",
                Model.of(""));
        form.add(propagationJEXLTransformer.enableJexlHelp());

        pullJEXLTransformer = new AjaxTextFieldPanel(
                "pullJEXLTransformer",
                "Pull",
                Model.of(""));
        form.add(pullJEXLTransformer.enableJexlHelp());

        form.add(new AjaxSubmitLink("submit", form) {

            private static final long serialVersionUID = 4617041491286858973L;

            @Override
            public void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                toggle(target, false);
                target.add(container);
            }
        });
    }

    public JEXLTransformersTogglePanel setMappingItem(final AjaxRequestTarget target, final MappingItemTO mapItem) {
        this.propagationJEXLTransformer.setNewModel(new PropertyModel<String>(mapItem, "propagationJEXLTransformer"));
        this.pullJEXLTransformer.setNewModel(new PropertyModel<String>(mapItem, "pullJEXLTransformer"));
        setHeader(target, StringUtils.EMPTY);
        return this;
    }
}