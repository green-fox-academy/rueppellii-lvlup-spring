ssh -i $1 $2
sudo docker stop lvlup
sudo docker run \
--name lvlup \
--restart=always \
-d \
foxyfox/lvlup-spring:$3
exit
