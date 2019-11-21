fixes EASY-

#### When applied it will
* 
* 
* 

#### Where should the reviewer @DANS-KNAW/easy start?


#### How should this be manually tested?
* `mvn clean install -f ~/git/service/easy/easy-ingest-flow`
  if http://easy.dans.knaw.nl/schemas/ is not available some tests will be skipped
* create a deposit and ingest it, for example from the project root:
  * `./debug-init-env.sh`
  * `./debug-add-deposit.sh src/test/resources/deposits/`XXX
  * replace the user in `data/inbox/`XXX`/deposit.properties` with ...
  * `run.sh data/inbox/`XXX (or `run-debug.sh`)
* 
* 
* 

#### related pull requests on github
repo                       | PR                | note
-------------------------- | ----------------- | ---
easy-                      | [PR#](PRlink)     | 
