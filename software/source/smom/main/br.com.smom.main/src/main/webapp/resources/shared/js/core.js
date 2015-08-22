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

function getModules(callbackSuccess) {

    $.ajax({
        dataType: "json", // Tipo de dados esperado de retorno do servidor.
        type: "GET",
        url: '/modules/main/modules.json',
        success: function (data, textStatus, jqXHR) {
            console.log('data: ' + JSON.stringify(data));
            console.log('textStatus: ' + textStatus);
            console.log('jqXHR: ' + jqXHR);
            callbackSuccess(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('jqXHR: ' + jqXHR);
            console.log('textStatus: ' + textStatus);
            console.log('errorThrown: ' + errorThrown);
        }
    });

}

function mountSideBar(modules) {

    var elementModule, elementGroup;

    if (modules.length > 0) {
        modules.forEach(function (module) {
            if (module.type === "module") {
                elementModule = generateElementModule(module);
                $('#side-menu').append(elementModule);
            } else if (module.type === "group") {
                elementGroup = generateElementGroup(module);
                $('#side-menu').append(elementGroup);
            }
        });
    }

    loadScripts();
}

function generateElementModule(module) {

    var liTag, aTag, iTag;

    liTag = $('<li></li>');
    aTag = $('<a href="' + module.url + '"></a>');
    iTag = $('<i class="' + module.icon + '"></i>');
    liTag.append(aTag);
    aTag.append(iTag);
    aTag.append(' ' + module.name);
    liTag.click(function (){
        alert("Redirect to module " + module.id);
    });
    return liTag;
}

function generateElementGroup(group) {

    var liTag, aTag, iTag, spanTag, ulTag, elementModule;

    liTag = $('<li></li>');
    aTag = $('<a href="' + group.url + '"></a>');
    iTag = $('<i class="' + group.icon + '"></i>');
    spanTag = $('<span class="fa arrow"></span>');
    ulTag = $('<ul class="nav nav-second-level"></ul>');

    liTag.append(aTag);
    aTag.append(iTag);
    aTag.append(' ' + group.name);
    aTag.append(spanTag);

    group.children.forEach(function (module) {
        elementModule = generateElementModule(module);
        ulTag.append(elementModule);
    });

    liTag.append(ulTag);
    return liTag;
}

function loadScripts() {
    appendScript('/modules/main/resources/plugins/startbootstrap/sb_admin_2/bower_components/metisMenu/dist/metisMenu.min.js');
    appendScript('/modules/main/resources/plugins/startbootstrap/sb_admin_2/bower_components/raphael/raphael-min.js');
    appendScript('/modules/main/resources/plugins/startbootstrap/sb_admin_2/bower_components/morrisjs/morris.min.js');
    appendScript('/modules/main/resources/plugins/startbootstrap/sb_admin_2/dist/js/sb-admin-2.js');
}

function appendScript(url) {
    var script = document.createElement('script');
    script.type = 'text/javascript';
    script.src = url;
    document.body.appendChild(script);
    return script;
}

getModules(function (data) {
    if (data !== null) {
        mountSideBar(data);
    }
});