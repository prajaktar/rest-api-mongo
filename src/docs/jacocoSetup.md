#How to add jacocoTask
1. Add plugins `{
           id 'jacoco'
       }` in build.gradle
2. Run command `gradle jacocoTestReport`

    - This command will generate HTML report in `$buildDir/reports/jacoco/test` 
3. Setup dependency between test and jacoco task

    - Add/update test block to have `finalizedBy jacocoTestReport`
    This indicates that jacocoReport will always be generated when tests run
    - Add `jacocoTestReport {
              dependsOn test // tests are required to run before generating the report
          }` section which indicates that tests must run first before generating jacocoReport
4. You can specify your own directory for jacoco report generation using `jacoco {
                                                                              toolVersion = "0.8.5"
                                                                              reportsDir = file("$buildDir/customJacocoReportDir")
                                                                          }`
5. To set test coverage rule use following code:
`def coverageExcludes = [
 		'**/common*',
 		'**/*Application*'
 ]
jacocoTestCoverageVerification {
 	dependsOn jacocoTestReport
 	afterEvaluate {
 		classDirectories.setFrom(files(classDirectories.files.collect {
 			fileTree(dir: it, exclude:  coverageExcludes)
 		}))
 	}
 	violationRules { rule { limit { minimum = 0.90 } } }
 }
`                                                                                        
       
    