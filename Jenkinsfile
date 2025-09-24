node {
    // Define variables
    def gitRepo = 'https://github.com/neerajk555/jenkins-pipeline-lab.git'
    def buildNumber = env.BUILD_NUMBER
    def workspace = env.WORKSPACE
    
    try {
        // Stage 1: Checkout Source Code
        stage('Checkout') {
            echo "🔄 Starting Pipeline Build #${buildNumber}"
            echo "📁 Workspace: ${workspace}"
            echo "🌐 Checking out code from GitHub..."
            
            // Clean workspace before checkout
            deleteDir()
            
            // Checkout code from GitHub
            checkout scm
            
            echo "✅ Code checkout completed successfully"
        }
        
        // Stage 2: Build Information
        stage('Build Info') {
            echo "📋 Gathering build information..."
            
            // Display environment information
            sh '''
                echo "=== Build Environment ==="
                echo "Java Version: $(java -version 2>&1 | head -n 1)"
                echo "Current Directory: $(pwd)"
                echo "Available Files:"
                ls -la
                echo "========================="
            '''
            
            // Run Gradle build info task
            sh './gradlew buildInfo'
            
            echo "✅ Build information gathered"
        }
        
        // Stage 3: Compile Application
        stage('Compile') {
            echo "🔨 Compiling Java application..."
            
            // Make gradlew executable
            sh 'chmod +x gradlew'
            
            // Compile the code
            sh './gradlew compileJava'
            
            echo "✅ Compilation completed successfully"
        }
        
        // Stage 4: Run Tests
        stage('Test') {
            echo "🧪 Running unit tests..."
            
            // Run tests
            sh './gradlew test'
            
            // Publish test results
            publishTestResults testResultsPattern: 'build/test-results/test/*.xml'
            
            echo "✅ All tests passed successfully"
        }
        
        // Stage 5: Build Application
        stage('Build') {
            echo "📦 Building application..."
            
            // Build the application
            sh './gradlew build'
            
            echo "✅ Build completed successfully"
        }
        
        // Stage 6: Run Application
        stage('Run Application') {
            echo "🚀 Running the application..."
            
            // Run the application
            sh './gradlew run'
            
            echo "✅ Application executed successfully"
        }
        
        // Stage 7: Archive Artifacts
        stage('Archive') {
            echo "📚 Archiving build artifacts..."
            
            // Archive the built JAR files
            archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
            
            echo "✅ Artifacts archived successfully"
        }
        
    } catch (Exception e) {
        // Handle any errors
        currentBuild.result = 'FAILURE'
        echo "❌ Pipeline failed with error: ${e.getMessage()}"
        throw e
    } finally {
        // Cleanup stage - always runs
        stage('Cleanup') {
            echo "🧹 Performing cleanup..."
            echo "Pipeline Status: ${currentBuild.result ?: 'SUCCESS'}"
            echo "Build Duration: ${currentBuild.durationString}"
            echo "✅ Cleanup completed"
        }
    }
}
