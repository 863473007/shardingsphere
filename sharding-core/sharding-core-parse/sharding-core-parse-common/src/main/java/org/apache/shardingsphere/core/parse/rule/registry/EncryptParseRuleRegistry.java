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

package org.apache.shardingsphere.core.parse.rule.registry;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.shardingsphere.core.constant.DatabaseType;
import org.apache.shardingsphere.core.parse.rule.jaxb.loader.RuleDefinitionFileConstant;

import java.util.Collection;

/**
 * Parse rule registry for encrypt.
 *
 * @author duhongjun
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EncryptParseRuleRegistry extends ParseRuleRegistry {
    
    private static volatile ParseRuleRegistry instance;
    
    /**
     * Get singleton instance of parsing rule registry.
     *
     * @return instance of parsing rule registry
     */
    public static ParseRuleRegistry getInstance() {
        if (null == instance) {
            synchronized (EncryptParseRuleRegistry.class) {
                if (null == instance) {
                    instance = new EncryptParseRuleRegistry();
                    instance.init();
                }
            }
        }
        return instance;
    }
    
    @Override
    protected void fillRuleFilePaths(final DatabaseType databaseType, 
                                     final Collection<String> extractorFilePaths, final Collection<String> fillerFilePaths, final Collection<String> sqlStatementRuleFilePaths) {
        extractorFilePaths.add(RuleDefinitionFileConstant.getExtractorRuleDefinitionFileName(RuleDefinitionFileConstant.ENCRYPT_ROOT_PATH, databaseType));
        fillerFilePaths.add(RuleDefinitionFileConstant.getFillerRuleDefinitionFileName(RuleDefinitionFileConstant.ENCRYPT_ROOT_PATH, databaseType));
        sqlStatementRuleFilePaths.add(RuleDefinitionFileConstant.getSQLStatementRuleDefinitionFileName(RuleDefinitionFileConstant.ENCRYPT_ROOT_PATH, databaseType));
    }
    
    @Override
    protected boolean needParse(final DatabaseType databaseType) {
        return true;
    }
}

