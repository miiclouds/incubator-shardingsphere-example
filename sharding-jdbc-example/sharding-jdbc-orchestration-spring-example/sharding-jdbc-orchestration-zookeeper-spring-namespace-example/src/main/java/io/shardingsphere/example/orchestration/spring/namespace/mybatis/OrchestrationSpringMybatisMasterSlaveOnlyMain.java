/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.example.orchestration.spring.namespace.mybatis;

import io.shardingsphere.example.orchestration.spring.namespace.mybatis.service.DemoService;
import io.shardingsphere.jdbc.orchestration.api.OrchestrationMasterSlaveDataSourceFactory;
import io.shardingsphere.jdbc.orchestration.internal.OrchestrationMasterSlaveDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * Please make sure master-slave data sync on MySQL is running correctly. Otherwise this example will query empty data from slave.
 */
public class OrchestrationSpringMybatisMasterSlaveOnlyMain {
    
    public static void main(final String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/mybatisMasterSlaveOnlyByLocalConfigContext.xml");
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/mybatisMasterSlaveOnlyByCloudConfigContext.xml");
        DemoService demoService = applicationContext.getBean(DemoService.class);
        demoService.demo();
        OrchestrationMasterSlaveDataSourceFactory.closeQuietly(applicationContext.getBean(OrchestrationMasterSlaveDataSource.class));
    }
}