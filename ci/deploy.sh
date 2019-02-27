set -e
ssh -i $1 $2
sudo docker stop lvlup
sh ~/init.sh
exit
