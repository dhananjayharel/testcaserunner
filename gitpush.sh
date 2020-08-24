cd /home/project/$1
git config core.sshCommand "ssh -i /home/theia/githubkey -F /dev/null"
git add .
git commit -m "submit"
git push -u origin $1
echo "done"
