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

var modules = [{
        "id" : "100",
        "name": "Fornecedores",
        "url": "",
        "bundles": [{
                "id" : "101",
                "name": "Cadastro",
                "url": "/modules/fornecedores/cadastro"
            }, {
                "id" : "102",
                "name": "Relatórios",
                "url": "/modules/fornecedores/relatorios"
            }]
    }, {
        "id" : "200",
        "name": "Usuários",
        "url": "",
        "bundles": [{
                "id" : "201",
                "name": "Cadastro",
                "url": "/modules/fornecedores/cadastro"
            }, {
                "id" : "202",
                "name": "Relatórios",
                "url": "/modules/fornecedores/relatorios"
            }]
    }
];

modules.forEach(function (module) {

    var elementModule = $('<li><a href=' + module.url + '><i class="fa fa-tachometer"></i> ' + module.name + '<span class="fa arrow"></span></a></li>');
    $('#side-menu').append(elementModule);

    var listBundles = $('<ul class="nav nav-second-level">');
    elementModule.append(listBundles);

    module.bundles.forEach(function (bundle) {
        var elementBundle = $('<li><a href=' + bundle.url + '><i class="fa fa-pencil-square-o"></i> ' + bundle.name + '</a></li>');
        elementBundle.click(function () {
            alert("Click bundle: " + bundle.name);
        });
        listBundles.append(elementBundle);
    });

});



