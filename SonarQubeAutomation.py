import os

if __name__ == "__main__":

    path = '/Users/huangziheng/IdeaProjects/test3'  # Modify this to your projects' directory

    for project_name in os.listdir(path):
        rootdir = os.path.join(path, project_name)

        dir_arr = []
        rootdir_arr = []
        root_arr = []
        depth = 1

        src_term = 'src'

        for root, dirs, files in os.walk(rootdir):
            if root.count(os.sep) == depth and project_name in str(root):
                print(root)
                dir_arr.append(root)

        for element in dir_arr:
            for root, dirs, files in os.walk(element):
                for elem_dir in dirs:
                    file_last = elem_dir.split(os.sep)[-1]
                    if file_last == 'src':
                        replace_str = 'sdf'
                        root_tbc = root.replace(element, '')
                        root_tbc = root_tbc.replace(os.sep, '/')
                        root_tbc = root_tbc[1:]
                        root_arr.append(root_tbc + '/src')

            with open(os.path.join(element, 'sonar-project.properties'), 'w') as f:
                f.write("sonar.projectKey=" + element.split(os.sep)[-1] + '\n')
                f.write("sonar.projectName=" + element.split(os.sep)[-1] + '\n')
                f.write("sonar.projectVersion=1.0\n")
                f.write('sonar.java.binaries=')
                for i in range(len(root_arr)):
                    if i != len(root_arr) - 1:
                        f.write(root_arr[i] + ',')
                    else:
                        f.write(root_arr[i] + '\n')
                root_arr.clear()
                f.write("sonar.sourceEncoding=UTF-8\n")
                f.write("sonar.language=java\n")


            command = 'cd ' + element + ' && /usr/local/sonar/sonar-scanner-4.8.0.2856-macosx/bin/sonar-scanner ' \
                                        '-Dsonar.projectKey=' + project_name + ' -Dsonar.sources=. ' \
                                                                               '-Dsonar.host.url=http://localhost:9000 ' \
                                                                               '-Dsonar.login=admin ' \
                                                                               '-Dsonar.password=root'

            print(command)

            os.system(command)
