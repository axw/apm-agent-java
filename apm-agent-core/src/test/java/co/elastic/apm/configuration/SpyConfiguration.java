/*-
 * #%L
 * Elastic APM Java agent
 * %%
 * Copyright (C) 2018 the original author or authors
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package co.elastic.apm.configuration;

import org.stagemonitor.configuration.ConfigurationOptionProvider;
import org.stagemonitor.configuration.ConfigurationRegistry;
import org.stagemonitor.configuration.source.SimpleSource;

import java.util.ServiceLoader;

import static org.mockito.Mockito.spy;

public class SpyConfiguration {

    public static final String CONFIG_SOURCE_NAME = "test config source";

    /**
     * Creates a configuration registry where all {@link ConfigurationOptionProvider}s are wrapped with
     * {@link org.mockito.Mockito#spy(Object)}
     * <p>
     * That way, the default configuration values are returned but can be overridden by {@link org.mockito.Mockito#when(Object)}
     *
     * @return a syp configuration registry
     */
    public static ConfigurationRegistry createSpyConfig() {
        ConfigurationRegistry.Builder builder = ConfigurationRegistry.builder();
        for (ConfigurationOptionProvider options : ServiceLoader.load(ConfigurationOptionProvider.class)) {
            builder.addOptionProvider(spy(options));
        }
        return builder
            .addConfigSource(new SimpleSource(CONFIG_SOURCE_NAME).add("service_name", "elastic-apm-test"))
            .build();
    }
}
