## This is a Simple project run on Selenium 4
****
## Tech stacks
* Maven project
* Selenium 4.8.2
* WebDriverManager 4.4.0
* Junit 4.13.2
* Cucumber 7.8.1
* Allure cucumber 7 jvm 2.20.1
* CI/CD: Jenkins
* Cloud farm: Browserstack


****
## How to use

* #### By default, the tests will run over prod_env.properties file, which will assign `base_url` and `browser` to browser
```shell
$ mvn clean test
```
* #### To filter specific tags 
```shell
$ mvn clean test -Dcucumber.filter.tags="@tag1 and @tag2 or @tag3"
```
* #### To run on different environment
```shell
$ mvn clean test -Denv=<properties_name>.properties
```
* #### To override browser in properties file
```shell
$ mvn clean test -Dbrowser=chrome/firefox/egde/browserstack
```
* #### To generate allure report after test finished
```shell
$ allure generate --clean
```