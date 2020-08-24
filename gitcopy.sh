#echo "testid="$1
#echo "branch="$2
git config core.sshCommand "ssh -i /home/theia/githubkey -F /dev/null"
mkdir /home/project/$2
cd /home/project/$2   
git init
git remote add origin git@github.com:infoprogrammr/$1
git remote set-url origin git@github.com:infoprogrammr/$1
git config core.sshCommand "ssh -i /home/theia/githubkey -F /dev/null"
git pull origin master --allow-unrelated-histories

git remote add origin git@github.com:infoprogrammr/$1.git
git remote set-url origin git@github.com:infoprogrammr/$1.git
git config user.name candidate
git config user.email candidate@programmr.com

git checkout -b $2

cp /home/theia/testshfiles/java/test.sh /home/project/$2
cp /home/theia/testshfiles/python/testpy.sh /home/project/$2
cp /home/theia/testshfiles/cpp/testcpp.sh /home/project/$2
cp /home/theia/.testcaseop /home/project/$2
cp /home/theia/.testcaseop2 /home/project/$2
