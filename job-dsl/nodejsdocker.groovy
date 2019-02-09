job('NodeJS Docker example') {
    scm {
        git('git://github.com/peelmicro/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('peelmicro')
            node / gitConfigEmail('juanp_perez@loyaltycrm.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('peelmicro/docker-nodejs-demo')
            tag('${GIT_COMMIT,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
