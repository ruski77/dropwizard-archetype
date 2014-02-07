#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
#!/bin/sh

echo "Starting ${model} Service..."

java -jar target/${artifactId}-0.0.1-SNAPSHOT.jar server target/classes/${artifactId}_service.yml
