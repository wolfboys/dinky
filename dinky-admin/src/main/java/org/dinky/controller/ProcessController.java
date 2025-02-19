/*
 *
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.dinky.controller;

import org.dinky.common.result.ProTableResult;
import org.dinky.common.result.Result;
import org.dinky.process.model.ProcessEntity;
import org.dinky.service.ProcessService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.dev33.satoken.stp.StpUtil;
import lombok.RequiredArgsConstructor;

/**
 * ProcessController
 *
 * @author wenmo
 * @since 2022/10/16 22:53
 */
@RestController
@RequestMapping("/api/process")
@RequiredArgsConstructor
public class ProcessController {

    private final ProcessService processService;

    @GetMapping("/listAllProcess")
    public ProTableResult<ProcessEntity> listAllProcess(@RequestParam boolean active) {
        List<ProcessEntity> processEntities = processService.listAllProcess(active);
        return ProTableResult.<ProcessEntity>builder().success(true).data(processEntities).build();
    }

    @GetMapping("/getConsoleByUserId")
    public Result<String> getConsoleByUserId() {
        return Result.data(processService.getConsoleByUserId(StpUtil.getLoginIdAsInt()));
    }

    @GetMapping("/clearConsole")
    public Result<String> clearConsole() {
        processService.clearConsoleByUserId(StpUtil.getLoginIdAsInt());
        return Result.succeed("Clear succeed.");
    }
}
