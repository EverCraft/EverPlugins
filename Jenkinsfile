node {
	currentBuild.displayName = "bleeding"
    currentBuild.description = "For SpongeAPI 6.0"

	stage 'Stage Checkout'
	
	checkout scm
	sh "git submodule update --init"
	
	stage 'Stage Build'
	echo "My branch is : ${env.BRANCH_NAME}"
	
	sh "./gradlew clean build zip -PBUILD_NUMBER=${env.BUILD_NUMBER}"

	
	stage 'Stage Upload'
    archive 'build/plugins/*.jar'
	archive 'build/plugins/*.zip'

}