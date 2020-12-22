### Jenkins Orchastration with ansible
```sh
mkvirtualenv --python=python3 venvName
[or]
python3 -m venv venvName

ansible-playbook -i hosts.yml jenkins.yml -e ansible_python_interpreter=python3
```