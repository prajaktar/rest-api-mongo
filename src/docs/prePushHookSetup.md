##How to setup pre-push git hook

1. Create a pre-push file in your project, write the command that you have to execute while pre-push
2. In you build.gradle add following code:
    - `task gitExecutableHooks() {
     	Runtime.getRuntime().exec("chmod -R +x .git/hooks/")
     }` - This will give executable permissions to file in hooks directory
    
    - `task installGitHooks(type: Copy) {
     	from new File(rootProject.rootDir, 'pre-push')
     	into { new File(rootProject.rootDir, '.git/hooks') }
     	fileMode 0777
     }` - This will copy the pre-push script to the pre-push file in hooks directory
    - `afterEvaluate {
     	gitExecutableHooks.dependsOn installGitHooks
     	check.dependsOn gitExecutableHooks
     }` - This will execute the above tasks