# README #

A simple Java app for parsing RSS or JSON sources for lunch menus and posting them to a Hipchat channel.

Mainly useful for Mattilanniemi area at Jyväskylä, Finland, as the feed parsing is feed specific (no real deterministic approach) At the moment parses Sonaatti lunch cafeterias and Sodexo Mattilanniemi feeds.

## TODO: ##

- move hipchat channel key and id to external config
- parametrize which menus should be ran (empty= all?) 
- course tags and formatting (VH, L, G...)?
- clean up code

## Usage ##

- clone repo
- check Constants for api key and room id
- check constants for hilights
- run 'mvn clean install'
- run 'java -jar target/hiplunch-1.0-jar-with-dependencies.jar'