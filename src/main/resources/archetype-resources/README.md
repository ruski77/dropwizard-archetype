#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
${model} Service
===================

This restful web service is build on the Dropwizard framework.

== Add Project to Git ==

        cd loyalty_service

        git init

        git add *

        git status

        git commit -m "Initial Commit"

        Go to github and create repo

        git remote add origin https://github.com/ruski77/loyalty_service.git

        git push origin master

        remove local directory








== Deploy to Open shift ==

rhc create-app <app name> diy-0.1

rhc cartridge add mongodb-2.2 -a <app name>

mv ${artifactId}/* ${artifactId}app

cp ${artifactId}/.openshift/action_hooks/build ${artifactId}app/.openshift/action_hooks/build

cp ${artifactId}/.openshift/action_hooks/deploy ${artifactId}app/.openshift/action_hooks/deploy

cp ${artifactId}/.openshift/action_hooks/start ${artifactId}app/.openshift/action_hooks/start

cp ${artifactId}/.openshift/settings.xml ${artifactId}app/.openshift/settings.xml

mv ${artifactId}/.gitignore ${artifactId}app/

cp ${artifactId}/README.md ${artifactId}app/README.md

cd ${artifactId}app

git add .

git commit -m "initial commit"

git push





== Curl tests ==

curl -i -X POST -H "Content-Type: application/json" -d '{"foo":"bar"}' http://localhost:8080/${artifactId}

curl -i -X PUT -H "Content-Type: application/json" -d '{"foo":"test"}' http://localhost:8080/${artifactId}/{id}

curl -i -X GET -H "Content-Type: application/json"  http://localhost:8080/${artifactId}/{id}

curl -i -X DELETE -H "Content-Type: application/json"  http://localhost:8080/${artifactId}/{id}