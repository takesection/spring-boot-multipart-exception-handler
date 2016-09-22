/*
 * Copyright 2016 Pigumer Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.pigumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class Index {

    private static final Logger LOGGER = LoggerFactory.getLogger(Index.class);

    @ExceptionHandler(MultipartException.class)
    @ResponseBody
    public String handlerException(Throwable t) {
        LOGGER.error("Index Handler: " + t.getClass().getName(), t);
        return t.getMessage();
    }

    @RequestMapping
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping("upload")
    public ModelAndView upload(@ModelAttribute UploadModel uploadModel) throws IOException {
        ModelAndView mv = new ModelAndView("index");
        String filename = uploadModel.getFile().getOriginalFilename();
        mv.addObject("filename", filename);
        return mv;
    }
}
