/*
 * Copyright 2015 Smom - Software Module Management.
 *
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
 */
package br.com.smom.main.filter;

import javax.inject.Singleton;
import javax.servlet.http.HttpSession;

@Singleton
public class SessionSingleton {

    private HttpSession session1;
    private HttpSession session2;

    public HttpSession getSession1() {
        try {
            String id = session1.getId();
            return session1;
        } catch (Exception e) {
            return null;
        }
    }

    public void setSession1(HttpSession session1) {
        this.session1 = session1;
    }

    public HttpSession getSession2() {
        return session2;
    }

    public void setSession2(HttpSession session2) {
        this.session2 = session2;
    }

}
