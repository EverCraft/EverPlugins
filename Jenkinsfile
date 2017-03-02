echo "################################################"
echo "################################################"
stage 'build'
node {
	stage 'Stage Checkout'
	
	checkout scm
	sh 'git submodule update --init'
	
	stage 'Stage Build'
	echo "My branch is: ${env.BRANCH_NAME}"
	def flavor = flavor(env.BRANCH_NAME)
	echo "Building flavor ${flavor}"
	
	sh "./gradlew clean assemble${flavor}Debug -PBUILD_NUMBER=${env.BUILD_NUMBER}"

	stage 'Stage Upload To Fabric'
	sh "./gradlew build -PBUILD_NUMBER=${env.BUILD_NUMBER}"
  
    echo "Construyendo el proyecto con Gradle Wrapper"
    sh './gradlew build -x test'

    archive 'build/libs/*.jar'

}

stage 'test'
node {
    sh './gradlew clean test'

    step([$class: 'JUnitResultArchiver', testResults: '**/build/test-results/TEST-*.xml'])
}