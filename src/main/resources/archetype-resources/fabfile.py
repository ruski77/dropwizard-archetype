#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
from fabric.api import local

def start():
    print "Starting ${model} Dropwizrd Service..."
    local("java -jar target/${artifactId}-0.0.1-SNAPSHOT.jar server target/classes/${artifactId}_service.yml")

def build():
    print "Building ${model} Dropwizrd Service..."
    local("mvn clean package")

def start_local():
    build()
    start()
