node {
	def server
	def buildInfo
	def rtGradle

	stage ('Artifactory configuration') {
		server = Artifactory.server "EverCraft"

		rtGradle = Artifactory.newGradleBuild()
		rtGradle.useWrapper = true
		rtGradle.deployer repo: 'gradle-release-local', server: server
		rtGradle.deployer.deployArtifacts = false // Disable artifacts deployment during Gradle run
		rtGradle.deployer.mavenCompatible = true
		rtGradle.deployer.deployIvyDescriptors = true
		
		buildInfo = Artifactory.newBuildInfo()
	}
	
	stage('Stage Checkout') {
		checkout scm
		sh "git submodule update --init"
		
		sh "chmod +x gradlew"
	}
	
	stage ('Init Properties') {
		sh "echo build_number=${env.BUILD_NUMBER} >> gradle.properties"
		def spongeapi_version = sh (script: "cat gradle.properties | grep spongeapi_version | cut -d '=' -f 2 | tr -d '\n'", returnStdout: true)
		currentBuild.displayName = "EverPlugins #${env.BUILD_NUMBER} - SpongeAPI ${spongeapi_version}"
		currentBuild.description = "For SpongeAPI ${spongeapi_version}"
		
		rtGradle.deployer.ivyPattern = "[organisation]/[module]/ivy-${spongeapi_version}.xml"
		rtGradle.deployer.artifactPattern = "[organisation]/[module]/${spongeapi_version}/[artifact]-[revision](-[classifier]).[ext]"
	}
	
	stage ('Test') {
		rtGradle.run rootDir: './', buildFile: 'build.gradle', tasks: 'clean test'
	}
	
	stage ('Deploy') {
		echo "My branch is : ${env.BRANCH_NAME}"
	
		rtGradle.run rootDir: './', buildFile: 'build.gradle', tasks: 'build shadowJar artifactoryPublish zip', buildInfo: buildInfo
		rtGradle.deployer.deployArtifacts buildInfo
	}
	
	stage ('Publish build info') {
		server.publishBuildInfo buildInfo
	}
	
	stage('Stage Upload') {
		archive 'build/plugins/*.jar'
		archive 'build/plugins/*.zip'
	}
}