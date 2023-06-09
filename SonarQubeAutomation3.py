import os
import shutil

if __name__ == "__main__":

    path = '/Users/huangziheng/IdeaProjects/test3'  # 分析的项目名路径,项目要有独立的文件夹
    userName = 'admin'  # http://localhost:9000的用户名
    password = 'root'  # http://localhost:9000的密码

    for project_name in os.listdir(path+"/"):

        dir_arr = []
        rootdir_arr = []
        root_arr = []
        depth = 1

        src_term = 'src'

        for root, dirs, files in os.walk(path):
            if root.count(os.sep) == depth and project_name in str(root):
                print(root)
                dir_arr.append(root)

        for element in dir_arr:
            for root, dirs, files in os.walk(element):
                for elem_dir in dirs:

                    file_last = elem_dir.split('\\')[-1]
                    if file_last == 'src':
                        replace_str = 'sdf'
                        root_tbc = root.replace(element, '')
                        root_tbc = root_tbc.replace('\\', '/')
                        root_tbc = root_tbc[1:]
                        root_arr.append(root_tbc + '/src')

            with open(element + '/sonar-project.properties', 'w') as f:
                f.write("sonar.projectKey=" + element.split('\\')[-1] + '\n')
                f.write("sonar.projectName=" + element.split('\\')[-1] + '\n')
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
                # f.close()  这一行是多余的，因为你已经在一个 with open 语句中了

            list = os.listdir(element)
            for l in list:
                if l == ".scannerwork":
                    print(l)
                    shutil.rmtree(element + '/' + l)
            command = 'cd ' + element + ' & ' + 'sonar-scanner.bat -Dsonar.projectKey='+ project_name +' -Dsonar.sources=. -Dsonar.host.url=http://localhost:9000 -Dsonar.login='+userName +' -Dsonar.password='+password

            print(command)

            os.system(command)
