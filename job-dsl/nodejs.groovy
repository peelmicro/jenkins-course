job('NodeJS example') {
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
        shell("npm install")
    }
}
