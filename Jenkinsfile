node {
stage("Git Clone"){
git branch: 'main', url: 'https://github.com/surya5826/Facility.git'
}

stage("Docker build"){ 
 sh 'mvn clean package' 
 sh 'docker build -t facility:latest .'
sh 'docker images'
 
stage("Deploy"){
 sh 'docker rm -f facility||true' 
sh ' docker run -d -p 9001:9001 --name facility facility:latest'
}
}
}
