node {
	currentBuild.displayName = "EverPlugins #${env.BUILD_NUMBER} - SpongeAPI 7.0.0"
	currentBuild.description = "For SpongeAPI 7.0.0"
	
	stage('Stage Checkout') {
		checkout scm
		sh "git submodule update --init"
	}
	
	stage('Stage Build') {
		echo "My branch is : ${env.BRANCH_NAME}"
		sh "chmod +x gradlew"
		sh "./gradlew clean build shadowJar zip -PBUILD_NUMBER=${env.BUILD_NUMBER}"
	}

	stage('Stage Upload') {
	    archive 'build/plugins/*.jar'
		archive 'build/plugins/*.zip'
	}
}
