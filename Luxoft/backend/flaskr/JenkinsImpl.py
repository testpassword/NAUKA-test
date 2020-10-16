import jenkins


# https://medium.com/@prashant.vats/trigger-jenkins-job-remotely-using-python-22420792bac2
class JenkinsImpl:
    def __init__(self, url, username, password):
        self.jenkins_server = jenkins.Jenkins(url, username, password)
        user = self.jenkins_server.get_whoami()
        version = self.jenkins_server.get_version()
        print("SUCCESS!\nJenkins Version: {} start by user: {}".format(version, user["id"]))

    def launchPipeline(self, name, parameters=None, token=None):
        next_build_number = self.jenkins_server.get_job_info(name)['nextBuildNumber']
        self.jenkins_server.build_job(name, parameters=parameters, token=token)
        res = self.jenkins_server.get_build_info(name, next_build_number)
        return res