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
package co.elastic.apm.benchmark.reporter;

import co.elastic.apm.configuration.CoreConfiguration;
import co.elastic.apm.report.serialize.DslJsonSerializer;
import co.elastic.apm.report.serialize.PayloadSerializer;
import org.openjdk.jmh.runner.RunnerException;

/**
 * Measures the performance of the actually used reporter implementation
 * including JSON serialization and reporting payloads over HTTP
 */
public class HttpDslJsonReporterContinuousBenchmark extends AbstractHttpReporterBenchmark {

    /**
     * Convenience benchmark run method
     * <p>
     * For more accurate results, execute {@code mvn clean package} and run the benchmark via
     * {@code java -jar apm-agent-benchmarks/target/benchmarks.jar -prof gc}
     */
    public static void main(String[] args) throws RunnerException {
        run(HttpDslJsonReporterContinuousBenchmark.class);
    }

    @Override
    protected PayloadSerializer getPayloadSerializer() {
        return new DslJsonSerializer(tracer.getConfig(CoreConfiguration.class));
    }
}
