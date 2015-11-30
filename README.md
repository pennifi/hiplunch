# README #

A simple Java app for parsing RSS or JSON sources for lunch menus and posting them to a Hipchat channel.

Mainly useful for Mattilanniemi area at Jyväskylä, Finland, as the feed parsing is feed specific (no real deterministic approach) At the moment parses Sonaatti lunch cafeterias and Sodexo Mattilanniemi feeds.

## Providers ##

- Sonaatti
-- All locations (just add provider)
-- Piato
-- Wilhelmiina
- Sodexo
-- All locations (just add provider)
-- Mattilanniemi
-- Dynamo
- Fazer
-- Fiilu


## Usage ##

- Clone repo
- Check Constants for api key and room id
- Check constants for hilights
- Run 'mvn clean install'
- Run 'java -jar target/hiplunch-1.0-jar-with-dependencies.jar'

## TODO: ##

- Move hipchat channel key and id to external config
- Add providers for Lutakko area restaurants
- Parametrize which menus should be ran (empty= all?) 
- Course tags and formatting (VH, L, G...)?
- Clean up code

