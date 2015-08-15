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

// Load files CSS
var link;

// Bootstrap:
link = document.createElement('link');
link.rel = 'stylesheet';
link.href = '/modules/main/resources/plugins/bootstrap/css/bootstrap.min.css';
document.head.appendChild(link);

link = document.createElement('link');
link.rel = 'stylesheet';
link.href = '/modules/main/resources/plugins/bootstrap/css/bootstrap-theme.min.css';
document.head.appendChild(link);

// Start Bootstrap
link = document.createElement('link');
link.rel = 'stylesheet';
link.href = '/modules/main/resources/plugins/startbootstrap/bower_components/metisMenu/dist/metisMenu.min.css';
document.head.appendChild(link);

link = document.createElement('link');
link.rel = 'stylesheet';
link.href = '/modules/main/resources/plugins/startbootstrap/dist/css/timeline.css';
document.head.appendChild(link);

link = document.createElement('link');
link.rel = 'stylesheet';
link.href = '/modules/main/resources/plugins/startbootstrap/dist/css/sb-admin-2.css';
document.head.appendChild(link);

link = document.createElement('link');
link.rel = 'stylesheet';
link.href = '/modules/main/resources/plugins/startbootstrap/bower_components/morrisjs/morris.css';
document.head.appendChild(link);

link = document.createElement('link');
link.rel = 'stylesheet';
link.href = '/modules/main/resources/plugins/startbootstrap/bower_components/font-awesome/css/font-awesome.min.css';
document.head.appendChild(link);


// Load files Javascript
var script;

// jQuery:
script = document.createElement('script');
script.src = '/modules/main/resources/plugins/jquery/jquery.min.js';
document.body.appendChild(script);
script.onload = function () {

    // Bootstrap:
    script = document.createElement('script');
    script.src = '/modules/main/resources/plugins/bootstrap/js/bootstrap.min.js';
    document.body.appendChild(script);

    // Start Bootstrap:
    script = document.createElement('script');
    script.src = '/modules/main/resources/plugins/startbootstrap/bower_components/metisMenu/dist/metisMenu.min.js';
    document.body.appendChild(script);

    script = document.createElement('script');
    script.src = '/modules/main/resources/plugins/startbootstrap/bower_components/raphael/raphael-min.js';
    document.body.appendChild(script);

    script = document.createElement('script');
    script.src = '/modules/main/resources/plugins/startbootstrap/bower_components/morrisjs/morris.min.js';
    document.body.appendChild(script);

//    script = document.createElement('script');
//    script.src = '/modules/main/resources/plugins/startbootstrap/js/morris-data.js';
//    document.body.appendChild(script);

    script = document.createElement('script');
    script.src = '/modules/main/resources/plugins/startbootstrap/dist/js/sb-admin-2.js';
    document.body.appendChild(script);

    // Angular:
    script = document.createElement('script');
    script.src = '/modules/main/resources/plugins/angularjs/angular.min.js';
    document.body.appendChild(script);

    script.onload = function () {

        script = document.createElement('script');
        script.src = '/modules/main/resources/plugins/angularjs/angular-resource.min.js';
        document.body.appendChild(script);

        script = document.createElement('script');
        script.src = '/modules/main/resources/plugins/angularjs/angular-route.min.js';
        document.body.appendChild(script);
        
    };

};