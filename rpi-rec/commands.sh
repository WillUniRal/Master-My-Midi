# copy all the code to the workspace
scp -r code/* dev@raspberrypi.local:~/MMMIDI-python
# sync the code folder to workspace
rsync -avz --delete code/ dev@raspberrypi.local:~/MMMIDI-python
# dhcpcd local link ping
# this will give the ipv6 required to connect to through ssh
ping -I enp5s0 raspberrypi.local
ping -I enp1s0 raspberrypi.local