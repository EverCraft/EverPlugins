stage 'build'
node {
	stage 'Stage Checkout'
	
	checkout scm
	sh 'git submodule update --init'
	
	stage 'Stage Build'
	echo "My branch is: ${env.BRANCH_NAME}"
	
	def flavor = flavor(env.BRANCH_NAME)
	echo "Building flavor ${flavor}"
	
	sh "./gradlew clean -PBUILD_NUMBER=${env.BUILD_NUMBER}"

	sh "./gradlew build -PBUILD_NUMBER=${env.BUILD_NUMBER}"

	stage 'Stage Upload'
    archive 'build/plugins/*.jar'

}