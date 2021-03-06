/*
 * Copyright 2011-2017 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.codegen.emitters.tasks;

import com.amazonaws.codegen.emitters.FreemarkerGeneratorTask;
import com.amazonaws.codegen.emitters.GeneratorTask;
import com.amazonaws.codegen.emitters.GeneratorTaskParams;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SyncClientGeneratorTasks extends BaseGeneratorTasks {

    private final String baseDirectory;

    public SyncClientGeneratorTasks(GeneratorTaskParams dependencies) {
        super(dependencies);
        this.baseDirectory = dependencies.getPathProvider().getBasePackageDirectory();
    }

    @Override
    protected List<GeneratorTask> createTasks() throws Exception {
        info("Emitting Sync client classes");
        return Arrays.asList(createClientClassTask(),
                             createClientAbstractClassTask(),
                             createClientBuilderTask(),
                             createClientInterfaceTask());
    }

    private GeneratorTask createClientClassTask() throws IOException {
        return new FreemarkerGeneratorTask(
                baseDirectory,
                model.getMetadata().getSyncClient(),
                freemarker.getSyncClientTemplate(),
                model);
    }

    private GeneratorTask createClientAbstractClassTask() throws IOException {
        return new FreemarkerGeneratorTask(
                baseDirectory,
                model.getMetadata().getSyncAbstractClass(),
                freemarker.getSyncAbstractClassTemplate(),
                model);
    }

    private GeneratorTask createClientBuilderTask() throws IOException {
        return new FreemarkerGeneratorTask(
                baseDirectory,
                model.getMetadata().getSyncClientBuilderClassName(),
                freemarker.getSyncClientBuilderTemplate(),
                model);
    }

    private GeneratorTask createClientInterfaceTask() throws IOException {
        return new FreemarkerGeneratorTask(
                baseDirectory,
                model.getMetadata().getSyncInterface(),
                freemarker.getSyncInterfaceTemplate(),
                model);
    }
}
