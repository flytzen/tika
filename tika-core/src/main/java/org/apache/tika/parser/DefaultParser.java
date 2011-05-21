/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.tika.parser;

import javax.imageio.spi.ServiceRegistry;

import org.apache.tika.config.ServiceLoader;
import org.apache.tika.mime.MediaTypeRegistry;

/**
 * A composite parser based on all the {@link Parser} implementations
 * available through the {@link ServiceRegistry service provider mechanism}.
 *
 * @since Apache Tika 0.8
 */
public class DefaultParser extends CompositeParser {

    /** Serial version UID */
    private static final long serialVersionUID = 3612324825403757520L;

    private DefaultParser(MediaTypeRegistry registry, ServiceLoader loader) {
        super(registry, loader.loadServiceProviders(Parser.class));
    }

    public DefaultParser(MediaTypeRegistry registry, ClassLoader loader) {
        this(registry, new ServiceLoader(loader));
    }

    public DefaultParser(ClassLoader loader) {
        this(MediaTypeRegistry.getDefaultRegistry(), new ServiceLoader(loader));
    }

    public DefaultParser() {
        this(MediaTypeRegistry.getDefaultRegistry(), new ServiceLoader());
    }

}
