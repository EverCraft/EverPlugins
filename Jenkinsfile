node {
	currentBuild.displayName = "EverPlugins #${env.BUILD_NUMBER} - SpongeAPI 6.0.0"
	currentBuild.description = "For SpongeAPI 6.0.0"
	
	def server = Artifactory.newServer url: 'EverCraft', credentialsId: 'repo.evercraft.fr'
    	def rtGradle = Artifactory.newGradleBuild()
	def buildInfo = Artifactory.newBuildInfo()

	stage('Build') {
		checkout scm
		sh "git submodule update --init"
	}
	
	stage('Artifactory configuration') {
		rtGradle.tool = 'G3.4.1' // Tool name from Jenkins configuration
		rtGradle.deployer repo:'libs-snapshot-local',  server: server
		rtGradle.resolver repo:'remote-repos', server: server
	}
	
	stage('Config Build Info') {
		buildInfo.env.capture = true
            	buildInfo.env.filter.addInclude("*")
	}
	
	stage('Extra gradle configurations') {
		rtGradle.deployer.artifactDeploymentPatterns.addExclude("*.war")
           	rtGradle.usesPlugin = true // Artifactory plugin already defined in build script
	}
	
	stage('Exec Gradle') {
		echo "My branch is : ${env.BRANCH_NAME}"
		rtGradle.run buildFile: 'build.gradle', tasks: 'clean artifactoryPublish', buildInfo: buildInfo
	}

	
	stage('Publish build info') {
    		archive 'build/plugins/*.jar'
		archive 'build/plugins/*.zip'
		server.publishBuildInfo buildInfo
	}
}
