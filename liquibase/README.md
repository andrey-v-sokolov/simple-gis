# Liquibase

The database is managed with Liquibase.

No changesets should be added to the *master-db-changelog.xml*.  Rather, all changesets should be in the correct release
file (changelog-<version>.xml, e.g. *changelog-1.0.xml*) and the master changelog should 
reference these files in order. Version in file names should correspond with version from pom.xml

Changesets should have ids in the form of YYYYMMDD-# where # is incremented with each changeset you create for a given day.
*Note:* changesets ids only have to be unique by author, so two developers can use *YYYYMMDD-1* for the same date.



Database creation:
```
host$ /usr/lib/postgresql/9.4/bin/createuser -P -s -e simplegisuser
host$ psql
psql$ create database simplegisdb;
psql$ \connect simplegisdb;
psql$ CREATE SCHEMA simplegisdb;
```
