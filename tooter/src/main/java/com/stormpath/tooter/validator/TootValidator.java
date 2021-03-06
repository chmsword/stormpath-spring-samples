/*
 * Copyright 2012 Stormpath, Inc. and contributors.
 *
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
 */
package com.stormpath.tooter.validator;

import com.stormpath.tooter.model.Toot;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Elder Crisostomo
 */
@Component
public class TootValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Toot.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tootMessage", "tooter.required", "Field toot is required");

        Toot toot = (Toot) o;

        if (toot.getTootMessage() != null && toot.getTootMessage().length() > 160) {
            errors.rejectValue("tootMessage", "tooter.too.many.chars");
        }
    }
}
